<%-- 
    Document   : JSPSyntax
    Created on : Jan 29, 2024, 8:13:42 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--jsp script-->
        <%//code java here
        int MAX = 300; //local variable
        out.print("<h1>MAX="+MAX+"</h1>");
        %>
        <!--expression-->
        <h3 style="color:red"> MAX = <%=MAX%> </h3>
        <%
        for(int i = 10; i <= MAX; i += 10){%>
            <hr width="<%=i%>">
        <%}%>
        <!--declaration-->
        <%! int MIN = 0; //global variable%>
        <%!
            public int getMIN(){
                return MIN;
            }
        %>
        <h3 style="color:blue"> MAX = <%=getMIN()%> </h3>
        <h1>Hello World!</h1>
    </body>
</html>
