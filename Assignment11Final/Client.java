import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * this class requests the server for play the game.
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 *
 */
public class Client {
	String hostName = "127.0.0.1";
	int port = 2200;
	static Socket sock;

	/**
	 * in this default constructor , if the ip address and port are not passed
	 * by the user, then by default it will create.
	 */
	public Client() {
		try {
			sock = new Socket(hostName, port);
		} catch (Exception e) {

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
		this.port = port;
		try {
			sock = new Socket(hostName, port);
		} catch (Exception e) {

		}
	}

	/**
	 * we are initiating two threads, one to write to the server and one to read
	 * the message server sent at any time.
	 */
	public void startGame() {
		new Thread(new Runnable() {
			public void run() {
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					String s;
					while (true) {
						if ((s = in.readLine()) != null)
							System.out.println(s);
					}
				} catch (Exception e) {
					System.exit(0);
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				try {
					String s;
					Scanner sc = new Scanner(System.in);
					;
					PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
					while (true) {
						if ((s = sc.nextLine()) != null) {
							if (s.equals("exit")) {
								System.exit(0);
							} else {
								out.println(s);
							}
						}

					}
				} catch (Exception e) {

					System.exit(0);

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