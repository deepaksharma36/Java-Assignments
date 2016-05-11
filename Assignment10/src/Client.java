
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class Client extends Applet implements Runnable, KeyListener {

	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	Socket sock = null;
	BufferedOutputStream socketOutputStream = null;
	BufferedInputStream socketInputStream = null;

	boolean stopCapture = false;

	AudioFormat audioFormat;
	TargetDataLine targetDataLine;
	AudioInputStream audioInputStream;
	SourceDataLine sourceDataLine;

	Mixer.Info[] mixerInfo;
	DataLine.Info dataLineInfo;
	Mixer mixer;

	// AudioFormat
	float sampleRate = 8000.0F;
	int sampleSizeInBits = 8;
	int channels = 1;
	boolean signed = true;
	boolean bigEndian = false;

	public void init() {
		setSize(WIDTH, HEIGHT);
		addKeyListener(this);
	}

	public void initClient() throws LineUnavailableException {
		mixerInfo = AudioSystem.getMixerInfo();
		System.out.println("Available mixers:");
		for (int cnt = 0; cnt < mixerInfo.length; cnt++) {
			System.out.println(mixerInfo[cnt].getName());
		}
		audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
		mixer = AudioSystem.getMixer(mixerInfo[2]);

		targetDataLine = (TargetDataLine) mixer.getLine(dataLineInfo);
		targetDataLine.open(audioFormat);
		targetDataLine.start();
	}

	public void start() {

		try {
			initClient();
			System.out.println("Connecting...");
			sock = new Socket("169.254.123.140", 7777);
			socketOutputStream = new BufferedOutputStream(sock.getOutputStream());
			socketInputStream = new BufferedInputStream(sock.getInputStream());
			System.out.println("Connected successfully");

			DataLine.Info dataLineInfo1 = new DataLine.Info(SourceDataLine.class, audioFormat);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo1);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();

			Thread outputThread = new Thread(this);
			outputThread.start();

			captureAndSend input = new captureAndSend(socketInputStream, this);
			Thread inputThread = new Thread(input);
			inputThread.start();

		} catch (Exception e) {
			System.out.println("unable to connect to client ");
		}

	}

	public void paint(Graphics g) {

	}

	@Override
	public void run() {
		byte[] bytebuffer = new byte[10000];

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		stopCapture = false;

		while (!stopCapture) {

			int count = targetDataLine.read(bytebuffer, 0, bytebuffer.length);
			try {
				socketOutputStream.write(bytebuffer);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}

class captureAndSend implements Runnable {

	TargetDataLine targetDataLine;
	AudioFormat audioFormat;
	SourceDataLine sourceDataLine;
	static Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
	Mixer mixer;
	DataLine.Info dataLineInfo;

	byte tempBuffer[] = new byte[10000];

	// Audio Format
	float sampleRate = 8000.0F;
	int sampleSizeInBits = 8;
	int channels = 1;
	boolean signed = true;
	boolean bigEndian = false;

	InputStream in;
	OutputStream out;
	Client client;

	public captureAndSend(InputStream in, Client c) throws LineUnavailableException {

		this.in = in;
		this.client = c;
		mixer = AudioSystem.getMixer(mixerInfo[0]);
		audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
		sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
		sourceDataLine.open(audioFormat);
		sourceDataLine.start();
	}

	@Override
	public void run() {
		
		try {
			Thread.sleep(1);
			while (in.read(tempBuffer) > 0 ) {
				sourceDataLine.write(tempBuffer, 0, 10000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
