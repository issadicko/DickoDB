package com.dickodb.dbconnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    private DBConnection(){

        String url = "jdbc:mysql://localhost/orm";
        String username = "root", password = "";

        try {

            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);

            System.out.println("Connection effective !!!");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    public static synchronized Connection getConnection(){
        if (connection == null) new DBConnection();

        return connection;
    }
}
