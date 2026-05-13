/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.persistence;

import com.mycompany.clubsociosaepda.model.Activitat;
import com.mycompany.clubsociosaepda.model.Balda;
import com.mycompany.clubsociosaepda.model.CursPintura;
import com.mycompany.clubsociosaepda.model.Torneig;
import com.mycompany.clubsociosaepda.model.Usuari;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void insertarSocio(Usuari u) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("INSERT INTO socios (dni, nombre, apellidos) VALUES (?, ?, ?)");
        ps.setString(1, u.getDni());
        ps.setString(2, u.getNom());
        ps.setString(3, u.getEmail());
        ps.setBoolean(4, u.esSoci());
        ps.executeUpdate();
        desconectar();
    }

    public List<Usuari> listarUsuaris() throws SQLException {
        List<Usuari> lista = new ArrayList<>();
        conectar();
        ps = conexion.prepareStatement("SELECT * FROM usuaris");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String nif = rs.getString("dni");
            String nom = rs.getString("nombre");
            String email = rs.getString("email");
            double saldo = rs.getDouble("saldo");
            boolean soci = rs.getBoolean("es_socio");
            lista.add(new Usuari(nif, nom, email, saldo));
        }
        rs.close();
        desconectar();
        return lista;
    }

    public void actualizarUsuari(Usuari u) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("UPDATE usuaris SET nombre = ?, apellidos = ?, es_socio = ? WHERE dni = ?");
        ps.setString(1, u.getNom());
        ps.setString(2, u.getEmail());
        ps.setBoolean(3, u.esSoci());
        ps.setString(4, u.getDni());
        ps.executeUpdate();
        desconectar();
    }

    public void eliminarUsuari(String dni) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("DELETE FROM usuaris WHERE dni = ?");
        ps.setString(1, dni);
        ps.executeUpdate();
        desconectar();
    }

    public void insertarActivitat(Activitat a) throws SQLException {
        conectar();

        String sql = "INSERT INTO activitats (nom, preu, tipus, dada_especifica) VALUES (?, ?, ?, ?)";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, a.getNom());
        ps.setObject(2, a.getData());

        if (a instanceof CursPintura curs) {
            ps.setString(3, "CURS");
            ps.setString(5, curs.getProfessor());
        } else if (a instanceof Torneig torneig) {
            ps.setString(3, "TORNEIG");
        }
        ps.executeUpdate();
        desconectar();
    }

    public List<Activitat> listarActivitats() throws SQLException {
        List<Activitat> lista = new ArrayList<>(); // Uso de interfaz List para interoperabilidad [6]
        conectar();
        ps = conexion.prepareStatement("SELECT * FROM activitats");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            String nom = rs.getString("nom");

            LocalDate fecha = rs.getObject("data", LocalDate.class);
            String tipus = rs.getString("tipus");
            String dadaEspecifica = rs.getString("dada_especifica");

            Activitat a;
            if (tipus.equals("CURS")) {
                a = new CursPintura(nom, fecha, dadaEspecifica);
            } else {
                a = new Torneig(nom, fecha);
            }

            // Rellenamos los datos comunes definidos en la clase padre Activitat
            lista.add(a);
        }
        rs.close();
        desconectar();
        return lista;
    }

    public void eliminarActivitat(int id) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("DELETE FROM activitats WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        desconectar();
    }
    
        public void insertarBalda(Balda b) throws SQLException {
        conectar();
        // Seguimos la Alerta de Seguretat: uso obligatorio de PreparedStatement [5]
        ps = conexion.prepareStatement("INSERT INTO baldes (id, ubicacion) VALUES (?, ?)");
        ps.setInt(1, b.getId());
        ps.setString(2, b.getUbicacion());
        ps.executeUpdate();
        desconectar();
    }
    
public Map<String, Balda> listarBaldas() throws SQLException {
        Map<String, Balda> lista = new HashMap<>();
        conectar();
        ps = conexion.prepareStatement("SELECT * FROM baldes");
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) {
            int id = rs.getInt("id");
            String codi = rs.getString("codi");
            lista.put(codi, new Balda(id, codi));
        }
        rs.close();
        desconectar();
        return lista;
    }


    public void eliminarBalda(int id) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("DELETE FROM baldes WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        desconectar();
    }
    
     public void actualizarBalda(Balda b) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("UPDATE baldes SET codi = ?, capacitat = ? WHERE id = ?");
        ps.setInt(1, b.getId());
        ps.setString(2, b.getUbicacion());
        ps.executeUpdate();
        desconectar();
    }

}
