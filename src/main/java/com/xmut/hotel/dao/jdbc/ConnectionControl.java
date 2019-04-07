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
    private String sqlUpdate = "UPDATE apply SET result = ? WHERE userId = ? AND friendId = ?";
    private String sql = "SELECT * FROM ";
    private String sqlInsertFriend = "INSERT INTO friend (userId, friendId) VALUES(?, ?)";

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

    public int setApply(JSONObject json){
        int result = 0;
        try {
            preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setString(1, String.valueOf(json.get("userId")));
            preparedStatement.setString(2, String.valueOf(json.get("friendId")));
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int setResult(JSONObject json){
        int result = 0;
        result = Integer.parseInt(String.valueOf(json.get("result")));
        try {
            preparedStatement = connection.prepareStatement(sqlUpdate);
            preparedStatement.setInt(1, result);
            preparedStatement.setString(2, String.valueOf(json.get("userId")));
            preparedStatement.setString(3, String.valueOf(json.get("friendId")));
            result = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public int setNewFriend(JSONObject json){
        int result = 0;
        try{
            preparedStatement = connection.prepareStatement(sqlInsertFriend);
            preparedStatement.setString(1, String.valueOf(json.get("userId")));
            preparedStatement.setString(2, String.valueOf(json.get("friendId")));
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(sqlInsertFriend);
            preparedStatement.setString(1, String.valueOf(json.get("friendId")));
            preparedStatement.setString(2, String.valueOf(json.get("userId")));
            result = preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return result;
    }

    public int setContent(JSONObject json){
        int result = 0;
        String sql = "INSERT INTO ";
        String value = "(friendId, content, type) VALUES(?, ?, ?)";
        sql += "chat"+json.get("userId") + value;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(json.get("friendId")));
            preparedStatement.setString(2, String.valueOf(json.get("content")));
            preparedStatement.setInt(3, (Integer) json.get("type"));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

}
