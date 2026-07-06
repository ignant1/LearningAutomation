package demo.project.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import demo.project.base.BaseClass;

public class DBConnectionManager {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/orangehrm";
	private static final String DB_Username = "root";
	private static final String DB_Password = "";
	public static final Logger logger = BaseClass.logger;
	
	public static Connection getDBConnection() {
		logger.info("Starting DB Connection...");
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_Username, DB_Password);
			logger.info("DB Connection is Successful!");
			return connection;
		} catch (SQLException e) {
			logger.error("Failed to connect to database at: " + DB_URL);
			logger.error("Error Message: " + e.getMessage());
			return null;
		}

	}
	
	public static Map<String,String> getEmployDetails(String employee_id){
		String query = "SELECT emp_firstname, emp_middle_name, emp_lastname FROM hs_hr_employee WHERE employee_id = " + employee_id;
		
		Map<String,String> employeeDetails = new HashMap<>();
		
		try(
				Connection connect = getDBConnection();
				Statement stmt = connect.createStatement();
				ResultSet rs = stmt.executeQuery(query);){
			logger.info("Executing the following query:");
			logger.info(query);
			if(rs.next()) {
				String firstName = rs.getString("emp_firstname").trim();
				String middleName = rs.getString("emp_middle_name").trim();
				String lastName = rs.getString("emp_lastname").trim();
				logger.info("Query executed successfully.");
				logger.info("Employee " + firstName + " " + lastName + " found.");
				
				//add to the map
				employeeDetails.put("emp_firstname", firstName);
				employeeDetails.put("emp_middle_name", middleName!=null? middleName:"");
				employeeDetails.put("emp_lastname", lastName);
				logger.info("Added employ info to the map object.");
				
			} else {
				logger.info("Employee not found.");
			}
		}catch(Exception e) {
			logger.error("Error will executing query");
			logger.error("Error Message: " + e.getMessage());
		}
		
		return employeeDetails;
	}
}
