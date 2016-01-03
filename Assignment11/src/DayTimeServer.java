import java.net.*;
import java.io.*;
import java.util.*;
// java DayTimeServer -port 12345
public class DayTimeServer extends Thread {
   ServerSocket 	aServerSocket;
   int			port     = 4242;
   //For defalt port no 4242
   public DayTimeServer()	{
   }

   public DayTimeServer(int port)	{
        try { 
            aServerSocket = new ServerSocket(port);
            System.out.println ("Listening on port: " + aServerSocket.getLocalPort());
        } catch(Exception e) {
            System.out.println(e);
        }
   }

    private void printMessage()	{
	System.out.println("-h		---->	help");
	System.out.println(" -port 		port");
	System.out.println(" {-port 		port}");
	System.out.println("or ");
	System.out.println(" no argument");
   }
	
   /**
     * Parse the commandlind arguments and sets variables.
     */
   private  void parseArgs(String args[]) {
	for (int i = 0; i < args.length; i ++) {
	   	if (args[i].equals("-h")) 
			printMessage();
	   	else if (args[i].equals("-port")) {
			port = new Integer(args[++i]).intValue();
			new DayTimeServer(port).start();
		}
	}
   }
   
   public void run()	{
        try {
            for(;;) {
                Socket clnt = aServerSocket.accept();
                System.out.println(clnt.toString());
                PrintWriter out = new PrintWriter
                    (clnt.getOutputStream (), true);
                System.out.println("sdcsd");
                out.println("Sending to client");
                BufferedReader din = new BufferedReader (
            			new InputStreamReader (clnt.getInputStream()));
            		String rTime = din.readLine ();
            		System.out.println (rTime);
                clnt.close();
            }
        } catch(Exception e) {
            System.out.println(e);
	    e.printStackTrace();
        }
   }

    public static void main(String args[]) {
	if ( args.length == 0 )
		new DayTimeServer(2156).start();
	else
		new DayTimeServer().parseArgs(args);
    }
}