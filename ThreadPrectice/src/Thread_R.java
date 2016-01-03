import java.util.*;

public class Thread_R implements Runnable	{
	private String name;
	private Vector aVector;

	public Thread_R (String name) {
		this.name = name;
	}

	public void run () {
		System.out.println("Hi :) ... my name is: " + name );
	}

	public static void main (String args []) {
		String names[] = { "bono", "U2" };
		for ( int index = 0; index < names.length; index ++ )	{
			new Thread( new Thread_R( names[index] ) ).start();
		}
	}
}
