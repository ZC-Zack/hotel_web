package com.xmut.hotel.service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public interface GetDataJSONArray {

    public JSONArray getRoomJSONArray();
    public JSONArray getApplyJSONArray(String data);
    public JSONArray getContentJSONArray(JSONObject json);
    public JSONArray getUserJSONArray();
}
