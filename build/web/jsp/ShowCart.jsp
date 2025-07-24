<%-- 
    Document   : ShowCart
    Created on : Feb 21, 2024, 10:49:55 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.ProductCart"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show cart</title>
    </head>
    <body>
        <table border="1">
            <caption>list of product</caption>
            <tr>
                <th>product_id</th>
                <th>product_name</th>
                <th>quantity</th>
                <th>list_price</th>
                <th>subtotal</th>
                <th>remove</th>
            </tr>
            <%  java.util.Enumeration em = session.getAttributeNames();
                    double grandTotal=0.0;
//        getkeys()

                    while(em.hasMoreElements()){
                    
                           String key= em.nextElement().toString(); //get key
                           if (!key.equals("username")){
                           ProductCart pro=(ProductCart)session.getAttribute(key); //get value      
                           double subtotal = pro.getQuantity() * pro.getList_price(); // Tính toán subtotal cho sản phẩm hiện tại
                           grandTotal += subtotal;
            %>
            <tr>
                <td><%=pro.getProduct_id()%></td>
                <td><%=pro.getProduct_name()%></td>
                <td><%=pro.getQuantity()%></td>
                <td><%=pro.getList_price()%></td>
                <td><%=pro.getQuantity()*pro.getList_price()%></td>
                <td><a href="Cart?service=remove&id=<%=pro.getProduct_id()%>">remove</a></td>
            </tr>
            <%}}%>
        </table>   
        <p><a href="Cart?service=removeAll">remove all</a> grand total:<%=grandTotal%></p>
        <p><a href="CheckOut">Check out (insert DB)</p>
    </body>
</html>
