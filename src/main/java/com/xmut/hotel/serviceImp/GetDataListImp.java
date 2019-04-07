package com.xmut.hotel.serviceImp;

import com.xmut.hotel.dao.jdbc.ConnectionControl;
import com.xmut.hotel.information.*;
import com.xmut.hotel.service.GetDataList;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDataListImp implements GetDataList {

    private ResultSet resultSet;
    private ConnectionControl connectionControl;
    private String sql = "SELECT * FROM ";

    public GetDataListImp(){
        try {
            connectionControl = new ConnectionControl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Room> getRoomList() {
        List<Room> list = new ArrayList<>();
        resultSet = connectionControl.getResultSet(sql + "room");
        try {
            while(resultSet.next()){
                Room room = new Room();
                room.setId(resultSet.getString("roomId"));
                room.setName(resultSet.getString("roomName"));
                room.setPrice(resultSet.getDouble("roomPrice"));
                list.add(room);
                //connectionControl.closeAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = new ArrayList<>();
        resultSet = connectionControl.getResultSet(sql + "user");
        try {
            while (resultSet.next()){
                User user = new User();
                user.setUserId(resultSet.getString("userId"));
                user.setUserName(resultSet.getString("userName"));
                user.setSex(resultSet.getString("sex"));
                userList.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void getOrderList() {

    }

    @Override
    public List<Apply> getApplyList() {
        List<Apply> applyList = new ArrayList<>();
        resultSet = connectionControl.getResultSet(sql +"apply");
        try {
            while(resultSet.next()){
                Apply apply = new Apply();
                apply.setFriendId(resultSet.getString("friendId"));
                apply.setUserId(resultSet.getString("userId"));
                apply.setResult(resultSet.getInt("result"));
                applyList.add(apply);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return applyList;
    }

    @Override
    public List<Friend> getFriendList() {
        List<Friend> friendList = new ArrayList<>();
        resultSet = connectionControl.getResultSet(sql + "friend");
        try {
            while(resultSet.next()){
               Friend friend = new Friend();
               friend.setFriendId(resultSet.getString("friendId"));
               friend.setUserId(resultSet.getString("userId"));
               friendList.add(friend);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return friendList;
    }

    @Override
    public List<Msg> getChatList(JSONObject json) {
        List<Msg> msgList = new ArrayList<>();
        String friendId = (String) json.get("friendId");
        resultSet = connectionControl.getResultSet(sql + "chat" + json.get("userId") + " WHERE friendId = " + friendId);
        System.out.println(resultSet);
        try {
            while(resultSet.next()){
                Msg msg = new Msg();
                msg.setContent(resultSet.getString("content"));
                msg.setType(resultSet.getInt("type"));
                msgList.add(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return msgList;
    }
}
