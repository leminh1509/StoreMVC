package model;

import entity.Staffs;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOStaffs extends DBConnect {
//    public boolean login(String username, String password){
//        String sql = "SELECT * FROM staffs WHERE email LIKE '"+username+"' AND phone LIKE '"+password+"'";
//        Vector<Staffs> vector = getAll(sql);
//        return !vector.isEmpty();
//    }
    public boolean login(String username,String pass){
        boolean flag=false;
        String sql="select * from Staffs where email=? and phone=?";
        try {
            PreparedStatement pre=conn.prepareStatement(sql);
            pre.setString(1, username);
            pre.setString(2,pass);
            ResultSet rs=pre.executeQuery();
            if(rs.next()){
                flag=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return flag;
    }

    public int insertStaff(Staffs st){
        int n = 0;
        String sql = "INSERT INTO [dbo].[staffs]\n" +
"           ([staff_id]\n" +
"           ,[first_name]\n" +
"           ,[last_name]\n" +
"           ,[email]\n" +
"           ,[phone]\n" +
"           ,[active]\n" +
"           ,[store_id]\n" +
"           ,[manager_id])\n" +
"     VALUES\n" +
"           ("+st.getStaff_id()+"\n" +
"           ,'"+st.getFirst_name()+"'\n" +
"           ,'"+st.getLast_name()+"'\n" +
"           ,'"+st.getEmail()+"'\n" +
"           ,'"+st.getPhone()+"'\n" +
"           ,"+st.getActive()+"\n" +
"           ,"+st.getStore_id()+"\n" +
"           ,"+st.getManager_id()+")";
        System.out.println(sql);
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();        
        }
        return n;
    }
    
    public int addStaff(Staffs st){
        int n = 0;
        String sql = "INSERT INTO [dbo].[staffs]\n" +
"           ([staff_id]\n" +
"           ,[first_name]\n" +
"           ,[last_name]\n" +
"           ,[email]\n" +
"           ,[phone]\n" +
"           ,[active]\n" +
"           ,[store_id]\n" +
"           ,[manager_id])\n" +
"     VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, st.getStaff_id());
            pre.setString(2, st.getFirst_name());
            pre.setString(3, st.getLast_name());
            pre.setString(4, st.getEmail());
            pre.setString(5, st.getPhone());
            pre.setInt(6, st.getActive());
            pre.setInt(7, st.getStore_id());
            pre.setInt(8, st.getManager_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public int updateStaff(Staffs st){
        int n = 0;
        String sql = "UPDATE [dbo].[staffs]\n" +
"   SET [first_name] = ?\n" +
"      ,[last_name] = ?\n" +
"      ,[email] = ?\n" +
"      ,[phone] = ?\n" +
"      ,[active] = ?\n" +
"      ,[store_id] = ?\n" +
"      ,[manager_id] = ?\n" +
" WHERE [staff_id] = ?";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, st.getFirst_name());
            pre.setString(2, st.getLast_name());
            pre.setString(3, st.getEmail());
            pre.setString(4, st.getPhone());
            pre.setInt(5, st.getActive());
            pre.setInt(6, st.getStore_id());
            pre.setInt(7, st.getManager_id());
            pre.setInt(8, st.getStaff_id());
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public Vector<Staffs> getAll(String sql){
        Vector<Staffs> vector = new Vector<>();
        
        try {
            Statement state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = state.executeQuery(sql);
            while(rs.next()){
                int staff_id = rs.getInt(1);
                String fName = rs.getString(2);
                String lName = rs.getString(3);
                String email = rs.getString(4);
                String phone = rs.getString(5);
                int active = rs.getInt(6);
                int store_id = rs.getInt(7);
                int manager_id = rs.getInt(8);
                Staffs st = new Staffs(staff_id, fName, lName, email, phone, active, store_id, manager_id);
                vector.add(st);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        return vector;
    }
    
    public int removeStaff(int id){
        int n = 0;
        String sqlRemove = "DELETE FROM staffs WHERE staff_id = " + id
                + "AND "+id+" NOT IN (SELECT staff_id FROM orders WHERE staff_id = " + id + ")";
        try {
            Statement state = conn.createStatement();
            n = state.executeUpdate(sqlRemove);
        } catch (SQLException ex) {
            Logger.getLogger(DAOStaffs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    
    public static void main(String[] args){
        DAOStaffs dao = new DAOStaffs();
        
        //int n = dao.insertStaff(new Staffs(11, "Andriy", "Chin", "gmail@gmail.com", "0987654321", 1, 2, 1));
//        int n = dao.addStaff(new Staffs(12, "Maksim", "Karasenko", "gemail@gmail.com", "0987654321", 1, 3, 5));
//        int n = dao.updateStaff(new Staffs(12, "Maksim", "Brovenko", "gemail@gmail.com", "0987654321", 1, 2, 1));
//        if(n > 0) System.out.println("inserted");

        Vector<Staffs> vector = dao.getAll("SELECT * FROM staffs");
        for(Staffs st : vector) System.out.println(st);

//        int n = dao.removeStaff(12);
//        if(n > 0) System.out.println("removed");
    }
}
