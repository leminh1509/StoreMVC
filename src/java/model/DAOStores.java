package model;

import entity.Stores;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStores extends DBConnect {

    public int insertStore(Stores str) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stores]\n"
                + "           ([store_id]\n"
                + "           ,[store_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code])\n"
                + "     VALUES\n"
                + "           (" + str.getStore_id() + "\n"
                + "           ,'" + str.getStore_name() + "'\n"
                + "           ,'" + str.getPhone() + "'\n"
                + "           ,'" + str.getEmail() + "'\n"
                + "           ,'" + str.getStreet() + "'\n"
                + "           ,'" + str.getCity() + "'\n"
                + "           ,'" + str.getState() + "'\n"
                + "           ,'" + str.getZip_code() + "')";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public int addStore(Stores str) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[stores]\n"
                + "           ([store_id]\n"
                + "           ,[store_name]\n"
                + "           ,[phone]\n"
                + "           ,[email]\n"
                + "           ,[street]\n"
                + "           ,[city]\n"
                + "           ,[state]\n"
                + "           ,[zip_code])\n"
                + "     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, str.getStore_id());
            pre.setString(2, str.getStore_name());
            pre.setString(3, str.getPhone());
            pre.setString(4, str.getEmail());
            pre.setString(5, str.getStreet());
            pre.setString(6, str.getCity());
            pre.setString(7, str.getState());
            pre.setString(8, str.getZip_code());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public int updateStore(Stores str) {
        int n = 0;
        String sql = "UPDATE [dbo].[stores]\n"
                + "   SET [store_name] = ?\n"
                + "      ,[phone] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[street] = ?\n"
                + "      ,[city] = ?\n"
                + "      ,[state] = ?\n"
                + "      ,[zip_code] = ?\n"
                + " WHERE [store_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);

            pre.setString(1, str.getStore_name());
            pre.setString(2, str.getPhone());
            pre.setString(3, str.getEmail());
            pre.setString(4, str.getStreet());
            pre.setString(5, str.getCity());
            pre.setString(6, str.getState());
            pre.setString(7, str.getZip_code());
            pre.setInt(8, str.getStore_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public Vector<Stores> getName(String _name) {
        Vector<Stores> vector = new Vector<>();
        String sql = "SELECT * FROM stores WHERE store_name like '" + _name + "'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String street = rs.getString(5);
                String city = rs.getString(6);
                String _state = rs.getString(7);
                String zipCode = rs.getString(8);
                Stores str = new Stores(id, name, phone, email, street, city, _state, zipCode);
                vector.add(str);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Stores> getAll(String sql) {
        Vector<Stores> vector = new Vector<>();

        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String phone = rs.getString(3);
                String email = rs.getString(4);
                String street = rs.getString(5);
                String city = rs.getString(6);
                String _state = rs.getString(7);
                String zipCode = rs.getString(8);
                Stores str = new Stores(id, name, phone, email, street, city, _state, zipCode);
                vector.add(str);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int removeStore(int id){
        int n = 0;
        String sqlRemove = "DELETE FROM stores WHERE store_id = " + id +
                " AND ("+id+" NOT IN (SELECT store_id FROM stocks WHERE store_id = " + id +
                ") AND ("+id+" NOT IN (SELECT store_id FROM orders WHERE store_id = " + id + 
                ") AND ("+id+" NOT IN (SELECT store_id FROM staffs WHERE store_id = " + id + "))))";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sqlRemove);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }

    public static void main(String[] args) {
        DAOStores dao = new DAOStores();

        //int n = dao.insertStore(new Stores(5 ,"Borsch", "0987654321", "gmail@gmail.com", "Ivana Mazepi street", "Bila Tserkva", "No state", "12345"));
        //int n = dao.addStore(new Stores(6 ,"Borsch", "0987654321", "gmail@gmail.com", "Ivana Mazepi street", "Bila Tserkva", "No state", "12345"));
//        int n = dao.updateStore(new Stores(6 ,"Ivanivka", "0987654321", "gmail@gmail.com", "Ivana Mazepi street", "Bila Tserkva", "No state", "12345"));
//        if(n > 0) System.out.println("inserted");
//        Vector<Stores> vector = dao.getAll("SELECT * FROM stores");
//        for(Stores str : vector) System.out.println(str);
        Vector<Stores> vector = dao.getName("Santa Cruz Bikes");
        for (Stores str : vector) {
            System.out.println(str);
        }
    }
}
