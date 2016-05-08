import java.io.Serializable;


 class ElectionMessage implements Serializable{
int processID;
public int getProcessID() {
	return processID;
}
public void setProcessID(int processID) {
	this.processID = processID;
}
int MessageID;
public int getMessageID() {
	return MessageID;
}
public void setMessageID(int messageID) {
	MessageID = messageID;
}
ElectionMessage(int processID, int MessageID)
{
	this.processID=processID;
	this.MessageID=MessageID;
}


}
