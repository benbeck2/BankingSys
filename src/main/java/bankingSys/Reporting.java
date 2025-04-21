package bankingSys;

import java.util.Scanner;
import bankingSys.DatabaseTest;


public class Reporting {
	
	public static void reportingMenu(String accountId, String customerId ) {
		System.out.println("Hi, please select the Report you would like to view");
		System.out.println();
		int option=0;
		

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
				DatabaseTest.printQueryResults(DatabaseTest.RunRead("SELECT * FROM account WHERE customer_id = "+ customerId));;
				break;
			case 3:
				DatabaseTest.printQueryResults(DatabaseTest.RunRead("SELECT * FROM transaction WHERE account_id = "+ accountId +" ORDER BY transaction_id DESC"));;
				break;
			default:
				System.out.println("Invalid option. Please enter a valid option.");
			}
		}
	}

