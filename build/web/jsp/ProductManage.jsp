<%-- 
    Document   : ProductManage
    Created on : Jan 29, 2024, 8:54:02 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="java.util.Vector"%>
<%@page import="entity.Products"%>--%>

<%@page import="java.util.Vector, entity.Products"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=(String)request.getAttribute("pageTitle")%>
            <%=(String)session.getAttribute("username")%>
        </title>
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
        <p><a href="ProductControllerURL?service=insert">insert</a></p>
        <p><a href="Cart?service=showCart">show cart</a></p>
        <table>
            <caption><%=(String)request.getAttribute("tableTitle")%></caption>
            <tr>
                <th>product_id</th>
                <th>product_name</th>
                <th>model_year</th>
                <th>list_price</th>
                <th>brand_name</th>
                <th>category_name</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%
                Vector<Products> vector = (Vector<Products>)request.getAttribute("data");
                for(Products pro : vector) {
            %>
            <tr>
                <td><%=pro.getProduct_id()%></td>
                <td><%=pro.getProduct_name()%></td>
                <td><%=pro.getModel_year()%></td>
                <td><%=pro.getList_price()%></td>
                <td><%=pro.getBrand_name()%></td>
                <td><%=pro.getCategory_name()%></td>
                <td><a href="ProductControllerURL?service=update&id=<%=pro.getProduct_id()%>">update</a></td>
                <td><a href="ProductControllerURL?service=delete&id=<%=pro.getProduct_id()%>">delete</a></td>
                 <td><a href="Cart?service=add2cart&id=<%=pro.getProduct_id()%>"">add2Cart</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
