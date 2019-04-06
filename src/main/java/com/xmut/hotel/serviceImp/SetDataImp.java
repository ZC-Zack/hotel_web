package com.xmut.hotel.serviceImp;

import com.xmut.hotel.dao.jdbc.ConnectionControl;
import com.xmut.hotel.service.SetData;
import net.sf.json.JSONObject;

import java.sql.SQLException;


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
}
