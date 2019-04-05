package com.xmut.hotel.dao.jdbc;

import net.sf.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionControl {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private String url="jdbc:mysql://localhost:3306/hotel";
    private String username = "root", password = "root";
    private String sqlInsert = "INSERT INTO apply (userId, friendId) VALUES (?, ?)";

    public ConnectionControl() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password);
    }

    public ResultSet getResultSet(String sql){
        System.out.println(sql);
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            return  resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet getResultSet(){
        return resultSet;
    }

    public int setApply(JSONObject json){
        int resulte = 0;
        try {
            preparedStatement = connection.prepareStatement(sqlInsert);
            preparedStatement.setString(1, String.valueOf(json.get("userId")));
            preparedStatement.setString(2, String.valueOf(json.get("friendId")));
            resulte = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resulte;
    }

}
