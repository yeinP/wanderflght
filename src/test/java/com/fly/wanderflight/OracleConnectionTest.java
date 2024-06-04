package com.fly.sky.wanderflight_;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OracleConnectionTest {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "wfadmin"; // or "wfadmin" depending on your user
        String password = "oracle";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.OracleDriver");
            // Establish the connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to the Oracle database successfully!");

            // Create a statement
            statement = connection.createStatement();
            // Execute a query
            resultSet = statement.executeQuery("SELECT 1 FROM dual");
            // Process the result set
            if (resultSet.next()) {
                System.out.println("Query executed successfully, result: " + resultSet.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}