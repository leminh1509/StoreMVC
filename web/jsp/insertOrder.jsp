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
        
        <form action="OrderControllerURL" method="post">
            <table>
                <caption>Update Order</caption>
                <tr>
                    <td>order_id</td>
                    <td><input type="text" name="order_id" id="" /></td>
                </tr>
                <tr>
                    <td>customer_id</td>
                    <td><input type="text" name="customer_id" id="" /></td>
                </tr>
                <tr>
                    <td>order_status</td>
                    <td><input type="text" name="status" id="" /></td>
                </tr>
                <tr>
                    <td>order_date</td>
                    <td><input type="text" name="oDate" id="" /></td>
                </tr>
                <tr>
                    <td>required_date</td>
                    <td><input type="text" name="rDate" id="" /></td>
                </tr>
                <tr>
                    <td>shipped_date</td>
                    <td><input type="text" name="sDate" id="" /></td>
                </tr>
                <tr>
                    <td>store_id</td>
                    <td><input type="text" name="store_id" id="" /></td>
                </tr>
                <tr>
                    <td>staff_id</td>
                    <td><input type="text" name="staff_id" id="" /></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="insert Orders" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                <input type="hidden" name="service" value="insert" />
            </table>
        </form>
    </body>
</html>

