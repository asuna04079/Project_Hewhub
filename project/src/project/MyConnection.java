/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author POOM
 */
class MyConnection {
    public static Connection getConnection(){
        Connection con = null;
    try {
                        Class.forName("org.sqlite.JDBC");
                        con = DriverManager.getConnection("jdbc:sqlite:database.sqlite");			
			}catch (Exception es) {
				System.out.println("error"+es);
			}
        return con;
    
    }   
}
