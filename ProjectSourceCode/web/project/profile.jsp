<%@ page import="ejb.DataStorage" %>
<%@ page import="ejb.User" %>
<%@ page import="myClasses.Permissions" %>
<%@ page import="javax.xml.crypto.Data" %><%--
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
    <title> Testing main frame </title>
    <link href="../styles/style3.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/vue.js"></script>
    <script type="text/javascript" src="../js/Admin.js"></script>
</head>
<body>
    <table id="zoneTable">
        <tr id="zoneHat"> <td>
            <header id="hat">
                <br/><br/><br/><br/>
                <div align="right">
                    <%
                        DataStorage dataStorage = new DataStorage();
                        String paramUserLogin = request.getParameter("user");
                        User paramUser = dataStorage.getUserByLogin(paramUserLogin);
                        System.out.println(" ------ profiling --- paramUserLogin = " + paramUserLogin);
                        if(paramUser == null){
                            // если параметр задан неверно - перенаправляем клиента в раздел новости
                            //response.sendRedirect("./news.jsp");
                        }
                        Cookie[] cookies = request.getCookies();
                        int coockieUserId = -1;
                        for(Cookie cook : cookies){
                            System.out.println(" cookie : name = " + cook.getName() + " val = " + cook.getValue() );
                            if(cook.getName().equals("userId")){
                                coockieUserId = Integer.parseInt(cook.getValue());
                                break;
                            }
                        }
                        if(coockieUserId == -1){
                            out.print("<a href=\"./enter.jsp\" class=\"linkingText\"> Войти </a> &nbsp;&nbsp; ");
                            out.print("<a href=\"./registration.jsp\" class=\"linkingText\"> Зарегистрироваться &nbsp; </a>");
                        }
                        else {
                            User user = dataStorage.getUserById(coockieUserId);
                            out.print("<a href=\"./profile.jsp?user=" + user.getLogin() + "\" class=\"linkingText\"> " + user.getLogin() + " </a> &nbsp;&nbsp; ");
                            out.print("<a href=\"../doLogOut\" class=\"linkingText\"> Выйти &nbsp; </a>");
                        }
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
                <div id="content" align="center" style="opacity: 1">
                    <h3> Профиль <br/>
                        <table style="opacity: 0.75; background-color: darkturquoise">
                                    <%
                                        String tr = "<tr>";
                                        String td = "<td style=\"\">";
                                        if(paramUser == null){
                                            paramUser = dataStorage.getUserById(coockieUserId);
                                        }
                                        if(paramUser != null){
                                            out.print("<tr>" + td + "login : </td>" + td + paramUser.paramToView(paramUser.getLogin()) + "</td></tr>");
                                            out.print(tr + td + "Name : </td>" + td + paramUser.paramToView(paramUser.getName()) + "</td></tr>");
                                            out.print(tr + td + "Surname : </td>" + td + paramUser.paramToView(paramUser.getSurName()) + "</td></tr>");
                                            out.print(tr + td + "Email : </td>" + td + paramUser.paramToView(paramUser.getEmail()) + "</td></tr>");
                                            out.print(tr + "<td>" + "Score : </td>" + td + paramUser.getScore() + "</td></tr>");
                                            out.print(tr + td + "Country : </td>" + td + paramUser.paramToView(paramUser.getCountry()) + "</td></tr>");
                                            if((coockieUserId != -1) && (dataStorage.getUserById(coockieUserId).getPermission() == Permissions.ADMIN)){
                                                out.print(tr + td + "Status : </td>" + td + paramUser.paramToView(paramUser.getPermission()) + "</td></tr>");
                                            }
                                        }
                                    %>
                        </table>
                        <%
                            if((paramUser != null) && (coockieUserId != -1)){
                                if(paramUser.getUserId() == coockieUserId) {
                                    out.print("<input type=\"button\" value=\"Редактировать\" onClick='location.href=\"http://localhost:8080/JavaEEtest_war_exploded/project/EditProfile.jsp\"'>");
                                }
                                else {
                                    User cookieUser = dataStorage.getUserById(coockieUserId);
                                    if(cookieUser.getPermission() == Permissions.ADMIN){
                                        out.print("<button id=\"swaper\"> Поменять Права </button> <br/>");
                                        if(paramUser.getPermission() != Permissions.ADMIN) {
                                            out.print("<button id=\"kiker\"> Удалить Пользователя </button>");
                                        }
                                    }
                                }
                            }
                        %>

                    </h3>
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
