package myClasses.logic;

import myClasses.MyLog;
import myClasses.User;

public class UserInfoGetter {
    public static String getUserEmail(String userLogin){
        for(User user : MyLog.getUsers()){
            if(user.getLogin().equals(userLogin)){
                return user.getEmail();
            }
        }
        return "no_email";
    }
}
