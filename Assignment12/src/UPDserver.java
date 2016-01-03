import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;


public class UPDserver {

	public static void main(String[] args) {
	try{
	DatagramSocket datagramSocket = new DatagramSocket();
	byte[] buffer; //= new byte[10];
	buffer="my name is deepak".getBytes();
	while(true){
	InetAddress address = InetAddress.getLocalHost();
	DatagramPacket packet = new DatagramPacket(
	buffer, buffer.length,address, 9001);
	datagramSocket.send(packet);
	}
	}
	catch( Exception ex)
	{
		
	}
}
}