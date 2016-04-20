import java.util.Arrays;

/**
 * This Class Store an Event
 * @author deepak
 *
 */
public class Transaction {
String type;
int[] timeStamp;
int amountInvolved;
int amount;
public Transaction(String type, int[] timeStamp, int amountInvolved, int amount) {
this.type=type;
this.timeStamp=Arrays.copyOf(timeStamp,timeStamp.length);
this.amountInvolved=amountInvolved;
this.amount=amount;
}
public String toString()
{
	return "\n"+Arrays.toString(timeStamp)+"\n\t"+type+"\t"+amountInvolved+"\n\t"+"Final Amount: "+amount+"\n";
}

}
