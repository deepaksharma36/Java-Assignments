public class T_2 extends Thread    {
    int id = 1;
    static String  theValue  = "1";
    T_2(int id)       {
        this.id = id;
    }
    public void run () {
        if ( id == 1 )
                theValue = "3";
        else
                theValue = "2";
    }      
        
    public static void main (String args []) {
        new T_2(1).start();;
        new T_2(2).start();;
            
        System.out.println("theValue = " + theValue );
        System.out.println("theValue = " + theValue );
    }       
}  
/*
 * theValue = 3		here if part of the run method is executed. 
 * theValue = 2		here else part of the run method is executed.
 * 
 * theValue = 1 	main thread executes before the two threads are executed.
 * theValue = 1		main thread executes before the two threads are executed.
 * 
 * theValue = 1		main thread executes before the two threads are executed.
 * theValue = 3		here if part of run method is executed by one of the threads.
 * 
 * theValue = 2		here else part of the run is executed by one of the threads.
 * theValue = 2		here the value sets to 2 and the main thread is executed.
 * 
 * theValue = 3		here if part of the run is executed by one of the threads.
 * theValue = 3		here the value sets to 3 and the main thread is executed.
 * 
 * theValue = 1		main thread executes before the two threads are executed.
 * theValue = 2		here else part of run method is executed by one of the threads. 
 * 
 * theValue = 2		here else part of run method is executed by one of the threads.
 * theValue = 3		here if part of run method is executed by one of the threads.
 *
 * */
