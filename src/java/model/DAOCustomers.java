package model;

import entity.Customers;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOCustomers extends DBConnect {
    public int insertCustomers(Customers cust){
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]\n" +
"           ([customer_id]\n" +
"           ,[first_name]\n" +
"           ,[last_name]\n" +
"           ,[phone]\n" +
"           ,[email]\n" +
"           ,[street]\n" +
"           ,[city]\n" +
"           ,[state]\n" +
"           ,[zip_code])\n" +
"     VALUES\n" +
"           ("+cust.getCustomer_id()+"" +
"           , '"+cust.getFirst_name()+"'" +
"           , '"+cust.getLast_name()+"'" +
"           , '"+cust.getPhone()+"'" +
"           , '"+cust.getEmail()+"'" +
"           , '"+cust.getStreet()+"'" +
"           , '"+cust.getCity()+"'" +
"           , '"+cust.getState()+"'" +
"           , '"+cust.getZip_code()+"')";
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
    
    public int addCustomer(Customers cust){
        int n = 0;
        String sql = "INSERT INTO [dbo].[customers]\n" +
"           ([customer_id]\n" +
"           ,[first_name]\n" +
"           ,[last_name]\n" +
"           ,[phone]\n" +
"           ,[email]\n" +
"           ,[street]\n" +
"           ,[city]\n" +
"           ,[state]\n" +
"           ,[zip_code])\n" +
"     VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, cust.getCustomer_id());
            pre.setString(2, cust.getFirst_name());
            pre.setString(3, cust.getLast_name());
            pre.setString(4, cust.getPhone());
            pre.setString(5, cust.getEmail());
            pre.setString(6, cust.getStreet());
            pre.setString(7, cust.getCity());
            pre.setString(8, cust.getState());
            pre.setString(9, cust.getZip_code());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateCustomer(Customers cust){
        int n = 0;
        String sql = "UPDATE [dbo].[customers]\n" +
"   SET [first_name] = ?\n" +
"      ,[last_name] = ?\n" +
"      ,[phone] = ?\n" +
"      ,[email] = ?\n" +
"      ,[street] = ?\n" +
"      ,[city] = ?\n" +
"      ,[state] = ?\n" +
"      ,[zip_code] = ?\n" +
" WHERE [customer_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, cust.getFirst_name());
            pre.setString(2, cust.getLast_name());
            pre.setString(3, cust.getPhone());
            pre.setString(4, cust.getEmail());
            pre.setString(5, cust.getStreet());
            pre.setString(6, cust.getCity());
            pre.setString(7, cust.getState());
            pre.setString(8, cust.getZip_code());
            pre.setInt(9, cust.getCustomer_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<Customers> searchFirstName(String _firstName){
        Vector<Customers> vector = new Vector<>();
        String sql = "SELECT * FROM Customers WHERE first_name like '%" + _firstName + "%'";
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String _state = rs.getString(8);
                String zipCode = rs.getString(9);
                Customers cust = new Customers(id, firstName, lastName, phone, email, street, city, _state, zipCode);
                vector.add(cust);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public Vector<Customers> getAll(String sql){
        Vector<Customers> vector = new Vector<>();
        
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String phone = rs.getString(4);
                String email = rs.getString(5);
                String street = rs.getString(6);
                String city = rs.getString(7);
                String _state = rs.getString(8);
                String zipCode = rs.getString(9);
                Customers cust = new Customers(id, firstName, lastName, phone, email, street, city, _state, zipCode);
                vector.add(cust);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public int removeCustomer(int id){
        int n = 0;
        String sqlRemove = "DELETE FROM Customers WHERE customer_id = " + id +
                " AND "+id+" NOT IN (SELECT customer_id FROM orders WHERE customer_id = " + id + ")";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sqlRemove);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCustomers.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public static void main(String[] args){
        DAOCustomers dao = new DAOCustomers();
        
        //int n = dao.insertCustomers(new Customers(502, "Maksim", "Borodyanik", "0987654321", "gmail.@gmail.com", "Ivana Mazepi 81A", "Bila Tserkva", "nothing", "4563"));
        //int n = dao.addCustomer(new Customers(510, "Maksim", "Borodyanik", "0987654321", "gmail.@gmail.com", "Ivana Mazepi 81A", "Bila Tserkva", "nothing", "4563"));
//        int n = dao.updateCustomer(new Customers(510, "Nikita", "Borodyanik", "0987654321", "gmail.@gmail.com", "Ivana Mazepi 81A", "Bila Tserkva", "nothing", "4563"));
//
//        if(n > 0) System.out.println("inserted");

//        Vector<Customers> vector = dao.getAll("SELECT * FROM Customers");
//        for(Customers cust : vector) System.out.println(cust);

//        Vector<Customers> vector = dao.searchFirstName("Maksim");
//        for(Customers cust : vector) System.out.println(cust);

        int n = dao.removeCustomer(502);
        if(n > 0) System.out.println("removed");
    }
}
