import ejb.ConnectedUser;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doLogOut")
public class ServletLogOut extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectedUser connectedUser = new ConnectedUser();

        Cookie[] cookies = request.getCookies();
        Cookie logOutCookie = null;
        for(Cookie cook : cookies){
            if(cook.getName().equals("access")){
                logOutCookie = new Cookie("access", cook.getValue());
                break;
            }
        }

        Cookie userIdCookie = null;
        for(Cookie cook : cookies){
            if(cook.getName().equals("userId")){
                userIdCookie = new Cookie("userId", cook.getValue());
                break;
            }
        }
        if(logOutCookie != null){
            System.out.println(" \n ---- Servlet log out   user login = " + logOutCookie.getValue() + " --- \n");
            logOutCookie.setMaxAge(0);
            response.addCookie(logOutCookie);
            connectedUser.addUser(null);
        }
        if(userIdCookie != null){
            System.out.println(" \n ---- Servlet log out  usrId = " + userIdCookie.getValue() + " --- \n");
            userIdCookie.setMaxAge(0);
            response.addCookie(userIdCookie);
            connectedUser.addUser(null);
        }
        response.sendRedirect("./project/news.jsp");
    }
}
