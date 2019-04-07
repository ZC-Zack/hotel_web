package com.xmut.hotel.service;

import com.xmut.hotel.information.*;
import net.sf.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

public interface GetDataList {
    public List<Room> getRoomList();
    public List<User> getUserList();
    public void getOrderList();
    public List<Apply> getApplyList();
    public List<Friend> getFriendList();
    public List<Msg> getChatList(JSONObject json);
}
