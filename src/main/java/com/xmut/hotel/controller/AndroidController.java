package com.xmut.hotel.controller;

import com.xmut.hotel.information.Room;
import com.xmut.hotel.service.GetDataJSONArray;
import com.xmut.hotel.service.SetData;
import com.xmut.hotel.serviceImp.GetDataJSONArrayImp;
import com.xmut.hotel.serviceImp.SetDataImp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class AndroidController {
    private List<Room> list;
    private JSONArray jsonArray;
    private GetDataJSONArray getDataJSONArray;
    private SetData setData;

    public AndroidController(){
        getDataJSONArray = new GetDataJSONArrayImp();
    }

    @RequestMapping(value = "/android",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> test(){
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("msg", "登录成功");
        list = new ArrayList<>();
        return hashMap;
    }

    @RequestMapping(value = "/room",method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getRoom(){
        jsonArray = getDataJSONArray.getRoomJSONArray();

        return  jsonArray;
    }

    @RequestMapping(value = "/apply",method = RequestMethod.POST)
    @ResponseBody
    public int setApply(@RequestBody JSONObject jsonObject){
        setData = new SetDataImp();
        return setData.setApply(jsonObject);
    }

}
