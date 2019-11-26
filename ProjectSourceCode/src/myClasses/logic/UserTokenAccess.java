package myClasses.logic;

import myClasses.MyLog;
import myClasses.User;

public class UserTokenAccess {
    public static void setToken(String userLogin, String token){
        MyLog.getUsers().forEach(user -> {
            if(user.getLogin().equals(userLogin)){
                user.setToken(token);
            }
        });
    }

    public static String getUserToken(String userLogin){
        for(User user : MyLog.getUsers()){
            if(user.getLogin().equals(userLogin)){
                return user.getToken();
            }
        }
        return "";
    }
}
