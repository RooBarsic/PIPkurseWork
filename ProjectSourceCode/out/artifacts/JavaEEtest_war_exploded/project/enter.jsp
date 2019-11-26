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
    <title> Вход </title>
    <link href="../styles/style3.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../js/EnterWithVk.js"></script>
</head>
<body>
    <table id="zoneTable">
        <tr id="zoneHat"> <td>
            <header id="hat">
                <br/><br/><br/><br/>
                <div align="right">
                    <strong>
                        <a href="./enter.jsp" class="linkingText" >  Войти  </a> &nbsp;&nbsp;
                        <a href="./registration.jsp" class="linkingText" > Зарегистрироваться &nbsp; </a>
                    </strong>
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
                    <h2> Войти </h2>
                    <form method="post" action="../doTesting">
                        <h3>
                            Логин : <input type="text" name="login"> <br/>
                            Пароль : <input type="password" name="password"> <br/>
                            <button type="send"> Войти </button>
                        </h3>
                    </form>
                    <button id="enterWithVk"> Войти через Vk </button>
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
