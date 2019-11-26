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
    <title> Игра </title>
    <link href="../styles/style3.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="./js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="./js/cosmo.js"></script>
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
            <main align="center">
                <div id="content" align="center">
                    <h2> Игра </h2>
                    <table class="contentTable">
                        <tr> <td class="contentTableTd">
                            <a href="./games/Episode1Task2.jsp" class="linkingText"> Эпизод I, новые горизонты. </a>
                        </td> </tr>
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
