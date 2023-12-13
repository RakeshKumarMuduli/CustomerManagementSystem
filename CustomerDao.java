package com.dao_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model_Package.Customer;

public class CustomerDao {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/Internsala";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Rakesh@424";

    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO Customer" + "  (firstName,lastName,address,city,state,email,phone) VALUES " +
        " (?,?,?,?,?,?,?);";

   private static final String SELECT_CUSTOMER_BY_EMAIL = "select firstName,lastName,address,city,state,email,phone from Customer where email =?;";
    private static final String SELECT_ALL_CUSTOMER = "select * from Customer";
    private static final String DELETE_CUSTOMER_SQL = "delete from Customer where email =?";
    private static final String UPDATE_CUSTOMER_SQL = "update Customer set firstName = ?,lastName=?,address=?,city=?,state=?,phone=? where email = ?;";

    public CustomerDao()
    {
    	
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    //INSERT THE DATA INTO CUSTOMER TABLE.
    public void insertCustomer(Customer customer) throws SQLException {
        System.out.println(INSERT_CUSTOMER_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getCity());
            preparedStatement.setString(5, customer.getState());
            preparedStatement.setString(6, customer.getEmail());          
            preparedStatement.setLong(7, customer.getPhone());
            
            
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
           System.out.println(e);
        }
    }
    
    public boolean deleteCustomer(String email) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_SQL);) {
            statement.setString(1,email);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    
    public boolean updateCustomer(Customer customer) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); 
        		PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER_SQL);) {
           
        	statement.setString(1, customer.getFirstName());
        	statement.setString(2, customer.getLastName());
            statement.setString(3, customer.getAddress());
            statement.setString(4, customer.getCity());
            statement.setString(5, customer.getState());
            statement.setString(7, customer.getEmail());
            statement.setLong(6, customer.getPhone());
            
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    
    public Customer selectCustomer(String Email) {
        Customer customer = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_EMAIL);) {
            preparedStatement.setString(6,Email);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                
            	String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String address = rs.getString("address");
                String city	 = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                long phone = rs.getLong("phone");
                 customer=new Customer( firstName, lastName, address,  city,  state,  email, phone);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return customer;
    }

    public List < Customer > selectAllCustomer() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Customer > customer = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
           
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String address = rs.getString("address");
                String city	 = rs.getString("city");
                String state = rs.getString("state");
                String email = rs.getString("email");
                long phone = rs.getLong("phone");
                customer.add(new Customer( firstName, lastName, address,  city,  state,  email, phone));
            }
        } catch (SQLException e) {
            System.out.print(e);
        }
        return customer;
    }
}
