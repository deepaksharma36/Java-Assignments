import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;


public class TpServer {
	int TcpPort=2040;
	int UdpPort=2041;
	public void tcpServer()
	{
		int readData=0;
		try{
	   
	    ServerSocket server = new ServerSocket(TcpPort);
        Socket socket = server.accept();
	    InputStream dataInput = socket.getInputStream();
	    long startTime = System.currentTimeMillis();
	    long total = 0;
	    int timeSlote=100000;
	    byte[] dataPacket = new byte[64*1000]; 
	    int counteri=1;
	    while(true){
	    	
	         readData = dataInput.read(dataPacket);
	         counteri++;
	        if (readData ==-1) 
	        	{break;
	        	}
	        total += readData;
	        if (counteri % timeSlote == 0) {
	            long timeFram = System.currentTimeMillis() - startTime;
	            System.out.printf("server has Read "+total+" bytes, speed: "+ total/timeFram/1000+" MB/s%n");
	        }
	    }
	}catch(Exception ex)
	{
		ex.printStackTrace();
		
	}
		}
	
	public void UDPServer()
	{
		long count=0;
		long dataRead=0;
	
		try {
			DatagramSocket datagramSocket = new DatagramSocket(UdpPort);
			int timeSlote=100000;
			long startTime = System.currentTimeMillis();
			while (true) {
				byte[] buffer = new byte[64*1000];
				DatagramPacket packet = new DatagramPacket(buffer,
						buffer.length);
				datagramSocket.receive(packet);
				//System.out.println("Packet Received");
				
				count++;
				dataRead+=64*1000;
				if (count % timeSlote == 0) {
		            long timeFram = System.currentTimeMillis() - startTime;
		            
		            System.out.println("server has Read "+dataRead/1024+" kilo bytes, speed: "+ dataRead/timeFram/1000+" MB/s");
		        }	

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
	public void startDataReceivingTCP(){
		new Thread(new Runnable() {
			public void run() {
			tcpServer();	
				
			}
		}).start();
		
	}
	public void startDataReceivingUDP() {
		new Thread(new Runnable() {
			public void run() {
			UDPServer();	
				
			}
		}).start();
		

	}
    public static void main(String[] args) {

    	TpServer aTpServer = new TpServer();
    	if(args[1].equals("TCP"))
    	aTpServer.startDataReceivingTCP();
    	else
    	aTpServer.startDataReceivingUDP();
    	
    	
}
}