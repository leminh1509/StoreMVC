<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Customers"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Update Customer</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%Customers cust = (Customers)request.getAttribute("cust");%>
      
        <form action="CustomerControllerURL" method="post">
            <table>
                <caption>Update Customer</caption>
                <tr>
                    <td>customer_id</td>
                    <td><input type="text" name="cid" id="" value="<%=cust.getCustomer_id()%>"/></td>
                </tr>
                <tr>
                    <td>first_name</td>
                    <td><input type="text" name="fName" id="" value="<%=cust.getFirst_name()%>"/></td>
                </tr>
                <tr>
                    <td>last_name</td>
                    <td><input type="text" name="lName" id="" value="<%=cust.getLast_name()%>"/></td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td><input type="text" name="phone" id="" value="<%=cust.getPhone()%>"/></td>
                </tr>
                <tr>
                    <td>email</td>
                    <td><input type="text" name="email" id="" value="<%=cust.getEmail()%>"/></td>
                </tr>
                <tr>
                    <td>street</td>
                    <td><input type="text" name="street" id="" value="<%=cust.getStreet()%>"/></td>
                </tr>
                <tr>
                    <td>city</td>
                    <td><input type="text" name="city" id="" value="<%=cust.getCity()%>"/></td>
                </tr>
                <tr>
                    <td>state</td>
                    <td><input type="text" name="state" id="" value="<%=cust.getState()%>"/></td>
                </tr>
                <tr>
                    <td>zip_code</td>
                    <td><input type="text" name="zip_code" id="" value="<%=cust.getZip_code()%>"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="update Customers" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                <input type="hidden" name="service" value="update" />
            </table>
        </form>
    </body>
</html>