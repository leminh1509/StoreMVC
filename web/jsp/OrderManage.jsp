<%-- 
    Document   : OrderManage
    Created on : Feb 17, 2024, 11:39:32 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Orders"%>

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
                <th>order_id</th>
                <th>customer_id</th>
                <th>order_status</th>
                <th>order_date</th>
                <th>required_date</th>
                <th>shipped_date</th>
                <th>store_id</th>
                <th>staff_id</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%
                Vector<Orders> vector = (Vector<Orders>)request.getAttribute("data");
                for(Orders o : vector) {
            %>
            <tr>
                <td><%=o.getOrder_id()%></td>
                <td><%=o.getCustomer_id()%></td>
                <td><%=o.getOrder_status()%></td>
                <td><%=o.getOrder_date()%></td>
                <td><%=o.getRequired_date()%></td>
                <td><%=o.getShipped_date()%></td>
                <td><%=o.getStore_id()%></td>
                <td><%=o.getStaff_id()%></td>
                <td><a href="OrderControllerURL?service=update&id=<%=o.getOrder_id()%>">update</a></td>
                <td><a href="OrderControllerURL?service=delete&id=<%=o.getOrder_id()%>">delete</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
