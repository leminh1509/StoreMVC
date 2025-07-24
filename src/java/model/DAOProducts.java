package model;

import entity.Products;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOProducts extends DBConnect {
    public int insertProduct(Products pro){
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]\n" +
"           ([product_id]\n" +
"           ,[product_name]\n" +
"           ,[model_year]\n" +
"           ,[list_price]\n" +
"           ,[brand_name]\n" +
"           ,[category_name])\n" +
"     VALUES("+pro.getProduct_id()+", "
                + "'"+pro.getProduct_name()+"', "
                + ""+pro.getModel_year()+", "
                + ""+pro.getList_price()+", "
                + "'"+pro.getBrand_name()+"', "
                + "'"+pro.getCategory_name()+"')";
        System.out.println(sql);
        try{
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return n;
    }
    
    public int addProduct(Products pro){
        int n = 0;
        String sql = "INSERT INTO [dbo].[products]\n" +
"           ([product_id]\n" +
"           ,[product_name]\n" +
"           ,[model_year]\n" +
"           ,[list_price]\n" +
"           ,[brand_name]\n" +
"           ,[category_name])\n" +
"     VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement pre = conn.prepareStatement(sql);
            //pre.setDataType(indexOf?, value);
            pre.setInt(1, pro.getProduct_id());
            pre.setString(2, pro.getProduct_name());
            pre.setInt(3, pro.getModel_year());
            pre.setDouble(4, pro.getList_price());
            pre.setString(5, pro.getBrand_name());
            pre.setString(6, pro.getCategory_name());
            n = pre.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public int updateProduct(Products pro){
        int n = 0;
        String sql = "UPDATE [dbo].[products]\n" +
"   SET [product_name] = ?\n" +
"      ,[model_year] = ?\n" +
"      ,[list_price] = ?\n" +
"      ,[brand_name] = ?\n" +
"      ,[category_name] = ?\n" +
" WHERE [product_id] = ?";
        try{
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, pro.getProduct_name());
            pre.setInt(2, pro.getModel_year());
            pre.setDouble(3, pro.getList_price());
            pre.setString(4, pro.getBrand_name());
            pre.setString(5, pro.getCategory_name());
            pre.setInt(6, pro.getProduct_id());
            n = pre.executeUpdate();
        }
        catch(SQLException ex){
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return n;
    }
    
    public Vector<Products> searchName(String name){
        Vector<Products> vector = new Vector<>();
        String sql = "SELECT * FROM Products WHERE product_name like '%" + name + "%'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("Product_id"); // id = rs.getInt(1);
                String proName = rs.getString("Product_name"); //name = rs.getString(2);
                int model = rs.getInt(3);
                double price = rs.getDouble(4);
                String brand = rs.getString(5);
                String cate = rs.getString(6);
                Products pro = new Products(id, proName, model, price, brand, cate);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Products> getAll(String sql){
        Vector<Products> vector = new Vector<>();
        
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("Product_id"); // id = rs.getInt(1);
                String name = rs.getString("Product_name"); //name = rs.getString(2);
                int model = rs.getInt(3);
                double price = rs.getDouble(4);
                String brand = rs.getString(5);
                String cate = rs.getString(6);
                Products pro = new Products(id, name, model, price, brand, cate);
                vector.add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int removeProduct(int id){
        int n = 0;
        String sqlRemove = "DELETE FROM Products WHERE product_id = " + id + 
                " AND ("+id+" NOT IN (SELECT product_id FROM order_items WHERE product_id = " + id +
                ") AND ("+id+" NOT IN (SELECT product_id FROM stocks WHERE product_id = " + id + ")))";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sqlRemove);
        } catch (SQLException ex) {
            Logger.getLogger(DAOProducts.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public static void main(String[] args){
        DAOProducts dao = new DAOProducts();
        
        //int n = dao.insertProduct(new Products(307, "Pro Demo", 2024, 200, "demo brand", "demo category"));
        //int n = dao.addProduct(new Products(377, "Pro Demo", 2024, 200, "demo brand", "demo category"));
//        int n = dao.updateProduct(new Products(377, "New Pro Demo", 2024, 200, "demo brand", "demo category"));
//
//        if(n > 0) System.out.println("updated");
//          Vector<Products> vector = dao.getAll("SELECT * FROM Products");
//          for(Products pro : vector){
//              System.out.println(pro);
//          }
//          Vector<Products> vector = dao.searchName("Pro Demo");
//          for(Products pro : vector) System.out.println(pro);
          
          int n = dao.removeProduct(300);
          if(n > 0) System.out.println("removed");
    }
}
