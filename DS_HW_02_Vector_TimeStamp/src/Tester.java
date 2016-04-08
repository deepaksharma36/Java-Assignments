import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;


public class Tester {
	public static void main(String[] args) throws RemoteException {
		Scanner sc = new Scanner(System.in);
		TransactionsLog log = new TransactionsLog();
		ArrayList<Process> processes = new ArrayList<Process>();
		processes.add(new Process(1,3,"2089"));
		processes.add(new Process(1,3,"2089"));
		processes.add(new Process(1,3,"2089"));
		int pChoice;
		int action;
		int amount;
		int processNumber;
		while(true)
		{
			System.out.println("Select a process 1-3");
			pChoice=sc.nextInt();
			System.out.println("Select an Action: Deposit(1) , send Money(2) or Withdrow(3)");
			action=sc.nextInt();
			switch (action) {
			case 1:
				System.out.println("How much money you wants to deposit");
				amount=sc.nextInt();
				processes.get(pChoice-1).deposit(amount,"testing");
				log.show();
				break;
			case 2:
				System.out.println("How much money you wants to send");
				amount=sc.nextInt();
				System.out.println("To whome you wants to send money, give process Id");
				processNumber=sc.nextInt();
				processes.get(pChoice-1).sendMoney(processNumber, amount);
				log.show();
				break;
			case 3:
				System.out.println("How much money you wants");
				amount=sc.nextInt();
				processes.get(pChoice-1).withdraw(amount,"Testing");
				log.show();
				break;				
			default:
				System.out.println("Invalid Choice");
				break;
			}
			
		}
	}
}
