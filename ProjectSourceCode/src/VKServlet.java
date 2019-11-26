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

@WebServlet("/VKServlet")
public class VKServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataStorage dataStorage = new DataStorage();
        ConnectedUser connectedUser = new ConnectedUser();

        System.out.println("  \n\n\n --- I got POST message from VK ----------- ");
        String userToken = request.getParameter("token");
        String userVkId = request.getParameter("userVkId");
        System.out.println(" \n token = " + userToken +
                "\n user_vk_id = " + userVkId + "\n");

        if((userToken != null) && (userVkId != null) && (!userToken.equals(""))){
            User user = dataStorage.findOrAddUserFromVk(userToken, userVkId);
            connectedUser.addUser(user);

            if(user != null) {
                Cookie accessCookie = new Cookie("access", user.getLogin());
                accessCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(accessCookie);

                Cookie userIdCookie = new Cookie("userId", user.getStringUserId());
                userIdCookie.setMaxAge(60 * 60 * 24);
                response.addCookie(userIdCookie);

                response.sendRedirect("./project/news.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
