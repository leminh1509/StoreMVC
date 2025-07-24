package model;

import entity.Stocks;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Vector;

public class DAOStocks extends DBConnect {
    public int insertStock(Stocks stk){
        int n = 0;
        String sql = "INSERT INTO [dbo].[stocks]\n" +
"           ([store_id]\n" +
"           ,[product_id]\n" +
"           ,[quantity])\n" +
"     VALUES\n" +
"           ("+stk.getStore_id()+",\n" +
"            "+stk.getProduct_id()+",\n" +
"            "+stk.getQuantity()+")";
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
    
    public int addStock(Stocks stk){
        int n = 0;
        String sql = "INSERT INTO [dbo].[stocks]\n" +
"           ([store_id]\n" +
"           ,[product_id]\n" +
"           ,[quantity])\n" +
"     VALUES(?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, stk.getStore_id());
            pre.setInt(2, stk.getProduct_id());
            pre.setInt(3, stk.getQuantity());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateStock(Stocks stk){
        int n = 0;
        String sql = "UPDATE [dbo].[stocks]\n" +
"   SET [quantity] = ?\n" +
" WHERE [store_id] = ? AND [product_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, stk.getQuantity());
            pre.setInt(2, stk.getStore_id());
            pre.setInt(3, stk.getProduct_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<Stocks> searchStoreId(int _storeId){
        Vector<Stocks> vector = new Vector<>();
        String sql = "SELECT * FROM stocks WHERE store_id = "+_storeId+"";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int storeId = rs.getInt(1);
                int productId = rs.getInt(2);
                int quantity = rs.getInt(3);
                Stocks stk = new Stocks(storeId, productId, quantity);
                vector.add(stk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Stocks> getAll(String sql){
        Vector<Stocks> vector = new Vector<>();
        
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int storeId = rs.getInt(1);
                int productId = rs.getInt(2);
                int quantity = rs.getInt(3);
                Stocks stk = new Stocks(storeId, productId, quantity);
                vector.add(stk);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int removeStock(int store_id, int product_id){
        int n = 0;
        String sqlRemove = "DELETE FROM Stocks WHERE store_id = "+store_id+" AND product_id = "+product_id+"";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sqlRemove);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStocks.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public static void main(String[] args){
        DAOStocks dao = new DAOStocks();
        
        //int n = dao.insertStock(new Stocks(4, 2, 5));
        //int n = dao.addStock(new Stocks(4, 3, 5));
//        int n = dao.updateStock(new Stocks(4, 3, 10));
//        if(n > 0) System.out.println("inserted");
        
//        Vector<Stocks> vector = dao.getAll("SELECT * FROM stocks");
//        for(Stocks stk : vector) System.out.println(stk);
        
//        Vector<Stocks> vector = dao.searchStoreId(4);
//        for(Stocks stk : vector) System.out.println(stk);
        //int n = dao.addStock(new Stocks(4, 5, 10));
        int n = dao.removeStock(4, 5);
    }
}
