import java.net.*;
import java.io.*;
import java.net.Socket;

/**
 * This class create player when it receive a new new request for playing. this
 * will interact the details with the player and also creates the object of
 * player n stores it in the data structure array
 * 
 * @author Deepak Sharma ds5930
 * @author Sree Lakshmi Kurra sk9040
 * 
 */
public class ClientRequestHandler extends Thread {

	private DatagramPacket packet;
	
	public DatagramPacket getPacket() {
		return packet;
	}

	Player myPlayer;
	public void setPacket(DatagramPacket packet) {
		this.packet = packet;
	}

	InetAddress clientIP;
	int clienPort;
	GameServer aGameServer;
	char[] GamePiece = { '*', '^', '#', '$' };
	volatile boolean status = false;
	volatile boolean isNewPacket = false;
	public synchronized boolean isNewPacket() {
		return isNewPacket;
	}

	public synchronized void  setNewPacket(boolean isNewPacket) {
		this.isNewPacket = isNewPacket;
	}

	public synchronized void  setStatus(boolean status) {
		
		this.status = status;
	}

	public synchronized boolean isStatus() {
		return status;
	}

	public ClientRequestHandler(DatagramPacket packet, GameServer aGameServer) {
		this.packet = packet;
		this.aGameServer = aGameServer;
		this.clientIP = packet.getAddress();
		this.clienPort = packet.getPort();
		
		//this.start();

	}

	/**
	 * in run method, it interacts with the player and creates the player object
	 * and stores the information in appropriate arrays.
	 * 
	 */
	public void run() {
		playerSetUp();
		try {
			while(true){
			while (!status || !isNewPacket )
			{
			}
			if(isNewPacket)
			{	String move =new String(packet.getData());
				System.out.println("My Move is "+ move);
				synchronized(this)
				{
				myPlayer.setMove(Integer.parseInt(move.trim()));	
				 this.notify();
				}
				setNewPacket(false);
			}
			
			
			}
		}catch(Exception ex)
		{}
	}
	
	public void playerSetUp()
	{
		try{
		System.out.println("Creating New Players");
		//System.out.println(getPacket().getData());
		int my =getPacket().getPort();
		System.out.println(my);
		String message = new String(getPacket().getData(), 0, getPacket().getLength());//,packet.getOffset(),packet.getLength());
		String choice=message.trim().split(" ")[0];
		String name=message.trim().split(" ")[1];
		System.out.println("Player Choice"+choice);
		System.out.println("Player Name"+name);
		if (choice.equals("4") && aGameServer.getFourPlayerGameCount() < 4) {
			System.out.println("Creating New Player for four player game"); 
				myPlayer	 = new Player(
					aGameServer.getaConnect4FieldFourPlayer(), name,
					GamePiece[aGameServer.getFourPlayerGameCount()], this);
			aGameServer.setFourPlayerGameCount(aGameServer.getFourPlayerGameCount() + 1);
			aGameServer.getFourPlayerGame()[aGameServer.getFourPlayerGameCount()]= myPlayer;
		}
		if (choice.equals("2")) {
			System.out.println("Creating New Player for two player game");
			myPlayer = new Player(
					aGameServer.getaConnect4FieldTwoPlayer(), name, GamePiece[aGameServer.getTwoPlayerGameCount()],
					this);
			 aGameServer.getTwoPlayerGame()[aGameServer.getTwoPlayerGameCount()]=myPlayer;
			aGameServer.setTwoPlayerGameCount(aGameServer.getTwoPlayerGameCount() + 1);
			System.out.println("Right now We have "+aGameServer.getTwoPlayerGameCount()+" Players");
		}
		packet=null;
		}
		catch(Exception e)
		{
			System.out.println("Something Went Wrong"+e);
		}
	}
	
	}
	
