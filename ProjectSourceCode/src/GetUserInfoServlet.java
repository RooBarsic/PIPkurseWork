import myClasses.logic.UserInfoGetter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("//getUserMail")
public class GetUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = request.getParameter("login");
        Cookie[] cookies = request.getCookies();

        for(Cookie cook : cookies){
            System.out.println(" cookie : name = " + cook.getName() + " val = " + cook.getValue() );
            if(cook.getName().equals("access")){
                userLogin = cook.getValue();
                break;
            }
        }
        String responseString = "{email:" + UserInfoGetter.getUserEmail(userLogin) + "}";
        response.getWriter().print(responseString);
        response.getWriter().flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
