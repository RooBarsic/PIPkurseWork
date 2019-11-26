package myClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserAccessController {
    /**
     * Return true if we have user with (login, password) = (userLogin, userPassword)
     * Retunr false - in any else cases
     *
     * @param userLogin
     * @param userPassword
     * @return
     */
    public static int checkUser(String userLogin, String userPassword) {
        List< User> usersInfo = MyLog.getUsers();
        for(User user : usersInfo){
            System.out.println(" Print users info : "); user.printUser(new PrintWriter(System.out));
            if(user.getLogin().equals(userLogin)){
                if(user.checkPassword(userPassword)) return 2; // Ok
                return 1; // wrong password
            }
        }
        System.out.println( " users done ");
        return 0; // wrong user name
    }

    /**
     * return 0 - if we has user whith long=userLogin
     * return 1 - if succes
     * return 2 - if any exeptions
     *
     * @param userLogin
     * @param userPassword
     * @param userEmail
     * @return
     */
    public static int addNewUser(String userLogin, String userPassword, String userEmail) {
        List< User> usersInfo = MyLog.getUsers();
        for(User user : usersInfo){
            if(user.getLogin().equals(userLogin)) return 0;
        }
        usersInfo.add(new User(userLogin, userPassword, userEmail, 0));
        MyLog.saveUsers(usersInfo);

        System.out.println("New user was added { long : " + userLogin + "; password : " + userPassword + "; email : " + userEmail + " }");
        System.out.println(" Total number of users = " + usersInfo.size());
        return 1;
    }

    public static int addNewUserFromVk(String userLogin, String userEmail, String userToken) {
        if(userLogin.equals("")) return 0;
        if(userEmail.equals("")) userEmail = "no_Email_registered_From_VK";
        List< User> usersInfo = MyLog.getUsers();
        for(User user : usersInfo){
            if(user.getLogin().equals(userLogin)) return 0;
        }
        usersInfo.add(new User(userLogin, "no_password", userEmail, 0, userToken, "", "", "", "", Permissions.USER));
        MyLog.saveUsers(usersInfo);

        return 1;
    }
}