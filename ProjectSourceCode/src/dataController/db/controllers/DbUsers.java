package dataController.db.controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DbUsers {
    private String TABLE_NAME = "users";
    DbUsers(){

    }

    public void initTable(Connection connection){
        String s;
        //s.length();
        Map<Integer, Integer> hm = new HashMap<>();
        hm.getOrDefault(4,5);
        int[] arr = new int[234];
        Map< Integer, Integer > sumCount = new HashMap<>();
        sumCount.put(0, 1);
       // int ans = sumCount.getOrDefault(dp[0][0] - target, 0);
        Map<String, String>[] ddd = new Map[45];
        ddd[0] = new HashMap<>();
        try (Statement statement = connection.createStatement()){
            statement.execute("create table users (" +
                    "id int primary key," +
                    "position int," +
                    "login varchar(256)," +
                    "password varchar(256)," +
                    "email varchar(256)," +
                    "score int NOT NULL DEFAULT 0," +
                    "token varchar(256)," +
                    "name varchar(256)," +
                    "surName varchar(256)," +
                    "country int references countries(id)," +
                    "userVkId varchar(256)," +
                    "permissions int references permissions(id));"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
