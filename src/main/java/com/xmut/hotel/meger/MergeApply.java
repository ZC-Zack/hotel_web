package com.xmut.hotel.meger;

import com.xmut.hotel.information.Apply;
import com.xmut.hotel.information.Friend;
import com.xmut.hotel.information.User;
import net.sf.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MergeApply {

    public List<User> getApply(List<User> userList, List<Apply> applyList, String friendId){
        List<User> list = new ArrayList<>();
        for(Apply apply: applyList){
            if(apply.getFriendId().equals(friendId)&&apply.getResult() == 0){
                String id = apply.getUserId();
                for (User user: userList){
                    if(user.getUserId().equals(id)){
                        list.add(user);
                    }
                }
            }
        }
        return list;
    }

    public List<User> getFriend(List<User> userList, List<Friend> friendList, String userId){
        List<User> list = new ArrayList<>();
        for(Friend friend: friendList){
            if(userId.equals(friend.getUserId())){
                for(User user: userList){
                    if(friend.getFriendId().equals(user.getUserId())){
                        list.add(user);
                    }
                }
            }
        }

        return list;
    }
}
