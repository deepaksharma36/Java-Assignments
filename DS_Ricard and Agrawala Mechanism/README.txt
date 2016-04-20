
Files and Discription
 TransactionsLog.java  Records Events on Process
 Transaction.java      An representation of event
 CSRequest.java           An representation of CS request
 ServerRMI.java           Controller of the Process, Intiate all the Threads
 Process.java        Implementation of all functionalities concerning to Process
 ProcessInterface.java    An Interface to Process for Enabling remote Processes to access Methods

How to Run
    Step 1 store all the files on selected destination servers(4 or more):
        "glados.cs.rit.edu", "kansas.cs.rit.edu",  "gorgon.cs.rit.edu","doors.cs.rit.edu",
        "newyork.cs.rit.edu", "yes.cs.rit.edu", "kinks.cs.rit.edu", "medusa.cs.rit.edu", "joplin.cs.rit.edu",
        "delaware.cs.rit.edu", "buddy.cs.rit.edu", "arizona.cs.rit.edu"   
    Step 2 Know the ID of selected Server, The id's of servers are in increasing order from 1 to N in the above mentioned series.
           Example Id of Glados is 1 and Kansas is 2,
        To change the Id change the array "PROCESS" in ProcessInterface.java accordingly
    Step 3 Compile the program using javac *. java on each destination server.
    Step 4 Run the program using java ServerRMI <portNumber> <Id number> <Number of Processes>. DO NOT PRESS Enter!!! once the execution get             start. First start all the servers using step 4.
        portNumber Should be same on each process
        Idnumber Should be according to the series mentioned in step 1
        Number of process will be equal to number of process taking part in mutual exclution program
    Step 5 Repeat task 4, for all the server.
    Step 6 Press Enter on each server to intitate the Process.
   
Events
Start Seeking Critical Section (This event will be compared with others event)
Receiving ok Request from other Server
Receiving Token Request from other Server (If receiver in future Invoke Seeking Critical Section, by Updating clock we can resolve the order)
Receiving/Deposit Money
withdrawing/Sending Money
   
Possible Error: Deadlock
Reason: - There is a thread requestResolverThread, which should be activated(should inside run) (One time activity at starting) before the process receive First Toekn requests. such request will not entertained if thread is not running, A server has no control over receiving request from other server.
Give some time In the beginning, then press Enter.      

