package myClasses;

public class UserInfo implements Comparable{
    public String login;
    public int score;

    UserInfo(String login, int score){
        this.score = score;
        this.login = login;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
