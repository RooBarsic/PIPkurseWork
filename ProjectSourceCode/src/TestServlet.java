import ejb.ConnectedUser;
import ejb.DataStorage;
import ejb.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doTesting")
public class TestServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataStorage dataStorage = new DataStorage();
        ConnectedUser connectedUser = new ConnectedUser();

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        System.out.println("compare 123 vs 321" + "123".compareTo("321"));
        System.out.println("compare 321 vs 123" + "321".compareTo("123"));
        System.out.println("compare 123 vs 123" + "123".compareTo("123"));

        User user = dataStorage.findUserWithLoginPassword(login, password);
        if(user != null){
            connectedUser.addUser(user);

            Cookie accessCookie = new Cookie("access", user.getLogin());
            accessCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(accessCookie);

            Cookie userIdCookie = new Cookie("userId", user.getStringUserId());
            userIdCookie.setMaxAge(60 * 60 * 24);
            response.addCookie(userIdCookie);

            response.sendRedirect("./project/news.jsp");

            System.out.println(" Access OK ");
        }
        else {
            response.sendRedirect("./project/enter.jsp");
            System.out.println(" Wrong user name or password");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
