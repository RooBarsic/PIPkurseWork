import ejb.DataStorage;
import ejb.FindInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doRegistration")
public class ServletRegistration extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataStorage dataStorage = new DataStorage();
        FindInfo findInfo = new FindInfo();

        String login = request.getParameter("ulogin");
        String password = request.getParameter("upassword");
        String email = request.getParameter("uemail");

        System.out.println(" \n ----------------- kukuku doRegistration ---------------- num = " + dataStorage.getNumberOfUsers() + "\n ");
        if(dataStorage == null){
            System.out.println(" \n ++++++++++++++++++ dataStorage == null \n");
        }

        if((login == null) || (password == null) || (email == null) ||
                (findInfo.findUserEmail(email)) || (findInfo.findUserLogin(login)) ||
                (!dataStorage.validateLogin(login)) || (!dataStorage.validateEmail(email))){
            response.getWriter().print("0");
            response.getWriter().flush();
            //response.sendRedirect("./project/registration.jsp");
            System.out.println(" Registration ERROR. New user wasn't :( added { long : " + login + "; password : " + password + "; email : " + email + " }");
        }
        else {
            dataStorage.addNewUser(login, password, email);
            System.out.println(" Registration OK. New user was added { long : " + login + "; password : " + password + "; email : " + email + " }");
            response.getWriter().print("1");
            response.getWriter().flush();
            //response.sendRedirect("./project/enter.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
