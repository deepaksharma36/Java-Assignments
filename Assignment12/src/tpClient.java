import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


public class tpClient {
	int TcpPort=2040;
	int UdpPort=2041;
	InetAddress	UDPIp;
	DatagramSocket datagramSocket;
	tpClient(){
		try{
	 datagramSocket=new DatagramSocket();
	String hostName = "127.0.0.1";
	UDPIp = InetAddress.getByName(hostName);
		}catch(Exception ex)
		{}
	}
	
	
	public void TcpClient()
	{
	try{
		 Socket socket = new Socket("127.0.0.1", TcpPort);
        OutputStream output = socket.getOutputStream();

        byte[] bytes = new byte[64*1024]; 
        while (true) {
            output.write(bytes);
        }
    }catch(Exception ex)
    {
    	
    }
	
	}
	
	public void UDPtpClient()
	{
		try {
			while (true) {
			byte[] buffer = new byte[1000*24];
			DatagramPacket packet = new DatagramPacket(buffer,
					buffer.length, UDPIp, UdpPort);
			datagramSocket.send(packet);
			
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		
	}
	
	
	
	
    public static void main(String[] args)  {
    tpClient aTpClient = new tpClient();
    	if(args[1].equals("TCP"))
    	aTpClient.TcpClient();
    	else
    		aTpClient.UDPtpClient();
    	
    	
}
    }