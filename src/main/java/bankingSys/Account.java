package bankingSys;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import bankingSys.DatabaseTest;
import bankingSys.Reporting;

public class Account {
	String customerName;
	String customerId;
	String accountId;
	String accountName;
	int balance;
	int previousTransaction;
	
	//constructor
	Account(String cName, String cId, String aId) {
		customerName = cName;
		customerId = cId;
		accountId = aId;
	}
	//methods
	
	/**
	 * @param amount
	 * This method adds the amount to the remaining balance
	 */
	public void deposit(int amount) {
		if(amount<=0) {
			System.out.println("Invalid amount. Please enter a valid amount.");
			return;
		}
		else {
			List<Map<String, String>> result = DatabaseTest.RunRead("SELECT balance FROM transaction WHERE account_id = " + accountId + " ORDER BY transaction_id DESC LIMIT 1");
			Map<String, String> row = result.get(0); // First (and only) row
			String balance = row.get("balance"); 
			DatabaseTest.RunCUD("INSERT INTO transaction (account_id, quantity, balance) VALUES (" + accountId + ", " + amount + ",  (" + balance +"+"+ amount + "))");
			previousTransaction=amount;
		}
	}
	
	/**
	 * @param amount
	 * This method subtracts the amount from the remaining balance
	 * It also checks if the amount mentioned is smaller than the balance or not
	 */
	public void withdraw(int amount) {
		List<Map<String, String>> result = DatabaseTest.RunRead("SELECT balance FROM transaction WHERE account_id = " + accountId + " ORDER BY transaction_id DESC LIMIT 1");
		Map<String, String> row = result.get(0); // First (and only) row
		String balance = row.get("balance"); 
		if(amount<=0) {
			System.out.println("Invalid amount. Please enter a valid amount.");
			return;
		}
		else if(amount>Float.parseFloat(balance)) {
			System.out.println("You do not have sufficient fund in your account.");
			return;
		}
		else {
			DatabaseTest.RunCUD("INSERT INTO transaction (account_id, quantity, balance) VALUES (" + accountId + ", -" + amount + ",  (" + balance +"-"+ amount + "))");
			previousTransaction=-amount;
		}
	}
	
	/**
	 * This method displays the remaining balance
	 */
	public void checkBalance() {
		System.out.println("Balance = "+balance);
		DatabaseTest.printQueryResults(DatabaseTest.RunRead("SELECT balance FROM transaction WHERE account_id = "+ accountId +" ORDER BY transaction_id DESC LIMIT 1"));
	}
	
	public void newAccount(String customerId, String accountName) {
		DatabaseTest.RunCUD("INSERT INTO Account (customer_id, account_name) VALUES (" + customerId +",'"+accountName+"');");
		DatabaseTest.RunCUD("INSERT INTO transaction (account_id, quantity, balance) VALUES (" + accountId + ", 0, 0);");
	}
	
	/**
	 * This method just shows the last transaction report
	 */
	public void showPreviousTransaction() {
		if(previousTransaction<0) {
			System.out.println("Withdraw : "+Math.abs(previousTransaction));
			
		}
		else if(previousTransaction>0){
			System.out.println("Deposit : "+previousTransaction);
		}
		else {
			System.out.println("No previous transaction found!");
		}
	}
	/**
	 * This method displays all the options to choose
	 */
	public void showMenu() {
		System.out.println("Hi "+ customerName + ", Welcome to Online Banking!");
		System.out.println("Your customer Id is : "+customerId);
		System.out.println();
		int option=0;
		
		do {
			System.out.println("Choose an option.");
			System.out.println("1 - Check Balance");
			System.out.println("2 - Deposit");
			System.out.println("3 - Withdraw");
			System.out.println("4 - Reporting");
			System.out.println("5 - New Account");
			System.out.println("6 - New Customer");
			System.out.println("7 - Exit");
			
			Scanner sc = new Scanner(System.in);
			option = sc.nextInt();
			
			switch(option) {
			case 1:
				checkBalance();
				break;
			case 2:
				System.out.println("Enter the amount to be deposited.");
				int amount = sc.nextInt();
				deposit(amount);
				break;
			case 3:
				System.out.println("Enter the amount to be withdrawn.");
				amount = sc.nextInt();
				withdraw(amount);
				break;
			case 4:
				Reporting.reportingMenu(accountId, customerId);
				break;
			case 5:
				System.out.println("Enter the new Account name.");
				sc.nextLine();
				accountName = sc.nextLine();
				newAccount(customerId, accountName);
				break;
			case 6:
				System.out.println("Please contact us at 07777777777 to get Set up");
				break;
			case 7:
				System.out.println("Exiting the application..");
				break;
			default:
				System.out.println("Invalid option. Please enter a valid option.");
			}
		}while(option!=5);
		
		System.out.println("Thank you for using our service!");
		
	}
	
	

}
