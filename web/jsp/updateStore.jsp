<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Stores"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Update Store</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%Stores st = (Stores)request.getAttribute("str");%>
        <form action="StoreControllerURL" method="post">
            <table>
                <caption>Update Store</caption>
                <tr>
                    <td>store_id</td>
                    <td><input type="text" name="store_id" id="" value="<%=st.getStore_id()%>"/></td>
                </tr>
                <tr>
                    <td>store_name</td>
                    <td><input type="text" name="store_name" id="" value="<%=st.getStore_name()%>"/></td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td><input type="text" name="phone" id="" value="<%=st.getPhone()%>"/></td>
                </tr>
                <tr>
                    <td>email</td>
                    <td><input type="text" name="email" id="" value="<%=st.getEmail()%>"/></td>
                </tr>
                <tr>
                    <td>street</td>
                    <td><input type="text" name="street" id="" value="<%=st.getStreet()%>"/></td>
                </tr>
                <tr>
                    <td>city</td>
                    <td><input type="text" name="city" id="" value="<%=st.getCity()%>"/></td>
                </tr>
                <tr>
                    <td>state</td>
                    <td><input type="text" name="state" id="" value="<%=st.getState()%>"/></td>
                </tr>
                <tr>
                    <td>zip_code</td>
                    <td><input type="text" name="zip_code" id="" value="<%=st.getZip_code()%>"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="update Store" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                <input type="hidden" name="service" value="update" />
        </form>
    </table>
</body>
</html>

