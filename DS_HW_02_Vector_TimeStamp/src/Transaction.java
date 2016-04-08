
public class Transaction {
String type;
String timeStamp;
int amountInvolved;
int amount;
public Transaction(String type, String timeStamp, int amountInvolved, int amount) {
this.type=type;
this.timeStamp=timeStamp;
this.amountInvolved=amountInvolved;
this.amount=amount;
}
public String toString()
{
	return "\n"+timeStamp+"\n\t"+type+"\t"+amountInvolved+"\n\t"+"Final Amount: "+amount+"\n";
}

}
