public class T_1 extends Thread	{
	static int x = 1;
	String info = "";

	public T_1 (String info) {
		this.info = info;
		x++;//object created
	}

	public void run () {
		x++;//execution started
		String output = x + " " + info + ": " + x;
		System.out.println(output);
	}

	public static void main (String args []) {
		new T_1("a").start();
		new T_1("b").start();
	}
}
/*
 * all possible outputs are below.
 * 3 a/b: 3		when one thread is created and executed before second thread is created.
 * 5 a/b: 5		both threads are created and starts execution.
 *  
 * 3 a/b: 4		when one thread is created and starts execution, but meanwhile second thread was created. 
 * 5 b/a: 5 	both threads are created and starts execution.
 * 
 * 3 a/b: 5		when one thread is created and starts execution, but meanwhile second thread was created and executed.
 * 5 b/a: 5		both threads are created and starts execution.
 * 
 * 4 a/b: 4		when one thread is created and start execution and second thread is created.			
 * 4 a/b: 5		here second thread starts executing and x sets to 5.
 * 
 * 4 a/b: 4		when one thread is created and start execution and second thread is created.
 * 5 a/b: 5		here second finishes execution and the value of x becomes 5.
 * 
 * 4 a/b: 5		when one thread is created and starts execution, at the beginning of the print statement x is 4, but meanwhile second thread finishes execution and sets x to 5.
 * 5 a/b: 5		thread second and one both are created and executed.
 * 
 * 5 a/b: 5		when both the threads are created and start their execution before their print statements execute.
 * 4 a/b: 4		both threads are created but only one thread starts execution.
 * 
 * 5 a/b: 5		both threads have been created and executed before the print statement.
 * 5 a/b: 5		both threads have been created and executed before the print statement.
 * 
 * 5 a/b: 5		when both the threads are created and start their execution before their print statements execute.
 * 4 a/b: 5		during the execution of the print statement one of the threads dint start its execution.
 */
