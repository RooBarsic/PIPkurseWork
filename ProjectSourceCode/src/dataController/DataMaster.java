package dataController;

import ejb.User;
import myClasses.Permissions;

import java.util.List;

public abstract class DataMaster {

    public abstract void saveData();

    public abstract int getNumberOfUsers();

    public abstract int addNewUser(String userLogin, String userPassword, String userEmail);

    public abstract int addNewUser(String userToken, String userVkId);

    public abstract void newUserScore(int userId, int newScore);

    public abstract User getUserByVkId(String userVkId);

    public abstract void setUserToken(int userId, String token);

    public abstract User findOrAddUserFromVk(String userToken, String userVkId);

    public abstract User getUserById(int userId);

    public abstract User getUserByLogin(String userLogin);

    public abstract void saveUser(User user);

    public abstract User findUserWithLoginPassword(String login, String password);

    public abstract List<User> getUsers(String loginExp, String countryExp, String maxScoreStr);

    public abstract long getMaxScore();

    public abstract void deleteUSer(User deletingUser);

    public abstract void setPermissions(User admin, User user, Permissions permissions);

    public boolean validateLogin(String login){
        if(login == null) return false;
        if(login.equals("")) return false;
        for(int i = 0; i < login.length(); i++){
            if(!((('a' <= login.charAt(i)) && (login.charAt(i) <= 'z')) ||
                    (('A' <= login.charAt(i)) && (login.charAt(i) <= 'Z')) ||
                    (('0' <= login.charAt(i)) && (login.charAt(i) <= '9')) ||
                    (login.charAt(i) == '_'))){
                return false;
            }
        }
        return true;
    }


    public boolean validateEmail(String email){
        if(email == null) return false;
        if(email.equals("")) return false;
        boolean status = false;
        for(int i = 0; i < email.length(); i++){
            if(!((('a' <= email.charAt(i)) && (email.charAt(i) <= 'z')) ||
                    (('A' <= email.charAt(i)) && (email.charAt(i) <= 'Z')) ||
                    (('0' <= email.charAt(i)) && (email.charAt(i) <= '9')) ||
                    (email.charAt(i) == '_') || (email.charAt(i) == '@') ||
                    (email.charAt(i) == '.') || (email.charAt(i) == '-'))){
                return false;
            }
            if(email.charAt(i) == '@'){
                status = true;
            }
        }
        return status;
    }
}
