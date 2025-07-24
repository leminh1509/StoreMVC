<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.OrderItems"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Update Order Item</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%OrderItems oi = (OrderItems)request.getAttribute("oi");%>
        <form action="OrderItemControllerURL" method="post">
            <table>
                <caption>Update Order Item</caption>
                <tr>
                    <td>order_id</td>
                    <td><input type="text" name="order_id" id="" value="<%=oi.getOrder_id()%>"/></td>
                </tr>
                <tr>
                    <td>item_id</td>
                    <td><input type="text" name="item_id" id="" value="<%=oi.getItem_id()%>"/></td>
                </tr>
                <tr>
                    <td>product_id</td>
                    <td><input type="text" name="product_id" id="" value="<%=oi.getProduct_id()%>"/></td>
                </tr>
                <tr>
                    <td>quantity</td>
                    <td><input type="text" name="quantity" id="" value="<%=oi.getQuantity()%>"/></td>
                </tr>
                <tr>
                    <td>list_price</td>
                    <td><input type="text" name="list_price" id="" value="<%=oi.getList_price()%>"/></td>
                </tr>
                <tr>
                    <td>discount</td>
                    <td><input type="text" name="discount" id="" value="<%=oi.getDiscount()%>"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="update orderList" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                <input type="hidden" name="service" value="update" />
            </table>
        </form>
    </body>
</html>

