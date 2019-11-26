package myClasses;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UsersScoreController {
    public static List<UserInfo> getUserScores(){
        List<UserInfo> userScores = new ArrayList<>();
        for(User user : MyLog.getUsers()){
            userScores.add(new UserInfo(user.getLogin(), user.getScore()));
            System.out.println(" user = " + user.getLogin() + " score = " + user.getScore());
        }

        for(int i = 0; i < userScores.size(); i++){
            for(int j = 1; j < userScores.size(); j++){
                if(userScores.get(j - 1).score < userScores.get(j).score){
                    String login = userScores.get(j).login;
                    int score = userScores.get(j).score;
                    userScores.get(j).login = userScores.get(j - 1).login;
                    userScores.get(j).score = userScores.get(j - 1).score;
                    userScores.get(j - 1).login = login;
                    userScores.get(j - 1).score = score;
                }
            }
        }

        return userScores;
    }
}
