/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author josea
 */
public class AepdaDAO {
    private Connection conexion;
    private PreparedStatement ps;
    
    

    
        private void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bbdd_aepda";
        String user = "root";
        String pass = "root";
        conexion = DriverManager.getConnection(url, user, pass);
    }
    
    private void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
}
