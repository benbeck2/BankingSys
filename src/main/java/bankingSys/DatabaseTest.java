/**
 * 
 */
package bankingSys;

/**
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.*;
import java.util.*;

public class DatabaseTest {

	/**
	 * 
	 */
	public static void RunCUD(String insertSQL) {
        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement()) {
               
               int rowsAffected = statement.executeUpdate(insertSQL);
               if (rowsAffected > 0) {
                   System.out.println("Data inserted/updated successfully.");
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
	
	public static List<Map<String, String>> RunRead(String readSQL) {
		
		List<Map<String, String>> results = new ArrayList<>();
		
		try (Connection conn = DatabaseConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(readSQL)) {

	            ResultSetMetaData meta = rs.getMetaData();
	            int columnCount = meta.getColumnCount();

	            while (rs.next()) {
	                Map<String, String> row = new LinkedHashMap<>(); // LinkedHashMap keeps insertion order
	                for (int i = 1; i <= columnCount; i++) {
	                    String colName = meta.getColumnName(i);
	                    String colValue = rs.getString(i);
	                    row.put(colName, colValue);
	                }
	                results.add(row);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return results;
    }
	
	// ✅ Prints any query results returned by runReadToListMap
    public static void printQueryResults(List<Map<String, String>> results) {
        if (results.isEmpty()) {
            System.out.println("No data returned.");
            return;
        }

        for (Map<String, String> row : results) {
            for (Map.Entry<String, String> entry : row.entrySet()) {
                System.out.print(entry.getKey() + ": " + entry.getValue() + "  | ");
            }
            System.out.println(); // New line after each row
        }
    }
	
	/*public static void RunRead(String readSQL) {
		ResultSet output;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement pstmt = conn.prepareStatement((readSQL))) {
        	output = pstmt.executeQuery(readSQL);
        	

        	// Iterate through the result set and print all data
        	// Get ResultSet metadata to dynamically access columns
            ResultSetMetaData rsMetaData = output.getMetaData();
            int columnCount = rsMetaData.getColumnCount(); 
            
            while (output.next()) {
                // Print data for each column in the current row
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsMetaData.getColumnName(i);  // Get column name
                    String columnValue = output.getString(i);         // Get column value
                    System.out.print(columnName + ": " + columnValue + "  | ");
                }
                System.out.println();  // New line after each row
            } 
            
            System.out.println(output);
        } catch (SQLException e) {
        	output = null;
            e.printStackTrace();
        }
    }*/
}

