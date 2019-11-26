import ejb.DataStorage;
import ejb.NewsStorage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/doSaveResult")
public class ServletSaveResult extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataStorage dataStorage = new DataStorage();
        NewsStorage newsStorage = new NewsStorage();

        System.out.println("score = " + request.getParameter("score"));
        System.out.println(" #############33 bebebe ");
        Map mp = request.getParameterMap();
        mp.forEach((a,b)->{
            System.out.println(a + " ---- " + b);
        });
        Cookie[] cookies = request.getCookies();
        String login = null;
        int userId = -1;
        int score = 0;
        if(request.getParameter("score") != null) score = Integer.parseInt(request.getParameter("score"));
        if(cookies != null) {
            for (Cookie cook : cookies) {
                if (cook.getName().equals("score")) {
                    int cookScore = Integer.parseInt(cook.getValue());
                    if (score < cookScore) score = cookScore;
                }
                if (cook.getName().equals("access")) {
                    login = cook.getValue();
                }
                if (cook.getName().equals("userId")) {
                    userId = Integer.parseInt(cook.getValue());
                }
            }
        }

        // json web tooken
        // browser local storage
        if((login != null) && (userId != -1)){
            //MyLog.updateData(login, score);
            long mx = dataStorage.getMaxScore();
            if(mx < score){
                newsStorage.setNews(" <h3> Внимание всем!!! </h3> Разведчик " + "<a href=./profile.jsp?user=" + login + " class=\"linkingText\">" + login + "</a> " + "только-что побил рекорд!!!  <br/> Новый рекорд " + score);
            }
            dataStorage.saveUserScore(userId, score);
        }
        System.out.println(" login = " + login + " score = " + score);
        //response.sendRedirect("./project/game.jsp");


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
