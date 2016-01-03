

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
//import files are placed here.
import java.util.Scanner;

/**
 * This class lets the player play the connect four field game between two human
 * players or between a human player and the CPU. Every player has a name and a
 * symbol(game piece) assigned. PlayerInterface methods implements this method.
 * 
 * @author Sharma, Deepak DS5930
 * @author Kurra, Sree Lakshmi SK9040
 */
public class Player implements PlayerInterface {
	private String name;
	private char GamePiece;
	private ClientRequestHandler aHandler;
	private int move=-1;
	
	public int getMove() {
		return move;
	}

	public void setMove(int move) {
		this.move = move;
	}

	private Connect4Field aConnect4Field;

	/**
	 * The game which player is playing.
	 * 
	 * @param aConnect4Field
	 *            is a class which executes the functionalities of the game and
	 *            stores the state of the game.
	 * @param name
	 *            name of the player
	 * @param GamePiece
	 *            symbol assigned to the player
	 */
	public Player(Connect4Field aConnect4Field, String name, char GamePiece, ClientRequestHandler aHandler) {
		this.aConnect4Field = aConnect4Field;
		this.GamePiece = GamePiece;
		this.name = name;
		this.aHandler=aHandler;

	}

	/**
	 * returns the name of the player and in the next move, the player will
	 * provide his input in the form of column number at the place where he
	 * wants to place his game piece.
	 */
	public char getGamePiece() {

		return GamePiece;
	}

	/**
	 * return the name of the player
	 */
	public String getName() {

		return name;
	}

	public void playerView(String o)
	{
		try {
			DatagramSocket datagramSocket = new DatagramSocket();
			byte[] buffer; //= new byte[10];
			buffer=o.getBytes();
			DatagramPacket packet = new DatagramPacket(
			buffer, buffer.length,aHandler.clientIP, aHandler.clienPort);
			datagramSocket.send(packet);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}
	
	/**
	 * takes player input to make his move.
	 */
	public int nextMove() {
		try {
		synchronized(aHandler)
		{
		aHandler.setStatus(true);
		
			aHandler.wait();
		System.out.println("Move"+move);
		return move;
		
		}
		}catch (InterruptedException e) {
			return -1;
		}
		finally{
			aHandler.setStatus(false);
			
		}
		
		//in.readline
/*		String userInput="-1";
		Scanner sc;
		try {
			
			sc = new Scanner(aSocket.getInputStream());
			//userInput =sc.nextInt();
			while((userInput=sc.nextLine()) == null)
			userInput = sc.nextLine();
			return Integer.parseInt(userInput);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;*/
		}
		


	

}
