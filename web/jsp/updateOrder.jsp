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
        <%Orders o = (Orders)request.getAttribute("o");%>
        <form action="OrderControllerURL" method="post">
            <table>
                <caption>Update Order</caption>
                <tr>
                    <td>order_id</td>
                    <td><input type="text" name="order_id" id="" value="<%=o.getOrder_id()%>"/></td>
                </tr>
                <tr>
                    <td>customer_id</td>
                    <td><input type="text" name="customer_id" id="" value="<%=o.getCustomer_id()%>"/></td>
                </tr>
                <tr>
                    <td>order_status</td>
                    <td><input type="text" name="status" id="" value="<%=o.getOrder_status()%>"/></td>
                </tr>
                <tr>
                    <td>order_date</td>
                    <td><input type="text" name="oDate" id="" value="<%=o.getOrder_date()%>"/></td>
                </tr>
                <tr>
                    <td>required_date</td>
                    <td><input type="text" name="rDate" id="" value="<%=o.getRequired_date()%>"/></td>
                </tr>
                <tr>
                    <td>shipped_date</td>
                    <td><input type="text" name="sDate" id="" value="<%=o.getShipped_date()%>"/></td>
                </tr>
                <tr>
                    <td>store_id</td>
                    <td><input type="text" name="store_id" id="" value="<%=o.getStore_id()%>"/></td>
                </tr>
                <tr>
                    <td>staff_id</td>
                    <td><input type="text" name="staff_id" id="" value="<%=o.getStaff_id()%>"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="update Orders" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                <input type="hidden" name="service" value="update" />
            </table>
        </form>
    </body>
</html>

