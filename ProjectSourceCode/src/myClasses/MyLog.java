package myClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MyLog {
    public static List< User> getUsers() {
        String fileName = "my_data4.txt";
        List< User> userInfo = new ArrayList<>();
        if(!(new File(fileName).exists())){
            try {
                File file1 = new File(fileName);
                System.out.println(" file " + fileName + " was not found.");
                System.out.println(" we try to create new file . Result " + file1.createNewFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()){
                String login = scanner.next();
                String password = scanner.next();
                String email = scanner.next();
                int score = scanner.nextInt();
                String token = scanner.next();
                String name = scanner.next();
                String surName = scanner.next();
                String country = scanner.next();
                String vkLogin = scanner.next();
                Permissions permission = Permissions.valueOf(scanner.next());
                userInfo.add(new User(login, password, email, score, token, name, surName, country, vkLogin, permission));
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println(" ################################ error in getUsersInfo ");
        }
        return userInfo;
    }

    public static void saveUsers(List<User> users){
        try (PrintWriter printWriter = new PrintWriter(new File("my_data4.txt"))) {
            for (User user : users) {
                user.printUser(printWriter);
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    public static void updateData(String login, int score){
        List<User> users = getUsers();
        for(User user : users){
            if(login.equals(user.getLogin())){
                user.setNewScore(score);
                break;
            }
        }
        saveUsers(users);
    }
}
