import java.net.Socket;



public interface PlayerInterface {


	
	public char getGamePiece();
	public String getName();
	public Socket getaSocket();
	public int  nextMove();
}
