<%@ page import="java.util.List" %>
<%@ page import="ejb.DataStorage" %>
<%--
  Created by IntelliJ IDEA.
  myClasses.User: farrukh
  Date: 02.05.19
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title> Рейтинг </title>
    <link href="../styles/style3.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/vue.js"></script>
    <script type="text/javascript" src="../js/ScorePage.js"></script>
</head>
<body>
    <table id="zoneTable">
        <tr id="zoneHat"> <td>
            <header id="hat">
                <br/><br/><br/><br/>
                <div align="right">
                    <%
                        Cookie[] cookies = request.getCookies();
                        String userlogin = "";
                        for(Cookie cook : cookies){
                            System.out.println(" cookie : name = " + cook.getName() + " val = " + cook.getValue() );
                            if(cook.getName().equals("access")){
                                userlogin = cook.getValue();
                                break;
                            }
                        }
                        if(userlogin.equals("")){
                            out.print("<a href=\"./enter.jsp\" class=\"linkingText\"> Войти </a> &nbsp;&nbsp; ");
                            out.print("<a href=\"./registration.jsp\" class=\"linkingText\"> Зарегистрироваться &nbsp; </a>");
                        }
                        else {
                            out.print("<a href=\"./profile.jsp?user=" + userlogin + "\" class=\"linkingText\"> " + userlogin + " </a> &nbsp;&nbsp; ");
                            out.print("<a href=\"../doLogOut\" class=\"linkingText\"> Выйти &nbsp; </a>");
                        }

                        String searchingLogin = request.getParameter("login");
                        if(searchingLogin == null) searchingLogin = "";
                        String searchingCountry = request.getParameter("country");
                        if(searchingCountry == null) searchingCountry = "";
                        String searchingScoreStr = request.getParameter("score");
                        if(searchingScoreStr == null) searchingScoreStr = "";
                    %>
                </div>
                <table id="hatTable">
                    <td class="hatTableTd"> <strong> <a href="./news.jsp" class="linkingText">
                        Новости
                    </a> </strong> </td>
                    <td class="hatTableTd"> <strong> <a href="./game.jsp" class="linkingText">
                        Игра
                    </a> </strong> </td>
                    <td class="hatTableTd"> <strong> <a href="./scores.jsp" class="linkingText">
                        Рейтинг
                    </a> </strong> </td>
                    <td class="hatTableTd"> <strong> <a href="./help.jsp" class="linkingText">
                        Помощь
                    </a> </strong> </td>
                </table>
            </header>
        </td> </tr>
        <tr id="zoneContent"> <td>
            <main align="center">
                <div id="content"  align="center">
                    <h2> Рейтинг </h2>
                    <table class="contentTable">
                        <tr>
                            <td class="contentTableTd">
                                <button id="findByCountry"> <h2>Найти</h2> </button>
                            </td>
                            <td class="contentTableTd">
                                <input type="text" id="searchingLogin" value="<%=searchingLogin%>">
                            </td>
                            <td class="contentTableTd">
                                <input type="text" id="searchingCountry" value="<%=searchingCountry%>">
                            </td>
                            <td class="contentTableTd">
                                <input type="text" id="searchingScore" value="<%=searchingScoreStr%>">
                            </td>
                        </tr>
                        <tr>
                            <td width="10%" class="contentTableTd"> <h2>№</h2> </td>
                            <td width="60%" class="contentTableTd"> <h2>Логин</h2> </td>
                            <td width="20%" class="contentTableTd"> <h2>Страна</h2> </td>
                            <td class="contentTableTd"> <h2>Рейтинг</h2> </td>
                        </tr>
                        <%
                            //List<UserInfo> usersScores = UsersScoreController.getUserScores();

                            DataStorage dataStorage = new DataStorage();
                            List<ejb.User> users = dataStorage.getUsers(searchingLogin, searchingCountry, searchingScoreStr);
                            for(int i = 0; i < users.size(); i++){
                                System.out.println("         JSP ::: id = " + i );
                                String result = "<tr>";
                                if((searchingCountry+searchingLogin+searchingScoreStr).equals("")) {
                                    result += "<td class=\"contentTableTd\">" + (i + 1) + "</td>";
                                }
                                else {
                                    result += "<td class=\"contentTableTd\">" + (i+1) + " ( " + users.get(i).getPosition() + " ) " + "</td>";
                                }
                                result += "<td class=\"contentTableTd\"><a class=\"linkingText\" href=\"./profile.jsp?user=" + users.get(i).getLogin() + "\"> " + users.get(i).getLogin() + " </a></td>";
                                result += "<td class=\"contentTableTd\">" + users.get(i).paramToView(users.get(i).getCountry()) + "</td>";
                                result += "<td class=\"contentTableTd\">" + users.get(i).getScore() + "</td>";
                                result += "</tr>";
                                out.println(result);
                            }
                        %>
                    </table>
                </div>

            </main>
        </td></tr>
        <tr id="zoneFooter"><td>
            <footer id="footer">
                <div align="center">
                    <br/>
                    <table class="footerTable">
                        <td> <h4>
                            Создатели Farrukh Karimov. <br/>
                            При поддержке Barsic.Enterprise.
                        </h4> </td>
                    </table>
                </div>
            </footer>
        </td></tr>
    </table>
</body>
</html>
