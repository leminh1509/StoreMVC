package model;

import entity.OrderItems;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOOrderItems extends DBConnect {

    public int getID() {
        int n=0;
        String sql = "SELECT new_number\n"
                + "FROM (\n"
                + "    SELECT FLOOR(RAND() * (SELECT MAX(column_name) FROM your_table)) AS new_number\n"
                + ") AS random_number\n"
                + "WHERE new_number NOT IN (SELECT column_name FROM your_table)\n"
                + "LIMIT 1;";
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;

    }

    public int insertOrderItem(OrderItems oi) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]\n"
                + "           ([order_id]\n"
                + "           ,[item_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity]\n"
                + "           ,[list_price]\n"
                + "           ,[discount])\n"
                + "     VALUES\n"
                + "           (" + oi.getOrder_id() + ","
                + "" + oi.getItem_id() + ","
                + "" + oi.getProduct_id() + ","
                + "" + oi.getQuantity() + ","
                + "" + oi.getList_price() + ","
                + "" + oi.getDiscount() + ")";
        System.out.println(sql);
        Statement state;
        try {
            state = conn.createStatement();
            n = state.executeUpdate(sql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int addOrderItem(OrderItems oi) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[order_items]\n"
                + "           ([order_id]\n"
                + "           ,[item_id]\n"
                + "           ,[product_id]\n"
                + "           ,[quantity]\n"
                + "           ,[list_price]\n"
                + "           ,[discount])\n"
                + "     VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, oi.getOrder_id());
            pre.setInt(2, oi.getItem_id());
            pre.setInt(3, oi.getProduct_id());
            pre.setInt(4, oi.getQuantity());
            pre.setDouble(5, oi.getList_price());
            pre.setDouble(6, oi.getDiscount());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateOrderItem(OrderItems oi) {
        int n = 0;
        String sql = "UPDATE [dbo].[order_items]\n"
                + "   SET [item_id] = ?\n"
                + "      ,[product_id] = ?\n"
                + "      ,[quantity] = ?\n"
                + "      ,[list_price] = ?\n"
                + "      ,[discount] = ?\n"
                + " WHERE [order_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, oi.getItem_id());
            pre.setInt(2, oi.getProduct_id());
            pre.setInt(3, oi.getQuantity());
            pre.setDouble(4, oi.getList_price());
            pre.setDouble(5, oi.getDiscount());
            pre.setInt(6, oi.getOrder_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<OrderItems> searchOrderId(String id) {
        Vector<OrderItems> vector = new Vector<>();
        String sql = "SELECT * FROM order_items WHERE order_id LIKE '%" + id + "%'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int order_id = rs.getInt(1);
                int item_id = rs.getInt(2);
                int product_id = rs.getInt(3);
                int quantity = rs.getInt(4);
                double price = rs.getDouble(5);
                double discount = rs.getDouble(6);
                OrderItems oi = new OrderItems(order_id, item_id, product_id, quantity, price, discount);
                vector.add(oi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<OrderItems> getAll(String sql) {
        Vector<OrderItems> vector = new Vector<>();

        try {
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int order_id = rs.getInt(1);
                int item_id = rs.getInt(2);
                int product_id = rs.getInt(3);
                int quantity = rs.getInt(4);
                double price = rs.getDouble(5);
                double discount = rs.getDouble(6);
                OrderItems oi = new OrderItems(order_id, item_id, product_id, quantity, price, discount);
                vector.add(oi);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public int removeOrderItem(int order_id, int item_id) {
        int n = 0;
        String sqlRemove = "DELETE FROM order_items WHERE order_id = " + order_id + " AND item_id = " + item_id + "";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sqlRemove);
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrderItems.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOOrderItems dao = new DAOOrderItems();

        //int n = dao.insertOrderItem(new OrderItems(1806, 1, 112, 5, 33.33, 0.1));
        //int n = dao.addOrderItem(new OrderItems(1801, 1, 112, 5, 33.33, 0.1));
//        int n = dao.updateOrderItem(new OrderItems(1801, 2, 112, 5, 33.33, 0.1));
//
//        if(n > 0) System.out.println("inserted");
//        Vector<OrderItems> vector = dao.getAll("SELECT * FROM order_items");
//        for(OrderItems oi : vector) System.out.println(oi);
//        Vector<OrderItems> vector = dao.searchOrderId("1615");
//        for (OrderItems io : vector) {
//            System.out.println(io);
//        }
        int n = dao.removeOrderItem(1801, 1);

    }
}
