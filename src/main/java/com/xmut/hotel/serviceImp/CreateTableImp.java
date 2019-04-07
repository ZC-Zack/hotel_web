package com.xmut.hotel.serviceImp;

import com.xmut.hotel.dao.jdbc.ConnectionControl;
import com.xmut.hotel.service.CreateTable;
import net.sf.json.JSONObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTableImp implements CreateTable {

    private ConnectionControl control;
    private PreparedStatement preparedStatement;

    public CreateTableImp() {
        try {
            control = new ConnectionControl();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUserTable(JSONObject json) {
        String name = (String) json.get("userId");
        control.createTable(name);
    }
}
