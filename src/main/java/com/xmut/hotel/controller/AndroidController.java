package com.xmut.hotel.controller;

import com.xmut.hotel.information.Apply;
import com.xmut.hotel.information.Friend;
import com.xmut.hotel.information.Room;
import com.xmut.hotel.information.User;
import com.xmut.hotel.meger.MergeApply;
import com.xmut.hotel.service.CreateTable;
import com.xmut.hotel.service.GetDataJSONArray;
import com.xmut.hotel.service.GetDataList;
import com.xmut.hotel.service.SetData;
import com.xmut.hotel.serviceImp.CreateTableImp;
import com.xmut.hotel.serviceImp.GetDataJSONArrayImp;
import com.xmut.hotel.serviceImp.GetDataListImp;
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
    private GetDataList dataList;
    private MergeApply mergeApply;
    private CreateTable createTable;

    public AndroidController(){
        getDataJSONArray = new GetDataJSONArrayImp();
        mergeApply = new MergeApply();
        createTable = new CreateTableImp();
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

    @RequestMapping(value = "/getApply", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getApply(@RequestBody JSONObject jsonObject){

        String userId = jsonObject.getString("userId");
        dataList = new GetDataListImp();
        List<User> userList = dataList.getUserList();
        List<Apply> applyList = dataList.getApplyList();
        List<User> users = mergeApply.getApply(userList, applyList, userId);
        jsonArray = JSONArray.fromObject(users);
        return jsonArray;
    }

    @RequestMapping(value = "/friend", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getFriend(@RequestBody JSONObject jsonObject){

        String userId = jsonObject.getString("userId");
        dataList = new GetDataListImp();
        List<User> userList = dataList.getUserList();
        List<Friend> friendList = dataList.getFriendList();
        List<User> users = mergeApply.getFriend(userList, friendList, userId);
        jsonArray = JSONArray.fromObject(users);
        //System.out.println("json:"+JSONArray.fromObject(jsonArray).toString());
        return jsonArray;
    }


    @RequestMapping(value = "/applyResult", method = RequestMethod.POST)
    @ResponseBody
    public int setApplyResult(@RequestBody JSONObject jsonObject){
        setData = new SetDataImp();
        return setData.setApplyResult(jsonObject);
        //return 0;
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    @ResponseBody
    public void setContent(@RequestBody JSONObject jsonObject){
        setData = new SetDataImp();
        setData.setContent(jsonObject);
    }

    @RequestMapping(value = "/getContent", method = RequestMethod.POST)
    @ResponseBody
    public JSONArray getContent(@RequestBody JSONObject jsonObject){
        jsonArray = getDataJSONArray.getContentJSONArray(jsonObject);
        return jsonArray;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ResponseBody
    public JSONArray getUser(){
        jsonArray = getDataJSONArray.getUserJSONArray();
        return jsonArray;
    }

    @RequestMapping(value = "/setUser", method = RequestMethod.POST)
    @ResponseBody
    public int setUser(@RequestBody JSONObject json){
        setData = new SetDataImp();
        createTable.createUserTable(json);
        return setData.setUser(json);
    }
}
