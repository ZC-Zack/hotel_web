package com.xmut.hotel.serviceImp;

import com.xmut.hotel.dao.jdbc.ConnectionControl;
import com.xmut.hotel.information.Room;
import com.xmut.hotel.service.GetDataList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetDataListImp implements GetDataList {

    private ResultSet resultSet;
    private ConnectionControl connectionControl;

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
        resultSet = connectionControl.getResultSet("room");
        try {
            while(resultSet.next()){
                Room room = new Room();
                room.setId(resultSet.getString("roomId"));
                room.setName(resultSet.getString("roomName"));
                room.setPrice(resultSet.getDouble("roomPrice"));
                list.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void getUserList() {

    }

    @Override
    public void getOrderList() {

    }
}
