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
public class Job {
    
    public void jobFieldTable(JTable table, String p, String n){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = MyConnection.getConnection().prepareStatement("SELECT * FROM job WHERE province=? AND namePlace=?");
            ps.setString(1, p);
            ps.setString(2, n);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){    
                int sellId = rs.getInt(2);
                row = new Object[2];
                row[0] = getUser(sellId);
                row[1] = getName(sellId);
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
    
    public void insertJob(String province, String namePlace ,int idLogin){
        PreparedStatement ps = null;
        String query = "INSERT INTO job(profileSell_id,province,namePlace) VALUES(?,?,?)";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1, idLogin);
            ps.setString(2, province);
            ps.setString(3, namePlace);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "New Job");

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {

            }
        }
    }
    
    public void deleteJob(int idLogin){
        PreparedStatement ps = null;
        String query = "DELETE FROM job WHERE profileSell_id = ?";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1, idLogin);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "DELETE User");

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {

            }
        } 
    }
    public void jobFieldTable2(JTable table, String p){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = MyConnection.getConnection().prepareStatement("SELECT * FROM job WHERE province LIKE ?");
            ps.setString(1, "%"+p+"%");
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){    
                int sellId = rs.getInt(2);
                row = new Object[2];
                row[0] = getUser(sellId);
                row[1] = getName(sellId);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                rs.close();
                ps.close();
            }catch(Exception e){
                
            }
        }
    }
    
    public String getName(int id){
        String name = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = MyConnection.getConnection().prepareStatement("SELECT * FROM profile WHERE id=? ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                name = rs.getString(4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                rs.close();
                ps.close();
            }catch(SQLException e){
                
            }
        }
        return name;
    }
    public String getUser(int id){
        String user = "";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = MyConnection.getConnection().prepareStatement("SELECT * FROM profile WHERE id=? ");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                user = rs.getString(2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                rs.close();
                ps.close();
            }catch(SQLException e){
                
            }
        }
        return user;
    }
    
}
