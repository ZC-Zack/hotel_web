package com.xmut.hotel.service;

import net.sf.json.JSONObject;

public interface SetData {
    public int setApply(JSONObject json);
    public int setApplyResult(JSONObject json);
    public int setContent(JSONObject json);
    public int setUser(JSONObject json);
}
