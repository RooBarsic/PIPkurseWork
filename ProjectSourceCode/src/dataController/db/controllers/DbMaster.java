package dataController.db.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbMaster {
    private final String DB_USER_NAME = "farruxkarimov";
    private final String DB_USER_PASSWORD = "";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/pipkouserwork";

    private Connection getConnection(){
        try {
            return DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_USER_PASSWORD);
        } catch (SQLException e) {
            System.out.println(" Can't get connection!!! Error ::::: ");
            e.printStackTrace();
            return null;
        }
    }


}
