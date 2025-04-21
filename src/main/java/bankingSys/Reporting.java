package bankingSys;

import java.util.Scanner;
import bankingSys.DatabaseTest;


public class Reporting {
	
	public static void reportingMenu(String accountId, String customerId ) {
		System.out.println("Hi, please select the Report you would like to view");
		System.out.println();
		int option=0;
		
		do {
			System.out.println("Choose an option.");
			System.out.println("1 - Previous Transaction");
			System.out.println("2 - My Accounts");
			System.out.println("3 - Transaction history");
			
			Scanner sc = new Scanner(System.in);
			option = sc.nextInt();
			
			switch(option) {
			case 1:
				DatabaseTest.printQueryResults(DatabaseTest.RunRead("SELECT * FROM transaction WHERE account_id = "+ accountId +" ORDER BY transaction_id DESC LIMIT 1"));;
				break;
			case 2:
				System.out.println("Please contact us at 07777777777 to get Set up");
				break;
			case 3:
				System.out.println("Exiting the application..");
				break;
			default:
				System.out.println("Invalid option. Please enter a valid option.");
			}
		}while(option!=5);
		
		System.out.println("Thank you for using our service!");
	}
}
