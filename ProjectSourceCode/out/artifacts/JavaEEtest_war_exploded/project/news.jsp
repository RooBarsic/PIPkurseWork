<%@ page import="ejb.NewsStorage" %><%--
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
    <title> Новости </title>
    <link href="../styles/style3.css" rel="stylesheet" type="text/css"/>
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
                <div id="content">
                    <table class="contentTable">
                        <%
                            NewsStorage newsStorage = new NewsStorage();
                            for(String news : newsStorage.getNews()){
                                String row = "<tr> <td class=\"contentTableTd\">";
                                row += news;
                                row += "</td> </tr>";
                                out.print(row);
                            }
                        %>
                        <tr>
                            <td class="contentTableTd">
                                <br/>
                                <h3> Авторизация через Vk.com </h3>
                                Доброго времени суток, дорогие игроки. <br/>
                                Добавлена авторизация через Вконтакте. <br/>
                                Теперь вы можете создавать учётную запись - в один клик. <br/>
                            </td>
                        </tr>
                        <tr>
                            <td class="contentTableTd">
                                <br/>
                                <h3> Обновление элементов управления в игре </h3>
                                Доброго времени суток, дорогие игроки. <br/>
                                Добавлены новые возможности в интерфейсе игры. <br/>
                                Теперь вы можете приостанавливать игру, сохранять результаты, возобновлять остановленную игру, и сразу запускать новую игру при желании.
                            </td>
                        </tr>
                        <tr>
                            <td class="contentTableTd">
                                <h3> Предыстория </h3>
                                Вы разведчик Млечного Пути в далёком космосе. <br/>
                                Наши учёные Млечного Пути в ходе эксперимента случайно наткнулись на ранее неизвестный телепортатор. <br/>
                                При попытке воспользоваться им, мы обнаружили завораживающие красотой неизведанные просторы космоса. <br/>
                                К сожалению радовались мы не долго. Через несколько минут нашего пребывания в этом неизведанном мире, нас встретил дрон Жнецов. <br/> Мы еле отбились от него. <br/>
                                Солдат, твоя задача пролететь как можно дальше и уничтожать на пути всех дронов Жнецов, чтобы наши учёные-исследователи могли спокойно <br/> изучать новые терретории для их дольнейшей коллонизации. <br/>
                                К сожеланию в этом неизведанном космосе, ты будешь один, мы можем только поддерживать связь с бортовым компьютером твоего корабля. <br/>
                                Чтобы через новый портал на нас не обрушилась атака Жнецов, ты долже выполнить это задание и держаться до конца. <br/>
                                Успехов солдат.
                            </td>
                        </tr>
                        <tr>
                            <td class="contentTableTd">
                                <h3> Игра запущена </h3>
                                Доброго времени суток, дорогие посетители данного сайта. <br/>
                                Этот сайт был запушен чтобы вы могли тратить своё время на космическую стрелялку жанра Mass Effect. <br/>
                                Надеемся что игра вам понравится и вы поймёте что не надо делать в свободное время). <br/>
                                Всем удачной игры. Да прибудит с вами свобода.
                            </td>
                        </tr>
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
