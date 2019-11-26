import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import ejb.DataStorage;
import ejb.User;
import myClasses.Permissions;

@WebServlet("/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataStorage dataStorage = new DataStorage();

        String userLogin = request.getParameter("ulogin");
        User user = dataStorage.getUserByLogin(userLogin);

        int cookieAdminId = -1;
        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("userId")) {
                cookieAdminId = Integer.parseInt(cookie.getValue());
                break;
            }
        }
        System.out.println(" got request for deleting --- adm = " + cookieAdminId + " ");
        if((cookieAdminId != -1) && (user != null)){
            User adminUser = dataStorage.getUserById(cookieAdminId);
            if((adminUser.getPermission() == Permissions.ADMIN) && (user.getPermission() != Permissions.ADMIN)){
                dataStorage.deleteUSer(user);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
