package co.edu.petlovers.dao;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;

public class Dao {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void Connet() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/petlovers", "petloversuser", "1234");
//            if (connection != null) {
//                System.out.println("Conexión a base de datos ... Ok");
//                connection.close();
//            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void Close() throws Exception {
        try {
            if (connection != null) {
                if (connection.isClosed() == false) {
                    connection.close();
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
