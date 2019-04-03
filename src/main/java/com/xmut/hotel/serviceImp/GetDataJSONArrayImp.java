package com.xmut.hotel.serviceImp;

import com.xmut.hotel.information.Room;
import com.xmut.hotel.service.GetDataJSONArray;
import com.xmut.hotel.service.GetDataList;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class GetDataJSONArrayImp implements GetDataJSONArray {
    private JSONArray jsonArray;
    private List<Room> roomList;
    private GetDataList getDataList;

    public GetDataJSONArrayImp(){
        getDataList = new GetDataListImp();
        roomList = new ArrayList<>();
    }

    public JSONArray getRoomJSONArray() {
        roomList = getDataList.getRoomList();
        jsonArray = JSONArray.fromObject(roomList);
        return jsonArray;
    }
}
