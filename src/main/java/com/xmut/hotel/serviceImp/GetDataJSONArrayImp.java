package com.xmut.hotel.serviceImp;

import com.xmut.hotel.information.Chat;
import com.xmut.hotel.information.Msg;
import com.xmut.hotel.information.Room;
import com.xmut.hotel.information.User;
import com.xmut.hotel.service.GetDataJSONArray;
import com.xmut.hotel.service.GetDataList;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetDataJSONArrayImp implements GetDataJSONArray {
    private JSONArray jsonArray;
    private List<Room> roomList;
    private List<User> userList;
    private GetDataList getDataList;
    private List<Chat> chatList;
    private List<Msg> msgList;

    public GetDataJSONArrayImp(){
        getDataList = new GetDataListImp();
    }

    public JSONArray getRoomJSONArray() {
        roomList = getDataList.getRoomList();
        jsonArray = JSONArray.fromObject(roomList);
        return jsonArray;
    }

    @Override
    public JSONArray getApplyJSONArray(String data) {
        userList = getDataList.getUserList();
        jsonArray = JSONArray.fromObject(roomList);
        return jsonArray;
    }

    @Override
    public JSONArray getContentJSONArray(JSONObject json) {
        msgList = getDataList.getChatList(json);
        jsonArray = JSONArray.fromObject(msgList);
        return jsonArray;
    }

}
