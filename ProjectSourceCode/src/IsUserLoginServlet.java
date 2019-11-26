import ejb.DataStorage;
import ejb.FindInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/isUserWith/login")
public class IsUserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FindInfo findInfo = new FindInfo();

        int cookiUserId = -1;
        for (Cookie cook : request.getCookies()) {
            if (cook.getName().equals("userId")) {
                cookiUserId = Integer.parseInt(cook.getValue());
            }
        }

        final String userLogin = request.getParameter("ulogin");
        System.out.println("\n --- Servlet /isUserWith/login login = " + userLogin + " --- \n");

        DataStorage dataStorage = new DataStorage();
        if(!dataStorage.validateLogin(userLogin)){
            response.getWriter().print(2);
            response.getWriter().flush();
        }
        else if(findInfo.findUserLogin(userLogin)){
            if((cookiUserId != -1) && (dataStorage.getUserById(cookiUserId).getLogin().equals(userLogin))){
                response.getWriter().print(0);
                response.getWriter().flush();
            }
            else {
                response.getWriter().print(1);
                response.getWriter().flush();
            }
        }
        else {
            response.getWriter().print(0);
            response.getWriter().flush();
        }
    }
}
