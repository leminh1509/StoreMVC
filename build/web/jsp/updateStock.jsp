<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Stocks"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Update Stock</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%Stocks stk = (Stocks)request.getAttribute("stk");%>
        <form action="StockControllerURL" method="post">
            <table>
                <caption>Update Stock</caption>
                <tr>
                    <td>store_id</td>
                    <td><input type="text" name="store_id" id="" value="<%=stk.getStore_id()%>"/></td>
                </tr>
                <tr>
                    <td>product_id</td>
                    <td><input type="text" name="product_id" id="" value="<%=stk.getProduct_id()%>"/></td>
                </tr>
                <tr>
                    <td>quantity</td>
                    <td><input type="text" name="quantity" id="" value="<%=stk.getQuantity()%>"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="update stock" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                <input type="hidden" name="service" value="update" />
            </table>
        </form>
    </body>
</html>

