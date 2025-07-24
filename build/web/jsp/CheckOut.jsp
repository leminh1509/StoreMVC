<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Orders"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Update Order</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
        <form action="CheckOut" method="post">
            <table>
                <caption>Insert Order</caption>
                <tr>
                    <td>order_id</td>
                    <td><input type="text" name="order_id" id="" value=""/></td>
                </tr>
                <tr>
                    <td>customer_id</td>
                    <td><input type="text" name="customer_id" id="" value=""/></td>
                </tr>
                <tr>
                    <td>order_status</td>
                    <td><input type="text" name="status" id="" value=""/></td>
                </tr>
                <tr>
                    <td>order_date</td>
                    <td><input type="text" name="oDate" id="" value=""/></td>
                </tr>
                <tr>
                    <td>required_date</td>
                    <td><input type="text" name="rDate" id="" value=""/></td>
                </tr>
                <tr>
                    <td>shipped_date</td>
                    <td><input type="text" name="sDate" id="" value=""/></td>
                </tr>
                <tr>
                    <td>store_id</td>
                    <td><input type="text" name="store_id" id="" value=""/></td>
                </tr>
                <tr>
                    <td>staff_id</td>
                    <td><input type="text" name="staff_id" id="" value=""/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="Check Out" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                
            </table>
        </form>
        <p>Check out at <%=request.getAttribute("order_id")%> </p>
    </body>
</html>

