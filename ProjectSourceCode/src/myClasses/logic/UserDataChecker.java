package myClasses.logic;

import myClasses.MyLog;
import myClasses.User;

public class UserDataChecker {
    public static boolean isUserWithLogin(String userLogin){
        if((userLogin == null) || (userLogin.equals(""))) return false;
        for(User user : MyLog.getUsers()){
            if(user.getLogin().equals(userLogin)){
                return true;
            }
        }
        return false;
    }

    public static boolean isUserWithEmail(String userEmail){
        if((userEmail == null) || (userEmail.equals(""))) return false;
        for(User user : MyLog.getUsers()){
            if(user.getEmail().equals(userEmail)){
                return true;
            }
        }
        return false;
    }
}
