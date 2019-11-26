import ejb.DataStorage;
import ejb.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editData")
public class EditDataServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataStorage dataStorage = new DataStorage();

        String userLogin = request.getParameter("uLogin");
        String userName = request.getParameter("uName");
        String userSurName = request.getParameter("uSurName");
        String userEmail = request.getParameter("uEmail");
        String userCountry = request.getParameter("uCountry");
        if((userLogin != null) && (userName != null) && (userSurName != null) && (userEmail != null) && (userCountry != null) &&
            (!userLogin.equals("")) && (!userName.equals("")) && (!userSurName.equals("")) && (!userEmail.equals("")) && (!userCountry.equals(""))) {

            int userId = -1;
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("userId")) {
                    userId = Integer.parseInt(cookie.getValue());
                    break;
                }
            }

            System.out.println(" \n ---- Saving request ------- \n");
            if ((dataStorage.getUserByLogin(userLogin) == null) || (dataStorage.getUserById(userId).getLogin().equals(userLogin))) {
                System.out.println(" \n ---- Saving request Accepted ------- \n");
                User user = dataStorage.getUserById(userId);
                user.setLogin(userLogin);
                user.setName(userName);
                user.setSurName(userSurName);
                user.setEmail(userEmail);
                user.setCountry(userCountry);
                dataStorage.saveUser(user);

                Cookie cookie = new Cookie("access", userLogin);
                cookie.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
