<%@ page import="ejb.DataStorage" %>
<%@ page import="ejb.User" %>
<%@ page import="myClasses.Permissions" %><%--
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
    <script type="text/javascript" src="../js/EditProfile.js"></script>
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
        <div id="app">
        <main align="center">
            <div id="content" align="center" style="opacity: 1">
                <h3> Профиль <br/>
                    <table style="opacity: 0.75; background-color: darkturquoise">
                        <%
                            DataStorage dataStorage = new DataStorage();
                            int userId = -1;
                            for(Cookie cook : cookies){
                                if(cook.getName().equals("userId")){
                                    userId = Integer.parseInt(cook.getValue());
                                    break;
                                }
                            }
                            String tr = "<tr>";
                            String td = "<td style=\"\">";
                            if(userId != -1){
                                User user = dataStorage.getUserById(userId);
                                out.print("<tr>" + td + "login :" + " </td>" +
                                        td + " <input id=\"uLogin\" type=\"text\" name=\"login\" value=\""
                                        + user.paramToView(user.getLogin()) + "\"></td></tr>");
                                out.print("<tr>" + td + "Name :" + " </td>" +
                                        td + " <input id=\"uName\" type=\"text\" name=\"login\" value=\""
                                        + user.paramToView(user.getName()) + "\"></td></tr>");
                                out.print("<tr>" + td + "Surname :" + " </td>" +
                                        td + " <input id=\"uSurName\" type=\"text\" name=\"login\" value=\""
                                        + user.paramToView(user.getSurName()) + "\"></td></tr>");
                                out.print("<tr>" + td + "Email :" + " </td>" +
                                        td + " <input id=\"uEmail\" type=\"text\" name=\"login\" value=\""
                                        + user.paramToView(user.getEmail()) + "\"></td></tr>");
                                out.print("<tr>" + td + "Country :" + " </td>" +
                                        td + " <input id=\"uCountry\" type=\"text\" name=\"login\" value=\""
                                        + user.paramToView(user.getCountry()) + "\"></td></tr>");
                            }
                        %>
                    </table>
                    <button id="save"> Сохранить </button> <br/>
                    <h3 style="color:red"> {{ mainMessage }} </h3> <br/>
                </h3>
            </div>
        </main>
        </div>
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
