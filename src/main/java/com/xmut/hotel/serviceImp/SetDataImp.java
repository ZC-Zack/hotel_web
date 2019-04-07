package com.xmut.hotel.serviceImp;

import com.xmut.hotel.dao.jdbc.ConnectionControl;
import com.xmut.hotel.information.Apply;
import com.xmut.hotel.information.Msg;
import com.xmut.hotel.service.GetDataList;
import com.xmut.hotel.service.SetData;
import net.sf.json.JSONObject;

import java.sql.SQLException;
import java.util.List;


public class SetDataImp implements SetData {

    private ConnectionControl control;

    public SetDataImp(){
        try {
            control = new ConnectionControl();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int setApply(JSONObject json) {
        GetDataList getDataList = new GetDataListImp();
        List<Apply> list = getDataList.getApplyList();
        System.out.println("in");
        for(Apply apply: list){
            if(apply.getUserId().equals(json.getString("userId")) && apply.getFriendId().equals(json.getString("friendId")) && apply.getResult() == 0){
                return 0;
            }
        }
        return control.setApply(json);
    }

    @Override
    public int setApplyResult(JSONObject json) {
        int result = 0;
        control.setResult(json);
        if("1".equals(String.valueOf(json.getString("result")))){
            result = control.setNewFriend(json);
        }
        return result;
    }

    @Override
    public int setContent(JSONObject json) {
        int reuslt = 0;
        json.put("type", Msg.TYPE_SENT);
        control.setContent(json);
        String temp = json.getString("userId");
        json.put("userId", json.getString("friendId"));
        json.put("friendId", temp);
        json.put("type", Msg.TYPE_RECEIVED);
        control.setContent(json);
        return 0;
    }

    @Override
    public int setUser(JSONObject json) {
        int result = 0;
        result = control.setUser(json);
        return result;
    }

}
