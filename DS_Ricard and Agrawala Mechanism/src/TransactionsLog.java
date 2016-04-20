import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class Keep track of all events which has taken place since Starting or Server
 * Used for Debuging 
 * @author deepak
 *
 */
public class TransactionsLog {
 ArrayList<Transaction> log;
TransactionsLog(){
	log = new ArrayList<Transaction>();
}
public synchronized void registerEvent(Transaction aTransaction){

		log.add(aTransaction);
}

public void show(){
		for(Transaction tr:log)
		{
			System.out.print(tr);
		}
	}
public synchronized void showLast()
{
System.out.println(log.get(log.size()-1));	
}
}
