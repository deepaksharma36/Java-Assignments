import java.net.*;
import java.io.*;
public class GameServer extends Thread {
   ServerSocket aServerSocket;
   final int port     = 2200;
   volatile int twoPlayerGameCount=0;
   volatile int fourPlayerGameCount=0;
   Object playerLock2;
   Object playerLock4;
   Connect4Field aConnect4FieldFourPlayer = new Connect4Field();
   Connect4Field aConnect4FieldTwoPlayer = new Connect4Field();
   PlayerInterface[] twoPlayerGame = new PlayerInterface[2];;
   PlayerInterface[] fourPlayerGame = new PlayerInterface[4];;
 //For defalt port no 4242
   public GameServer()	{
	   try{
	   aServerSocket=new ServerSocket(port);
	   this.playerLock2=new Object();
	   this.playerLock4=new Object();
	   }
	   catch(IOException io){
		   
	   }
   }
   public static void main(String args[]) {

 		new GameServer().start();

     }
  public int getPort() {
	return port;
}
   public void run()	{
        try {
        	
            for(;;) {
                Socket aClient = aServerSocket.accept();
                System.out.println("Received Client Request from"+aClient);
                new ClientRequestHandler(aClient,this).start();
                new GameRunner(this).start();
             /*   System.out.println(clnt.toString());
                PrintWriter out = new PrintWriter
                    (clnt.getOutputStream (), true);
                System.out.println("sdcsd");
                out.println("Sending to client");
                BufferedReader din = new BufferedReader (
            			new InputStreamReader (clnt.getInputStream()));
            		String rTime = din.readLine ();
            		System.out.println (rTime);
                clnt.close();*/
            }
        } catch(Exception e) {
            
	    e.printStackTrace();
        }
   }
    public int getTwoPlayerGameCount() {
    	synchronized (playerLock2) {
    	return twoPlayerGameCount;
    	}
    }

    public void setTwoPlayerGameCount(int twoPlayerGameCount) {
    	synchronized (playerLock2) {
    		this.twoPlayerGameCount = twoPlayerGameCount;	
		}
    	
    }
    public int getFourPlayerGameCount() {
    	   synchronized (playerLock4) {
    	return fourPlayerGameCount;
    	   }
    }

    public void setFourPlayerGameCount(int fourPlayerGameCount) {
    	synchronized (playerLock4) {
    	this.fourPlayerGameCount = fourPlayerGameCount;
    	}
    }

    
    public Connect4Field getaConnect4FieldFourPlayer() {
    	synchronized (playerLock4) {
			
		
    	return aConnect4FieldFourPlayer;
    	}
    }

    public void setaConnect4FieldFourPlayer(Connect4Field aConnect4FieldFourPlayer) {
    	synchronized (playerLock4){
    	this.aConnect4FieldFourPlayer = aConnect4FieldFourPlayer;
    }
    }
    public Connect4Field getaConnect4FieldTwoPlayer() {
    	synchronized (playerLock2){
    	return aConnect4FieldTwoPlayer;
    	}
    }

    public void setaConnect4FieldTwoPlayer(Connect4Field aConnect4FieldTwoPlayer) {
    	synchronized (playerLock2){
    	this.aConnect4FieldTwoPlayer = aConnect4FieldTwoPlayer;
    	}
    }    

       public PlayerInterface[] getTwoPlayerGame() {
    		synchronized (playerLock2){
    	   return twoPlayerGame;
    		}
    }

    public void setTwoPlayerGame(PlayerInterface[] twoPlayerGame) {
    	synchronized (playerLock2){
    	this.twoPlayerGame = twoPlayerGame;
    	}
    }

    public PlayerInterface[] getFourPlayerGame() {
    	synchronized (playerLock4){
    	return fourPlayerGame;
    	}
    }

    public void setFourPlayerGame(PlayerInterface[] fourPlayerGame) {
    	synchronized (playerLock4){
    	this.fourPlayerGame = fourPlayerGame;
    	}
    }

    
    
}