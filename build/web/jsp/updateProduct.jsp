<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Products"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Update Product</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <%Products pro = (Products)request.getAttribute("pro");%>
        <form action="ProductControllerURL" method="post">
            <table>
                <caption>Update Product</caption>
                <tr>
                    <td>product_id</td>
                    <td><input type="text" name="pid" id="" value="<%=pro.getProduct_id()%>" readonly></td>
                </tr>
                <tr>
                    <td>product_name</td>
                    <td><input type="text" name="pname" id="" value="<%=pro.getProduct_name()%>"/></td>
                </tr>
                <tr>
                    <td>model_year</td>
                    <td><input type="text" name="model" id="" value="<%=pro.getModel_year()%>"/></td>
                </tr>
                <tr>
                    <td>list_price</td>
                    <td><input type="text" name="price" id="" value="<%=pro.getList_price()%>"/></td>
                </tr>
                <tr>
                    <td>brand_name</td>
                    <td><input type="text" name="brand" id="" value="<%=pro.getBrand_name()%>"/></td>
                </tr>
                <tr>
                    <td>category_name</td>
                    <td><input type="text" name="category" id="" value="<%=pro.getCategory_name()%>"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="update Products" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                <input type="hidden" name="service" value="update" />
            </table>
        </form>
    </body>
</html>

