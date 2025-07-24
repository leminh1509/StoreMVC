<%-- 
    Document   : OrderItemManage
    Created on : Feb 18, 2024, 10:52:02 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.OrderItems"%>

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
                <th>item_id</th>
                <th>product_id</th>
                <th>quantity</th>
                <th>list_price</th>
                <th>discount</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%
                Vector<OrderItems> vector = (Vector<OrderItems>)request.getAttribute("data");
                for(OrderItems oi : vector){
            %>
            <tr>
                <td><%=oi.getOrder_id()%></td>
                <td><%=oi.getItem_id()%></td>
                <td><%=oi.getProduct_id()%></td>
                <td><%=oi.getQuantity()%></td>
                <td><%=oi.getList_price()%></td>
                <td><%=oi.getDiscount()%></td>
                <td><a href="OrderItemControllerURL?service=update&id1=<%=oi.getOrder_id()%>&id2=<%=oi.getItem_id()%>">update</a></td>
                <td><a href="OrderItemControllerURL?service=delete&id1=<%=oi.getOrder_id()%>&id2=<%=oi.getItem_id()%>">delete</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
