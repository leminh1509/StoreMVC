<%-- 
    Document   : StaffManage
    Created on : Feb 18, 2024, 1:41:58 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Staffs"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=(String)request.getAttribute("pageTitle")%></title>
        
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
        <table>
            <caption>
                <%=(String)request.getAttribute("tableTitle")%>
                <%=(String)session.getAttribute("username")%>
            </caption>
            <tr>
                <th>staff_id</th>
                <th>first_name</th>
                <th>last_name</th>
                <th>email</th>
                <th>phone</th>
                <th>active</th>
                <th>store_id</th>
                <th>manager_id</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <%
                Vector<Staffs> vector = (Vector<Staffs>)request.getAttribute("data");
                for(Staffs st : vector) {
            %>
            <tr>
                <td><%=st.getStaff_id()%></td>
                <td><%=st.getFirst_name()%></td>
                <td><%=st.getLast_name()%></td>
                <td><%=st.getEmail()%></td>
                <td><%=st.getPhone()%></td>
                <td><%=st.getActive()%></td>
                <td><%=st.getStore_id()%></td>
                <td><%=st.getManager_id()%></td>
                <td><a href="StaffControllerURL?service=update&id=<%=st.getStaff_id()%>">update</a></td>
                <td><a href="StaffControllerURL?service=delete&id=<%=st.getStaff_id()%>">delete</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
