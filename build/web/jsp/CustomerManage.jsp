<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector, entity.Customers"%>

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
            <caption><%=(String)request.getAttribute("tableTitle")%></caption>
            <tr>
                <th>customer_id</th>
                <th>first_name</th>
                <th>last_name</th>
                <th>phone</th>
                <th>email</th>
                <th>street</th>
                <th>city</th>
                <th>state</th>
                <th>zip_code</th>
                <th>update</th>
                <th>delete</th>
                
            </tr>
            <%
                Vector<Customers> vector = (Vector<Customers>)request.getAttribute("data");
                for(Customers cust : vector) {
            %>
            <tr>
                <td><%=cust.getCustomer_id()%></td>
                <td><%=cust.getFirst_name()%></td>
                <td><%=cust.getLast_name()%></td>
                <td><%=cust.getPhone()%></td>
                <td><%=cust.getEmail()%></td>
                <td><%=cust.getStreet()%></td>
                <td><%=cust.getCity()%></td>
                <td><%=cust.getState()%></td>
                <td><%=cust.getZip_code()%></td>
                <td><a href="CustomerControllerURL?service=update&id=<%=cust.getCustomer_id()%>">update</a></td>
                <td><a href="CustomerControllerURL?service=delete&id=<%=cust.getCustomer_id()%>">delete</a></td>
                <td><a href="CustomerControllerURL?service=showOrder&id=<%=cust.getCustomer_id()%>"">show</a></td>
            </tr>
            <%}%>
        </table>
    </body>
</html>
