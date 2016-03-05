import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * this class requests the server for play the game.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 * 
 */
public class Client {
	String hostName = "129.21.89.230";
	InetAddress serverIp;
	int Serverport = 2200;
	int myPort = 2201;
	DatagramSocket datagramSocket;
	static Socket sock;

	// View aView = new View();
	/**
	 * in this default constructor , if the ip address and port are not passed
	 * by the user, then by default it will create.
	 */
	public Client() {
		try {
			serverIp = InetAddress.getByName(hostName);
			this.datagramSocket = new DatagramSocket(myPort);
			this.datagramSocket.connect(serverIp, myPort);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * in this constructor , the ip address and port are passed by the user,
	 * 
	 * @param hostName
	 * @param port
	 */
	public Client(String hostName, int port) {
		this.hostName = hostName;
		this.Serverport = port;
		try {
			this.serverIp = InetAddress.getByName(hostName);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * we are initiating two threads, one to write to the server and one to read
	 * the message server sent at any time.
	 */
	public void startGame() {
		// Data Receiver
		new Thread(new Runnable() {
			public void run() {
				try {
					byte[] buffer = new byte[10240];
					String s;
					while (true) {
						DatagramPacket packet = new DatagramPacket(buffer,
								buffer.length);
						datagramSocket.receive(packet);

						if ((s = new String(packet.getData())) != null) {
							System.out.println(s + "Received");
							s = null;
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();
		// data Sender
		new Thread(new Runnable() {
			public void run() {
				try {
					String choice, name = "";
					System.out
							.println("press 2 for 2 player game else 4 for 4 player game");
					Scanner sc = new Scanner(System.in);
					choice = sc.nextLine().trim();
					System.out.println(choice);
					while (!choice.equals("4") && !(choice.equals("2"))) {
						System.out.println("wrong Input, Please enter again");
						choice = sc.nextLine().trim();

					}
					System.out.println("Please Provide your name");
					while ((name = sc.nextLine()).trim().equals("")) {
						System.out.println("No Input, Please enter again");
					}
					String data = choice + " " + name;
					// DatagramSocket datagramSocket = new DatagramSocket();
					byte[] buffer;// = new byte[50];
					buffer = data.getBytes();
					// InetAddress address = serverIp;
					DatagramPacket packet = new DatagramPacket(buffer,
							buffer.length, serverIp, Serverport);
					System.out.println(new String(packet.getData()));
					System.out.println("");
					datagramSocket.send(packet);
					String s = "";
					while (true) {

						if (!(s = sc.nextLine()).equals("")) {
							buffer = s.getBytes();

							packet = new DatagramPacket(buffer, buffer.length,
									serverIp, Serverport);
							datagramSocket.send(packet);
						}
					}

				} catch (Exception e) {

					e.printStackTrace();

				}
			}
		}).start();
	}

	public static void main(String[] args) {

		if (args.length == 2) {
			String hostName = args[0];
			int port = Integer.parseInt(args[1]);
			Client aClient = new Client(hostName, port);
			aClient.startGame();
		} else {
			Client aClient = new Client();
			aClient.startGame();
		}

	}
}