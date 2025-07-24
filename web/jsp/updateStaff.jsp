<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Staffs"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Update Staff</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%Staffs st = (Staffs)request.getAttribute("st");%>
        <form action="StaffControllerURL" method="post">
            <table>
                <caption>Update Staff</caption>
                <tr>
                    <td>staff_id</td>
                    <td><input type="text" name="staff_id" id="" value="<%=st.getStaff_id()%>"/></td>
                </tr>
                <tr>
                    <td>first_name</td>
                    <td><input type="text" name="fName" id="" value="<%=st.getFirst_name()%>"/></td>
                </tr>
                <tr>
                    <td>last_name</td>
                    <td><input type="text" name="lName" id="" value="<%=st.getLast_name()%>"/></td>
                </tr>
                <tr>
                    <td>email</td>
                    <td><input type="text" name="email" id="" value="<%=st.getEmail()%>"/></td>
                </tr>
                <tr>
                    <td>phone</td>
                    <td><input type="text" name="phone" id="" value="<%=st.getPhone()%>"/></td>
                </tr>
                <tr>
                    <td>active</td>
                    <td><input type="text" name="active" id="" value="<%=st.getActive()%>"/></td>
                </tr>
                <tr>
                    <td>store_id</td>
                    <td><input type="text" name="store_id" id="" value="<%=st.getStore_id()%>"/></td>
                </tr>
                <tr>
                    <td>manager_id</td>
                    <td><input type="text" name="manager_id" id="" value="<%=st.getManager_id()%>"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="update Staff" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                <input type="hidden" name="service" value="update" />
            </table>
        </form>
    </body>
</html>

