import java.net.Socket;



public interface PlayerInterface {


	
	public char getGamePiece();
	public String getName();
	
	public int  nextMove();
	public void playerView(String o);
}
