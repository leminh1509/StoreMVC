<%-- 
    Document   : StockManage
    Created on : Feb 18, 2024, 10:26:21 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Stocks"%>

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
                <th>product_id</th>
                <th>quantity</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%
                Vector<Stocks> vector = (Vector<Stocks>)request.getAttribute("data");
                for(Stocks stk : vector){
            %>
            <tr>
                <td><%=stk.getStore_id()%></td>
                <td><%=stk.getProduct_id()%></td>
                <td><%=stk.getQuantity()%></td>
                <td><a href="StockControllerURL?service=update&id1=<%=stk.getStore_id()%>&id2=<%=stk.getProduct_id()%>">update</a></td>
                <td><a href="StockControllerURL?service=delete&id1=<%=stk.getStore_id()%>&id2=<%=stk.getProduct_id()%>">delete</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
