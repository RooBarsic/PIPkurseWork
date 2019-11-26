package myClasses;

import java.io.PrintWriter;
import java.util.Comparator;

public class User implements Comparable {
    private String login;
    private String password;
    private String email;
    private int score;
    private String token;
    private String name;
    private String surName;
    private String country;
    private String vkLogin;
    private Permissions permission;

    public Permissions getPermission() {
        return permission;
    }

    public void setPermission(Permissions permission) {
        this.permission = permission;
    }

    User(String login, String token, String email){
        this.login = login;
        this.password = "?";
        this.email = email;
        this.score = 0;
        this.token = token;
        this.name = "?";
        this.surName = "?";
        this.country = "?";
        this.vkLogin = "?";
        this.permission = Permissions.USER;
    }

    User(String login, String password, String email, int score){
        this.login = login;
        this.password = password;
        this.email = email;
        this.score = score;
        this.token = "?";
        this.name = "?";
        this.surName = "?";
        this.country = "?";
        this.vkLogin = "?";
        this.permission = Permissions.USER;
    }

    public User(String login, String password, String email, int score, String token, String name, String surName, String country, String vkLogin, Permissions permission) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.score = score;
        this.token = token;
        this.name = name;
        this.surName = surName;
        this.country = country;
        this.vkLogin = vkLogin;
        this.permission = permission;
    }

    public void printUser(PrintWriter printWriter){
        printWriter.println(login + " " + password + " " + email + " " + score + " " + token + " " + name + " " + surName + " "
                + country + " " + vkLogin + " " + permission);
    }

    public String getLogin(){
        return login;
    }

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

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public boolean checkToken(String token){
        return this.token.equals(token);
    }

    @Override
    public int compareTo(Object o) {
        User t = (User) o;
        if(t.getScore() > score) return 2;
        return -2;
    }

    public String getVkLogin() {
        return vkLogin;
    }

    public void setVkLogin(String vkLogin) {
        this.vkLogin = vkLogin;
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
}
