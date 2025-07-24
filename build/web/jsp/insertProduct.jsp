<%-- 
    Document   : insertProduct
    Created on : Feb 21, 2024, 10:16:55 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
       <title>Insert Customer</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="ProductControllerURL" method="post">
            <table>
                <caption>insert Product</caption>
                <tr>
                    <td>product_id</td>
                    <td><input type="text" name="pid" id=""  ></td>
                </tr>
                <tr>
                    <td>product_name</td>
                    <td><input type="text" name="pname" id="" "/></td>
                </tr>
                <tr>
                    <td>model_year</td>
                    <td><input type="text" name="model" id="" "/></td>
                </tr>
                <tr>
                    <td>list_price</td>
                    <td><input type="text" name="price" id="" "/></td>
                </tr>
                <tr>
                    <td>brand_name</td>
                    <td><input type="text" name="brand" id="" "/></td>
                </tr>
                <tr>
                    <td>category_name</td>
                    <td><input type="text" name="category" id="" "/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submit" value="insert Products" /></td>
                    <td><input type="reset" value="Reset" /></td>
                </tr>
                <input type="hidden" name="service" value="insert" />
            </table>
        </form>
    </body>
</html>
