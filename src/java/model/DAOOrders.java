package model;

import entity.Orders;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOOrders extends DBConnect{
    public int insertOrder(Orders o){
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n" +
"           ([order_id]\n" +
"           ,[customer_id]\n" +
"           ,[order_status]\n" +
"           ,[order_date]\n" +
"           ,[required_date]\n" +
"           ,[shipped_date]\n" +
"           ,[store_id]\n" +
"           ,[staff_id])\n" +
"     VALUES\n" +
"           ("+o.getOrder_id()+","
                + " "+o.getCustomer_id()+", "
                + ""+o.getOrder_status()+", "
                + "'"+o.getOrder_date()+"', "
                + "'"+o.getRequired_date()+"', "
                + "'"+o.getShipped_date()+"', "
                + ""+o.getStore_id()+","
                + ""+o.getStaff_id()+")";
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
    
    public int addOrder(Orders o){
        int n = 0;
        String sql = "INSERT INTO [dbo].[orders]\n" +
"           ([order_id]\n" +
"           ,[customer_id]\n" +
"           ,[order_status]\n" +
"           ,[order_date]\n" +
"           ,[required_date]\n" +
"           ,[shipped_date]\n" +
"           ,[store_id]\n" +
"           ,[staff_id])\n" +
"     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, o.getOrder_id());
            pre.setInt(2, o.getCustomer_id());
            pre.setInt(3, o.getOrder_status());
            pre.setString(4, o.getOrder_date());
            pre.setString(5, o.getRequired_date());
            pre.setString(6, o.getShipped_date());
            pre.setInt(7, o.getStore_id());
            pre.setInt(8, o.getStaff_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateOrder(Orders o){
        int n = 0;
        String sql = "UPDATE [dbo].[orders]\n" +
"   SET [customer_id] = ?\n" +
"      ,[order_status] = ?\n" +
"      ,[order_date] = ?\n" +
"      ,[required_date] = ?\n" +
"      ,[shipped_date] = ?\n" +
"      ,[store_id] = ?\n" +
"      ,[staff_id] = ?\n" +
" WHERE [order_id] = ?";
        
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, o.getCustomer_id());
            pre.setInt(2, o.getOrder_status());
            pre.setString(3, o.getOrder_date());
            pre.setString(4, o.getRequired_date());
            pre.setString(5, o.getShipped_date());
            pre.setInt(6, o.getStore_id());
            pre.setInt(7, o.getStaff_id());
            pre.setInt(8, o.getOrder_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<Orders> searchId(int _id){
        Vector<Orders> vector = new Vector<>();
        String sql = "SELECT * FROM orders WHERE order_id = " + _id;
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int order_id = rs.getInt(1);
                int cust_id = rs.getInt(2);
                int status = rs.getInt(3);
                String o_date = rs.getString(4);
                String r_date = rs.getString(5);
                String s_date = rs.getString(6);
                int store_id = rs.getInt(7);
                int staff_id = rs.getInt(8);
                Orders o = new Orders(order_id, cust_id, status, o_date, r_date, s_date, store_id, staff_id);
                vector.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Orders> getAll(String sql){
        Vector<Orders> vector = new Vector<>();
        
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int order_id = rs.getInt(1);
                int cust_id = rs.getInt(2);
                int status = rs.getInt(3);
                String o_date = rs.getString(4);
                String r_date = rs.getString(5);
                String s_date = rs.getString(6);
                int store_id = rs.getInt(7);
                int staff_id = rs.getInt(8);
                Orders o = new Orders(order_id, cust_id, status, o_date, r_date, s_date, store_id, staff_id);
                vector.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int removeOrder(int id){
        int n = 0;
        String sqlRemove = "DELETE FROM orders WHERE order_id = " + id +
                " AND "+id+" NOT IN (SELECT order_id FROM order_items WHERE order_id = " + id + ")";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sqlRemove);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrders.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public static void main(String[] args){
        DAOOrders dao = new DAOOrders();
        
//        int n = dao.insertOrder(new Orders(1808, 5, 2, "9/9/9999", "11/11/9999", "4/4/9999", 3, 3));
        //int n = dao.addOrder(new Orders(1807, 5, 3, "11/11/1212", "11/11/1212", "10/13/1313", 3, 3));
//        int n = dao.updateOrder(new Orders(1807, 5, 3, "12/11/1212", "11/11/1212", "10/13/1313", 3, 3));
//
//       if(n > 0) System.out.println("inserted");

//        Vector<Orders> vector = dao.getAll("SELECT * FROM orders");
//        for(Orders o : vector) System.out.println(o);

//        Vector<Orders> vector = dao.searchId(1801);
//        for(Orders o : vector) System.out.println(o);
        
//        int n = dao.removeOrder(1808);
//        if(n > 0) System.out.println("removed");    
    }
}
