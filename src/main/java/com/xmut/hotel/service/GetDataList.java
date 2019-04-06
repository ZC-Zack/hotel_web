package com.xmut.hotel.service;

import com.xmut.hotel.information.Apply;
import com.xmut.hotel.information.Friend;
import com.xmut.hotel.information.Room;
import com.xmut.hotel.information.User;

import java.sql.SQLException;
import java.util.List;

public interface GetDataList {
    public List<Room> getRoomList();
    public List<User> getUserList();
    public void getOrderList();
    public List<Apply> getApplyList();
    public List<Friend> getFriendList();
}
