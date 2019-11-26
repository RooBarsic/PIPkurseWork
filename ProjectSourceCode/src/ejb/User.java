package ejb;


import com.sun.istack.internal.Nullable;
import myClasses.Permissions;

import java.io.PrintWriter;
import java.util.Comparator;

public class User implements Comparable {
    private int userId;
    private int position;
    private String login;
    private String password;
    private String email;
    private int score;
    private String token;
    private String name;
    private String surName;
    private String country;
    private String userVkId;
    private Permissions permission;

    public Permissions getPermission() {
        return permission;
    }

    public void setPermission(Permissions permission) {
        this.permission = permission;
    }

    User(int userId, String token, String userVkId){
        this.userId = userId;
        this.login = "user_" + userId;
        this.password = "?";
        this.email = "?";
        this.score = 0;
        this.token = token;
        this.name = "?";
        this.surName = "?";
        this.country = "?";
        this.userVkId = userVkId;
        this.permission = Permissions.USER;
    }

    User(int userId, String login, String password, String email){
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.score = 0;
        this.token = "?";
        this.name = "?";
        this.surName = "?";
        this.country = "?";
        this.userVkId = "?";
        this.permission = Permissions.USER;

        printUser(new PrintWriter(System.out));
    }

    User(int userId, String login, String password, String email, int score){
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.score = score;
        this.token = "?";
        this.name = "?";
        this.surName = "?";
        this.country = "?";
        this.userVkId = "?";
        this.permission = Permissions.USER;
    }

    public User(int userId, String login, String password, String email, int score, String token, String name, String surName, String country, String userVkId, Permissions permission) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.email = email;
        this.score = score;
        this.token = token;
        this.name = name;
        this.surName = surName;
        this.country = country;
        this.userVkId = userVkId;
        this.permission = permission;
    }

    public void printUser(PrintWriter printWriter){
        String diff = "\n";
        printWriter.println(userId + diff + login + diff + password + diff + email + diff + score + diff + token + diff + name + diff + surName + diff
                + country + diff + userVkId + diff + permission);
        System.out.println("\n --- user info : " + userId + " " + login + " " + password + " " + email + " " + score + " " + token + " " + name + " " + surName + " "
                + country + " " + userVkId + " " + permission + " --- \n");
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login) { this.login = login; }

    public int getScore() { return score; }

    public void setNewScore(int score) {
        if(this.score < score) this.score = score;
    }

    public boolean checkPassword(String password){
        return (password.equals(this.password));
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public boolean checkToken(String token){
        return this.token.equals(token);
    }

    @Override
    public int compareTo(Object o) {
        myClasses.User t = (myClasses.User) o;
        if(t.getScore() > score) return 2;
        return -2;
    }

    public String getUserVkId() {
        return userVkId;
    }

    public void setUserVkId(String vkLogin) {
        this.userVkId = vkLogin;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getStringUserId() { return Integer.toString(userId); }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String paramToView(String param){
        if((param == null) || (param.equals("")) || (param.equals("?"))) return "Не указано";
        return param;
    }

    public String paramToView(Permissions param){
        if(param == Permissions.USER) return "USER";
        if(param == Permissions.ADMIN) return "ADMIN";
        return "Не указано";
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
