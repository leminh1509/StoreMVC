<%-- 
    Document   : StoreManage
    Created on : Feb 18, 2024, 2:17:20 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Stores"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=(String)request.getAttribute("pageTitle")%></title>
        <style>
            table,
            th,
            td {
                padding: 5px;
                border: 1px solid black;
                border-collapse: collapse;
                text-align: center;
            }
            a{
                text-decoration: none;
                color: #000;
            }
        </style>
    </head>
    <body>
        <table>
            <caption><%=(String)request.getAttribute("tableTitle")%></caption>
            <tr>
                <th>store_id</th>
                <th>store_name</th>
                <th>phone</th>
                <th>email</th>
                <th>street</th>
                <th>city</th>
                <th>state</th>
                <th>zip_code</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%
                Vector<Stores> vector = (Vector<Stores>)request.getAttribute("data");
                for(Stores str : vector){
            %>
            <tr>
                <td><%=str.getStore_id()%></td>
                <td><%=str.getStore_name()%></td>
                <td><%=str.getPhone()%></td>
                <td><%=str.getEmail()%></td>
                <td><%=str.getStreet()%></td>
                <td><%=str.getCity()%></td>
                <td><%=str.getState()%></td>
                <td><%=str.getZip_code()%></td>
                <td><a href="StoreControllerURL?service=update&id=<%=str.getStore_id()%>">update</a></td>
                <td><a href="StoreControllerURL?service=delete&id=<%=str.getStore_id()%>">delete</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
