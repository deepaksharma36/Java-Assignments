
public class CPUplayer implements PlayerInterface
{

	private String name;
	private char GamePiece;
	private Connect4Field aConnect4Field; 
	public CPUplayer(Connect4Field aConnect4Field,String name, char GamePiece  )
	{
		this.aConnect4Field=aConnect4Field;
		this.GamePiece=GamePiece;
		this.name=name;
		

	}

	public char getGamePiece() {
		// TODO Auto-generated method stub
		return GamePiece;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public int nextMove() {
		// TODO Auto-generated method stub
		int playersLastMove=aConnect4Field.getLastMove()[1];
		int hereIamWining=whereWining(aConnect4Field.getPlayerSymbol()[1]);
	    if(hereIamWining>-1)
	    	return hereIamWining+1;
	    int hereHumanWining=whereWining(aConnect4Field.getPlayerSymbol()[0]);
	    if (hereHumanWining>-1)
		       return hereHumanWining+1;
		if (aConnect4Field.checkIfPiecedCanBeDroppedIn(playersLastMove+2))
			   return playersLastMove+2;
		if (aConnect4Field.checkIfPiecedCanBeDroppedIn(playersLastMove))
		    return aConnect4Field.getLastMove()[1]; 
		
		else
			   return aConnect4Field.getBoardSize()[1]/2;
	}
	public int whereWining(char Symbol)
	{		
	for(int index=1; index<=aConnect4Field.getBoardSize()[1];index++ )
	{
		if(aConnect4Field.checkIfPiecedCanBeDroppedIn(index))
		{
		aConnect4Field.dropPieces(index, Symbol);
        if(aConnect4Field.didLastMoveWin())
        {
        	aConnect4Field.undoDropPiece();	
        	return aConnect4Field.getLastMove()[1];
        	
        }
        else
        {
        	aConnect4Field.undoDropPiece();
        }
		}
	}
         return -1;
 
	}
	
		
	}


