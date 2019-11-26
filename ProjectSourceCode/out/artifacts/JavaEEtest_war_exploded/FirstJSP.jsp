<%@ page import="java.util.Set" %>
<%@ page import="java.util.LinkedHashSet" %><%--
  Created by IntelliJ IDEA.
  myClasses.User: farrukh
  Date: 04.04.19
  Time: 0:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My first JSP</title>
</head>
<body>
    <h1> Testing JSP </h1>
    <p>
        <%
            Set<Integer> c = new LinkedHashSet();
            c.add(2); c.add(5); c.add(4); c.add(1); c.add(5); c.add(1);
            for(int i : c){
                out.println(i);
            }
            java.util.Date nowDay = new java.util.Date();
            String someThing = "Текушая дата " + nowDay;

            out.println(" Smotri na eto) ");
        %>
        <%= someThing %> </p>
</body>
</html>
