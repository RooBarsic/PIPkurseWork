package ejb;

import myClasses.Permissions;

import javax.ejb.Singleton;
import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Singleton
public class DataStorage {
    private String dataFileName = "my_data5.txt";
    public ArrayList<User> usersList = new ArrayList<User>();

    public DataStorage(){

        System.out.println("\n \n -------------- Start loading users info ----------------- \n ");

        if(!(new File(dataFileName).exists())){
            try {
                File file1 = new File(dataFileName);
                System.out.println(" file " + dataFileName + " was not found.");
                System.out.println(" we try to create new file . Result " + file1.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (Scanner scanner = new Scanner(new File(dataFileName))) {
            while (scanner.hasNext()){
                int userId = Integer.parseInt(scanner.nextLine());
                String login = scanner.nextLine();
                String password = scanner.nextLine();
                String email = scanner.nextLine();
                int score = Integer.parseInt(scanner.nextLine());
                String token = scanner.nextLine();
                String name = scanner.nextLine();
                String surName = scanner.nextLine();
                String country = scanner.nextLine();
                String vkLogin = scanner.nextLine();
                Permissions permission = Permissions.valueOf(scanner.nextLine());
                User user = new User(userId, login, password, email, score, token, name, surName, country, vkLogin, permission);
                user.printUser(new PrintWriter(System.out));
                usersList.add(user);
            }
            sortUsers(usersList);
            setUsersPositions();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(" ################################ error in getUsersInfo ");
        }
    }

    private void setUsersPositions(){
        int position = 0;
        for(User user : usersList){
            position++;
            user.setPosition(position);
        }
    }

    public void sortUsers(List<User> users){
        for(int i = 0; i < users.size(); i++){
            for(int j = 1; j < users.size(); j++){
                if(users.get(j - 1).getScore() < users.get(j).getScore()){
                    User user1 = users.get(j - 1);
                    User user2 = users.get(j);
                    users.set(j, user1);
                    users.set(j - 1, user2);
                }
            }
        }
    }

    public void saveUsers(){
        System.out.println(" \n ---------- Start Saving Data sz = " + usersList.size() + " ----------- \n ");
        try (PrintWriter printWriter = new PrintWriter(new File(dataFileName))) {
            for (User user : usersList) {
                user.printUser(printWriter);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public void updateData(String login, int score){
        for(User user : usersList){
            if(login.equals(user.getLogin())){
                user.setNewScore(score);
                break;
            }
        }
        saveUsers();
    }

    public int getNumberOfUsers(){
        return usersList.size();
    }

    public User addNewUser(String userLogin, String userPassword, String userEmail){
        int numberOfUsers = getNumberOfUsers() + 1;
        System.out.println(" \n ---- DataStorage Info ; numberOfUsers = " + numberOfUsers + " ------ \n");
        User user = new User(numberOfUsers, userLogin, userPassword, userEmail);
        usersList.add(user);
        saveUsers();
        return user;
    }

    public User addNewUser(String userToken, String userVkId){
        int numberOfUsers = getNumberOfUsers() + 1;
        User user = new User(numberOfUsers, userToken, userVkId);
        usersList.add(user);
        saveUsers();
        return user;
    }

    public void saveUserScore(int userId, int score){
        for(User user : usersList){
            if(user.getUserId() == userId){
                user.setNewScore(score);
            }
        }
        saveUsers();
    }

    public User findUserByVkId(String userVkId){
        for(User user : usersList){
            if(user.getUserVkId().equals(userVkId)){
                return user;
            }
        }
        return null;
    }

    public void setUserToken(int userId, String token){
        for(User user : usersList){
            if(user.getUserId() == userId){
                user.setToken(token);
                saveUsers();
                break;
            }
        }
    }

    public User findOrAddUserFromVk(String userToken, String userVkId){
        if((userToken == null) || (userVkId == null) || (userToken.equals(""))){
            return null;
        }
        User user = findUserByVkId(userVkId);
        if(user == null){
            user = addNewUser(userToken, userVkId);
            System.out.println(" \n ----- I add new user ---- \n ");
        }
        else {
            setUserToken(user.getUserId(), userToken);
        }
        return user;
    }

    public User getUserById(int userId){
        for(User user: usersList){
            if(user.getUserId() == userId){
                return user;
            }
        }
        return null;
    }

    /**
     * User - если такой логин найден, null - иначе
     * @param userLogin
     * @return
     */
    public User getUserByLogin(String userLogin){
        if((userLogin == null) || (userLogin.equals(""))) return null;
        for(User user : usersList){
            if(user.getLogin().equals(userLogin)){
                return user;
            }
        }
        return null;
    }

    public void saveUser(User user) {
        System.out.println(" \n ---- I'm saving user ----- \n" );
        if(user != null) {
            for (int i = 0; i < usersList.size(); i++) {
                if (usersList.get(i).getUserId() == user.getUserId()) {
                    usersList.set(i, user);
                    saveUsers();
                    break;
                }
            }
        }
    }

    /**
     * возвращет user - если этому логину соответствует этот пароль, null - иначе/если ошибка
     * @param login
     * @param password
     * @return
     */
    public User findUserWithLoginPassword(String login, String password){
        System.out.println("\n --- check user login password --- login = " + login + " password = " + password + " numberOfUsers = " + getNumberOfUsers()  + "  ----- \n" );
        if((login == null) || (password == null) || (login.equals("")) || (password.equals(""))){
            System.out.println(" \n ----------- result null2 \n");
            return null;
        }
        for(User user : usersList){
            if((user.getLogin().equals(login)) && (user.checkPassword(password))){
                System.out.println(" \n ----------------  result = \n"); user.printUser(new PrintWriter(System.out));
                return user;
            }
        }
        System.out.println(" result null1 ");
        return null;
    }

    public List<User> getUsers(){
        List<User> userScores = new ArrayList<User>(usersList);
        sortUsers(userScores);
        return userScores;
    }

    public List<User> getUsers(String loginExp, String countryExp, String maxScoreStr){
        System.out.println(" --- getUSers UUUUU ----------- \n");
        System.out.println(" loing = " + loginExp + " country = " + countryExp + " minScore = " + maxScoreStr + "\n");
        List<User> userScores = new ArrayList<User>(usersList);
        if(loginExp != null){
            StringBuilder buff = new StringBuilder();
            for(int i = 0; i < loginExp.length(); i++){
                if((('a' <= loginExp.charAt(i)) && (loginExp.charAt(i) <= 'z')) ||
                    (('A' <= loginExp.charAt(i)) && (loginExp.charAt(i) <= 'Z')) ||
                    (('0' <= loginExp.charAt(i)) && (loginExp.charAt(i) <= '9')) ||
                        (loginExp.charAt(i) == '_')) {
                        buff.append(loginExp.charAt(i));
                }
            }
            loginExp = buff.toString();

            if(!loginExp.equals("")){
                for(int i = userScores.size() - 1; i >= 0; i--){
                    System.out.println(" ::::::  id = " + i + " userScores.get(i).getLogin() = " + userScores.get(i).getLogin() );
                    if(!userScores.get(i).getLogin().contains(loginExp)){
                        userScores.remove(i);
                    }
                }
            }
        }
        if(countryExp != null){
            StringBuilder buff = new StringBuilder();
            for(int i = 0; i < countryExp.length(); i++){
                if((('a' <= countryExp.charAt(i)) && (countryExp.charAt(i) <= 'z')) ||
                    (('A' <= countryExp.charAt(i)) && (countryExp.charAt(i) <= 'Z')) ||
                    (('0' <= countryExp.charAt(i)) && (countryExp.charAt(i) <= '9')) ||
                    (('а' <= countryExp.charAt(i)) && (countryExp.charAt(i) <= 'я')) ||
                    (('А' <= countryExp.charAt(i)) && (countryExp.charAt(i) <= 'Я'))) {
                    buff.append(countryExp.charAt(i));
                }
            }
            countryExp = buff.toString();
            if(!countryExp.equals("")) {
                for (int i = userScores.size() - 1; i >= 0; i--) {
                    System.out.println(" ::::::  id = " + i + " userScores.get(i).getCountry() = " + userScores.get(i).getCountry());
                    if (!userScores.get(i).getCountry().contains(countryExp)) {
                        userScores.remove(i);
                    }
                }
            }
        }
        if(maxScoreStr != null){
            StringBuilder buff = new StringBuilder();
            for(int i = 0; i < maxScoreStr.length(); i++){
                if(('0' <= maxScoreStr.charAt(i)) && (maxScoreStr.charAt(i) <= '9')) {
                    buff.append(maxScoreStr.charAt(i));
                }
            }
            maxScoreStr = buff.toString();
            if(!maxScoreStr.equals("")) {
                try {
                    int maxScore = Integer.parseInt(maxScoreStr);
                    for (int i = userScores.size() - 1; i >= 0; i--) {
                        System.out.println(" ::::::  id = " + i + " userScores.get(i).getScore() = " + userScores.get(i).getScore());
                        if (userScores.get(i).getScore() > maxScore) {
                            userScores.remove(i);
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        sortUsers(userScores);
        System.out.println(" result size = " + userScores.size());
        return userScores;
    }

    public long getMaxScore(){
        long mx = 0;
        for(User user : usersList){
            if(mx < user.getScore()){
                mx = user.getScore();
            }
        }
        return mx;
    }

    public void deleteUSer(User deletingUser){
        for(int i = 0; i < usersList.size(); i++){
            if(usersList.get(i).getUserId() == deletingUser.getUserId()){

                usersList.remove(i);
                break;
            }
        }
        saveUsers();
    }

    public void setPermissions(User admin, User user, Permissions permissions){
        if(admin.getPermission() == Permissions.ADMIN) {
            user.setPermission(permissions);
        }
        saveUsers();
    }

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
