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
public class Orders {
    Chat nChat = new Chat();
    
    public void deleteOrders(int id){
        PreparedStatement ps = null;
        String query = "DELETE FROM orders WHERE id = ?";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "DELETE order");

        } catch (SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {

            }
        }
    }
    
    public void updateStatusOrders(int rowid){
        PreparedStatement ps = null;
        String query = "UPDATE orders SET status=? WHERE _rowid_=?";
        try {
            ps = MyConnection.getConnection().prepareStatement(query);
            ps.setString(1, "เสร็จสิ้น");
            ps.setInt(2, rowid);
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
    
    public void orderFieldTable(JTable table, int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = MyConnection.getConnection().prepareStatement("SELECT * FROM orders WHERE profileBuy_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){ 
                String b = nChat.getName(rs.getInt(3));
                row = new Object[6];
                row[0] = rs.getString(1);
                row[1] = rs.getString(4);
                row[2] = rs.getString(5);
                row[3] = rs.getString(6);
                row[4] = b;
                row[5] = rs.getString(8);
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
    
    public void orderFieldTable2(JTable table, int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = MyConnection.getConnection().prepareStatement("SELECT * FROM orders WHERE profileSell_id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel)table.getModel();
            Object[] row;
            while(rs.next()){ 
                row = new Object[7];
                String b = nChat.getName(rs.getInt(2));
                row[0] = rs.getString(1);
                row[1] = rs.getString(4);
                row[2] = rs.getString(5);
                row[3] = rs.getString(6);
                row[4] = b;
                row[5] = rs.getString(7);
                row[6] = rs.getString(8);
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
    
}
