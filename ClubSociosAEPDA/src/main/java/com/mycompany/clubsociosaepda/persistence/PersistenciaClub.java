/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.persistence;

import com.mycompany.clubsociosaepda.model.Activitat;
import com.mycompany.clubsociosaepda.model.Asignacion;
import com.mycompany.clubsociosaepda.model.Usuari;
import com.mycompany.clubsociosaepda.model.Torneig;
import com.mycompany.clubsociosaepda.model.CursPintura;
import com.mycompany.clubsociosaepda.exception.PersistenciaException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase encargada de la persistencia de datos del club.
 * Gestiona la lectura y escritura de usuarios, actividades y asignaciones
 * en ficheros CSV dentro de una carpeta local.
 */
public class PersistenciaClub {

    private static final String carpeta = "AEPDA";
    private static final String separador = File.separator;
    private static final String fitxerUsuaris = carpeta + separador + "usuaris.csv";
    private static final String fitxerActivitats = carpeta + separador + "activitats.csv";
    private static final String fitxerAssignacions = carpeta + separador + "assignacions.csv";

    /**
     * Crea la carpeta de almacenamiento si no existe.
     */
    private static void crearCarpeta() {
        File c = new File(carpeta);
        if (!c.exists()) {
            c.mkdir();
        }
    }

    /**
     * Guarda la lista de usuarios en un fichero CSV.
     * @param usuaris lista de usuarios a guardar
     * @throws PersistenciaException si ocurre un error al escribir en el fichero
     */
    public static void guardarUsuaris(ArrayList<Usuari> usuaris) throws PersistenciaException {
        crearCarpeta();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerUsuaris))) {
            for (Usuari u : usuaris) {
<<<<<<< HEAD
                bw.write(u.getDni() + ";" + u.getNom() + ";" + u.getEmail() + ";" + u.esSoci() + ";" + u.getMesosMembresia());
=======
                bw.write(u.getDni() + ";" + u.getNom() + ";" + u.getEmail() + ";" + u.esSoci() + ";" + u.getMesosMembresia() + ";" + u.getParticipaciones());
>>>>>>> main
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al guardar usuarios", e);
        }
    }
<<<<<<< HEAD
=======

>>>>>>> main
    /**
    * Carga los usuarios desde el fichero CSV.
    * @return lista de usuarios cargados desde el fichero
    * @throws PersistenciaException si ocurre un error al leer el fichero
    */
    public static ArrayList<Usuari> carregarUsuaris() throws PersistenciaException {
        crearCarpeta();
        ArrayList<Usuari> usuaris = new ArrayList<>();
        File f = new File(fitxerUsuaris);
        if (!f.exists()) {
            return usuaris;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] d = linia.split(";");
                String dni = d[0];
                String nom = d[1];
                String email = d[2];
                boolean soci = Boolean.parseBoolean(d[3]);
                int mesos = Integer.parseInt(d[4]);
<<<<<<< HEAD
                Usuari u = new Usuari(dni, nom, email);
                if (soci) {
                    u.ferSoci(mesos);
                }
=======
                int participaciones = 0;
                if (d.length > 5) {
                    participaciones = Integer.parseInt(d[5]);
                }
                Usuari u = new Usuari(dni, nom, email);

                if (soci) {
                    u.ferSoci(mesos);
                }
                for (int i = 0; i < participaciones; i++) {
                    u.incrementarParticipaciones();
                }
>>>>>>> main
                usuaris.add(u);
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al cargar usuarios", e);
        }
        return usuaris;
    }

    /**
     * Guarda las actividades en un fichero CSV incluyendo su tipo.
     * @param activitats lista de actividades a guardar
     * @throws PersistenciaException si ocurre un error al escribir en el fichero
     */
    public static void guardarActivitats(ArrayList<Activitat> activitats) throws PersistenciaException {
        crearCarpeta();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerActivitats))) {
            for (Activitat a : activitats) {
                String tipus;
                String extra = "-";
                if (a instanceof Torneig) {
                    tipus = "TORNEIG";
                } else {
                    tipus = "CURS";
                    extra = ((CursPintura) a).getProfessor();
                }
                bw.write(a.getNom() + ";" + a.getData() + ";" + tipus + ";" + extra);
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al guardar actividades", e);
        }
    }

    /**
    * Carga las actividades desde el fichero CSV respetando su tipo.
    * @return lista de actividades cargadas desde el fichero
    * @throws PersistenciaException si ocurre un error al leer el fichero
    */
    public static ArrayList<Activitat> carregarActivitats() throws PersistenciaException {
        crearCarpeta();
        ArrayList<Activitat> activitats = new ArrayList<>();
        File f = new File(fitxerActivitats);
        if (!f.exists()) {
            return activitats;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] d = linia.split(";");
                String nom = d[0];
                LocalDate data = LocalDate.parse(d[1]);
                String tipus = d[2];
                Activitat a;
                if (tipus.equals("TORNEIG")) {
                    a = new Torneig(nom, data);
                } else {
                    String professor = d[3];
                    a = new CursPintura(nom, data, professor);
                }
                activitats.add(a);
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al cargar actividades", e);
        }
        return activitats;
    }

    
    /**
     * Guarda las asignaciones de baldas en un fichero CSV.
     * @param assignacons lista de asignaciones a guardar
     * @throws PersistenciaException si ocurre un error al escribir en el fichero
     */
    public static void guardarAssignacions(ArrayList<Asignacion> assignacons) throws PersistenciaException {
        crearCarpeta();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerAssignacions))) {
            for (Asignacion a : assignacons) {
                bw.write(a.getBalda().getId() + ";" +
                         a.getSocio().getDni() + ";" +
                         a.getFechaAsignacion() + ";" +
                         a.getFechaVencimiento() + ";" +
                         a.isActiva());
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al guardar asignaciones", e);
        }
    }

    /**
     * Carga las asignaciones de baldas desde el fichero CSV.
     * @return lista de asignaciones en formato de datos separados
     * @throws PersistenciaException si ocurre un error al leer el fichero
     */
    public static ArrayList<String[]> carregarAssignacions() throws PersistenciaException {
        crearCarpeta();
<<<<<<< HEAD
        ArrayList<String[]> assignacons = new ArrayList<>();
        File f = new File(fitxerAssignacions);
        if (!f.exists()) {
            return assignacons;
=======
        ArrayList<String[]> assignacions = new ArrayList<>();
        File f = new File(fitxerAssignacions);
        if (!f.exists()) {
            return assignacions;
>>>>>>> main
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linia;
            while ((linia = br.readLine()) != null) {
<<<<<<< HEAD
                assignacons.add(linia.split(";"));
=======
                assignacions.add(linia.split(";"));
>>>>>>> main
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al cargar asignaciones", e);
        }
<<<<<<< HEAD
        return assignacons;
=======
        return assignacions;
>>>>>>> main
    }
}