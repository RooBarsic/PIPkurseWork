package ejb;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;
import java.io.PrintWriter;

@Singleton
public class FindInfo {
    private DataStorage dataStorage;

    public FindInfo(){
        dataStorage = new DataStorage();
    }

    /**
     * true - если такой логин найден, false - иначе
     * @param userLogin
     * @return
     */
    public boolean findUserLogin(String userLogin){
        if((userLogin == null) || (userLogin.equals(""))) return false;
        for(User user : dataStorage.usersList){
            if(user.getLogin().equals(userLogin)){
                return true;
            }
        }
        return false;
    }

    /**
     * true - если такой email найден, false - иначе
     * @param userEmail
     * @return
     */
    public boolean findUserEmail(String userEmail){
        if((userEmail == null) || (userEmail.equals(""))) return false;
        for(User user : dataStorage.usersList){
            if(user.getEmail().equals(userEmail)){
                return true;
            }
        }
        return false;
    }

    /**
     * возвращет user - если этому логину соответствует этот пароль, null - иначе/если ошибка
     * @param login
     * @param password
     * @return
     */
    public User findUserWithLoginPassword(String login, String password){
        System.out.println("\n --- check user login password --- login = " + login + " password = " + password + " numberOfUsers = " + dataStorage.getNumberOfUsers()  + "  ----- \n" );
        if((login == null) || (password == null) || (login.equals("")) || (password.equals(""))){
            System.out.println(" \n ----------- result null2 \n");
            return null;
        }
        for(User user : dataStorage.usersList){
            if((user.getLogin().equals(login)) && (user.checkPassword(password))){
                System.out.println(" \n ----------------  result = \n"); user.printUser(new PrintWriter(System.out));
                return user;
            }
        }
        System.out.println(" result null1 ");
        return null;
    }
}
