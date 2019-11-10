/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author POOM
 */
public class Chat {
    Job nJob = new Job();
    
    public int searchIdProfile2Chat(String user ,String fName){
        int idProfile2 = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM profile WHERE user=? AND fName=?";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, fName);
            rs = ps.executeQuery();
            if (rs.next()) {
                idProfile2 = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {

            }
        }
        return idProfile2;
    }
    
    public void deleteChat(int id){
        PreparedStatement ps = null;
        String query = "DELETE FROM chat WHERE id = ?";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "DELETE Chat");

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {

            }
        }
    }
    
    public void updateMesChat(String word, int id){
        PreparedStatement ps = null;
        String query = "UPDATE chat SET mes=? WHERE _rowid_=?";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, word);
            ps.setInt(2, id);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {

                ps.close();
            }catch(SQLException e){
                
            }
        }
    }
    
    public void addChat(String user, String fName ,int idLogin){
        String mas = "เริ่มต้นสนทนา";
        int profile2_id = 0;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM profile WHERE user=? AND fName=?";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, user);
            ps.setString(2, fName);
            rs = ps.executeQuery();
            if (rs.next()) {
                profile2_id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {

            }
        }
        int chack=0;
        ps = null;
        rs = null;
        query = "SELECT * FROM chat WHERE profile1_id=? AND profile2_id=? OR profile1_id=? AND profile2_id =? ";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1, idLogin);
            ps.setInt(2, profile2_id);
            ps.setInt(3, profile2_id);
            ps.setInt(4, idLogin);
            rs = ps.executeQuery();
            if (rs.next()) {
                chack=1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {

            }
        }
        
        if (chack == 0){
            ps = null;
            query = "INSERT INTO chat(mes,profile1_id,profile2_id) VALUES(?,?,?)";
            try {
                ps = MyConnection.getConnection().prepareStatement(query);
                ps.setString(1, mas);
                ps.setInt(2, idLogin);
                ps.setInt(3, profile2_id);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "New Chat");

            } catch (SQLException ex) {
                Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    ps.close();
                } catch (SQLException e) {

                }
            }
        }else {
            JOptionPane.showMessageDialog(null, "มีแล้ว");
        }
    }
    
    public void chatFieldTable(JTable table, int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = MyConnection.getConnection().prepareStatement("SELECT * FROM chat WHERE profile1_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){ 
                String a = nJob.getName(rs.getInt(4));
                String b = nJob.getUser(rs.getInt(4));
                row = new Object[2];
                row[0] = b;
                row[1] = a;
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex); 
        } finally {
            try {
                rs.close();
                ps.close();
            }catch(SQLException e){
                
            }
        }
        

        ps = null;
        rs = null;
        try {
            ps = MyConnection.getConnection().prepareStatement("SELECT * FROM chat WHERE profile2_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){ 
                String a = nJob.getName(rs.getInt(3));
                String b = nJob.getUser(rs.getInt(3));
                row = new Object[2];
                row[0] = b;
                row[1] = a;
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex); 
        } finally {
            try {
                rs.close();
                ps.close();
            }catch(SQLException e){
                
            }
        }
    }
    public String getC(int id1,int id2){
        String meg = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM chat WHERE profile1_id=? AND profile2_id=? OR profile1_id=? AND profile2_id =? ";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1, id1);
            ps.setInt(2, id2);
            ps.setInt(3, id2);
            ps.setInt(4, id1);
            rs = ps.executeQuery();
            if (rs.next()) {
                meg = rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {

            }
        }
        return meg;
    }
    
    public String getName(int id1){
        String name = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM profile WHERE id=?";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1, id1);
            rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {

            }
        }
        return name;
    }
    
    public int getId(int id1,int id2){
        int id = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM chat WHERE profile1_id=? AND profile2_id=? OR profile1_id=? AND profile2_id =? ";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1, id1);
            ps.setInt(2, id2);
            ps.setInt(3, id2);
            ps.setInt(4, id1);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {

            }
        }
        return id;
    }
    
    public String getWord(int id1){
        String word = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM chat WHERE id=? ";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1, id1);
            rs = ps.executeQuery();
            if (rs.next()) {
                word = rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {

            }
        }
        return word;
    }
}
