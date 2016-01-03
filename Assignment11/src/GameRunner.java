// this class explore the oportunities for starting a new game
public class GameRunner extends Thread {
GameServer aGameServer;

public GameRunner(GameServer aGameServer )
{
	this.aGameServer=aGameServer;
	
}

public void run()
{
	while(true)
	{       
			if(aGameServer.getFourPlayerGameCount()==4)
			{
			//aGameServer.getaConnect4FieldFourPlayer().setSizeBoard(11,25);
			new Controller(aGameServer.getaConnect4FieldFourPlayer(), aGameServer.getFourPlayerGame()).start();
			aGameServer.setaConnect4FieldFourPlayer(new Connect4Field());
			aGameServer.setFourPlayerGame(new PlayerInterface[4]);
			aGameServer.setFourPlayerGameCount(0);
			}
		
		 
			if(aGameServer.getTwoPlayerGameCount()==2)
			{
				System.out.println("Intiating A new Two Player Game");
				System.out.println(aGameServer.getTwoPlayerGame()[0]);
				System.out.println(aGameServer.getTwoPlayerGame()[1]);
			new Controller(aGameServer.getaConnect4FieldTwoPlayer(), aGameServer.getTwoPlayerGame()).start();
			aGameServer.setaConnect4FieldTwoPlayer(new Connect4Field());
			aGameServer.setTwoPlayerGame(new PlayerInterface[2]);
			aGameServer.setTwoPlayerGameCount(0);
			}
	}

}

}
