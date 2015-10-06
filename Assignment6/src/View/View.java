package View;

import java.util.Scanner;

public class View {
public void showOutput(Object s)
{
	System.out.println(s);
	
	}
public int userInput()
{
	int userInput;
	Scanner sc = new Scanner(System.in);
	userInput = sc.nextInt();
	
   return userInput;
	
	}
	
	
}
