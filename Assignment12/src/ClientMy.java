import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;


public class ClientMy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
		DatagramSocket datagramSocket;
		datagramSocket  = new DatagramSocket(9001);
		byte[] buffer = new byte[1024];
		while(true){
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		datagramSocket.receive(packet);
		String message= new String(packet.getData());
		System.out.println(message+"I am not doing this");
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
