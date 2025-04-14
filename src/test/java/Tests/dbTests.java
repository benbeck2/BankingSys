package Tests;

import bankingSys.DatabaseTest;
import bankingSys.DatabaseConnection;


public class dbTests {

	public static void main(String[] args) {
		DatabaseTest.printQueryResults(DatabaseTest.RunRead("SELECT * FROM customer"));
		//DatabaseTest.RunCUD("INSERT INTO customer (customer_name) VALUES ('TEST')");
		//DatabaseTest.RunCUD("DELETE FROM customer WHERE customer_name = 'TEST'");
	}

}
