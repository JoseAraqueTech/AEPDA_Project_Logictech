/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.persistence;

import com.mycompany.clubsociosaepda.model.Activitat;
import com.mycompany.clubsociosaepda.model.Asignacion;
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
import java.util.List;

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
    
        public List<Usuari> mostrarUsuari(String dni) throws SQLException {
        List<Usuari> lista = new ArrayList<>();
        conectar();
        ps = conexion.prepareStatement("SELECT * FROM usuarios WHERE dni = ?");
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String nif = rs.getString("dni");
            String nom = rs.getString("nombre");
            String email = rs.getString("email");
            double saldo = rs.getDouble("saldo");
            boolean soci = rs.getBoolean("es_socio");
            int meses_membresia = rs.getInt("meses_membresia");
            int participaciones = rs.getInt("participaciones");
            lista.add(new Usuari(nif, nom, email, saldo));
        }
        rs.close();
        desconectar();
        return lista;
    }
        
    public boolean buscarUsuari(String dni) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("SELECT 1 FROM usuarios WHERE dni = ?");
        ps.setString(1, dni);

        ResultSet rs = ps.executeQuery();

        boolean existe = rs.next();

        rs.close();
        ps.close();
        desconectar();

        return existe;
    }
    
        public void ferSoci(String dni, int mesos) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("INSERT INTO socios (dni, mesos) VALUES (?, ?);");
        ps.setString(1, dni);
        ps.setInt(2, mesos);
        ResultSet rs = ps.executeQuery();
        rs.close();
        desconectar();
    }
    

    
    public boolean esSoci(String dni) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("SELECT socios FROM usuarios WHERE dni = ?");
        ps.setString(1, dni);
        ResultSet rs = ps.executeQuery();
        boolean soci = false;
        if (rs.next()) {
            soci = rs.getBoolean("soci");
        }
        rs.close();
        desconectar();
        return soci;
    }

    public void insertarUsuari(String dni, String nom, String email, double saldo) throws SQLException {
        conectar();
        // Seguimos la Alerta de Seguretat: uso obligatorio de PreparedStatement [5]
        ps = conexion.prepareStatement("INSERT INTO usuarios (dni, nom) VALUES (?, ?, ?, ?)");
        ps.setString(1, dni);
        ps.setString(2, nom);
        ps.setString(3, email);
        ps.setDouble(4, saldo);
        ps.executeUpdate();
        desconectar();
    }

    public void actualizarUsuari(String dni, String nom, String email, int soci) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("UPDATE usuaris SET nom = ?, email = ?, soci = ? WHERE dni = ?");
        ps.setString(1, nom);
        ps.setString(2, email);
        ps.setInt(3, soci);
        ps.setString(4, dni);
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
    
    public void finalitzarMembresia(String dni)
            throws SQLException {
        conectar();
        ps = conexion.prepareStatement("UPDATE usuarios SET soci = false, meses_membresia = 0 WHERE dni = ?;" + "\nDELETE FROM socios WHERE dni = ?;");
        ps.setString(1, dni);
        ps.executeUpdate();
        desconectar();
    }

    public void insertarActivitat(Activitat a) throws SQLException {
        conectar();
        String sql = "INSERT INTO activitats (nom, dada) VALUES (?, ?)";
        ps = conexion.prepareStatement(sql);
        ps.setString(1, a.getNom());
        ps.setObject(2, a.getData());

        if (a instanceof CursPintura curs) {

            ps.setString(3, curs.getProfessor());
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
            int id_activitat = rs.getInt("id");
            String nom = rs.getString("nom");

            LocalDate fecha = rs.getObject("data", LocalDate.class);
            String tipus = rs.getString("tipus");

            Activitat a;
            if (tipus.equals("CURS")) {
                String profesor = rs.getString("profesor");
                a = new CursPintura(id_activitat, nom, fecha, profesor);
            } else {
                a = new Torneig(id_activitat, nom, fecha);
            }

            // Rellenamos los datos comunes definidos en la clase padre Activitat
            lista.add(a);
        }
        rs.close();
        desconectar();
        return lista;
    }

    public boolean buscarActivitat(int id_activitat) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("SELECT * FROM activitat WHERE id = ?");
        ps.setInt(1, id_activitat);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        desconectar();
        return existe;
    }
    
public List<Activitat> listaActivitat(int id) throws SQLException {

    String sql = "SELECT * FROM activitats WHERE id = ?";
    List<Activitat> lista = new ArrayList<>();

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, id);

        try (ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                // 1. leer datos del resultset
                int idDb = rs.getInt("id");
                String dni = rs.getString("dni");
                String nom = rs.getString("nom");
                String email = rs.getString("email");
                double saldo = rs.getDouble("saldo");
                String tipus = rs.getString("tipus");
                LocalDate data = rs.getDate("data").toLocalDate();

                Activitat a;

                // 2. decidir tipo
                switch (tipus) {

                    case "Torneig":
                        a = new Torneig(idDb, dni, data);
                        break;

                    case "CursPintura":
                        a = new CursPintura(idDb, nom, data, email);
                        break;

                    default:
                        throw new RuntimeException("Tipus desconegut");
                }

                // 3. añadir a lista
                lista.add(a);
            }
        }
    }

    return lista;
}
    
        public void inscribirUsuari(int id_activitat, String dni) throws SQLException {
        conectar();
        // Seguimos la Alerta de Seguretat: uso obligatorio de PreparedStatement [5]
        ps = conexion.prepareStatement("UPDATE usuarios SET id_actividad = ? WHERE dni = ?");
        ps.setInt(1, id_activitat);
        ps.setString(2, dni);
        ps.executeUpdate();
        desconectar();
    }
        
    public boolean esTorneig(int id) throws SQLException {
        String sql = "SELECT tipus FROM activitats WHERE id = ?";
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            String tipus = rs.getString("tipus");
            return "Torneig".equalsIgnoreCase(tipus);
        }
        return false;
    }

    public void eliminarActivitat(int id_activitat) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("DELETE FROM activitats WHERE id = ?");
        ps.setInt(1, id_activitat);
        ps.executeUpdate();
        desconectar();
    }
    
        public void insertarBalda(int id, String ubicacion) throws SQLException {
        conectar();
        // Seguimos la Alerta de Seguretat: uso obligatorio de PreparedStatement [5]
        ps = conexion.prepareStatement("INSERT INTO balda (id, ubicacion) VALUES (?, ?)");
        ps.setInt(1, id);
        ps.setString(2, ubicacion);
        ps.executeUpdate();
        desconectar();
    }
        
    public boolean buscarBalda(int id) throws SQLException {
        conectar();
        // Seguimos la Alerta de Seguretat: uso obligatorio de PreparedStatement [5]
        ps = conexion.prepareStatement("SELECT * from balda WHERE id = ?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        boolean existe = rs.next();
        rs.close();
        desconectar();
        return existe;

    }

    public List<Balda> listarBaldas() throws SQLException {
        List<Balda> lista = new ArrayList<>();
        conectar();
        ps = conexion.prepareStatement("SELECT * FROM balda");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String ubicacion = rs.getString("ubicacion");
            lista.add (new Balda(id, ubicacion));
        }
        rs.close();
        desconectar();
        return lista;
    }

        public List<Balda> listarBaldasOcupadas() throws SQLException {
        List<Balda> lista = new ArrayList<>();
        conectar();
        ps = conexion.prepareStatement("SELECT * FROM balda WHERE asignacion_actual IS TRUE");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String ubicacion = rs.getString("ubicacion");
            lista.add (new Balda(id, ubicacion));
        }
        rs.close();
        desconectar();
        return lista;
    }
    
    
    public void eliminarBalda(int id) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("DELETE FROM balda WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        desconectar();
    }
    
     public void actualizarBalda(int id, String ubicacion) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("UPDATE balda SET id = ?, ubicacion = ? WHERE id = ?");
        ps.setInt(1, id);
        ps.setString(2, ubicacion);
        ps.executeUpdate();
        desconectar();
    }

    public void asignarBalda(String dni, int id) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("UPDATE balda SET asignacion_actual = ? WHERE id = ?");
        ps.setString(1,dni);
        ps.setInt(2, id);
        ps.executeUpdate();
        desconectar();
    }
    
        public void liberarBalda( int id) throws SQLException {
        conectar();
        ps = conexion.prepareStatement("UPDATE balda SET asignacion_actual = NULL WHERE id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();
        desconectar();
    }

}
