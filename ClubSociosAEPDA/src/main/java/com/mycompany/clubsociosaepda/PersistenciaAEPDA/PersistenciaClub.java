/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.PersistenciaAEPDA;

import com.mycompany.clubsociosaepda.ClasesAEPDA.Activitat;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Asignacion;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Balda;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Usuari;
import com.mycompany.clubsociosaepda.ExceptionAEPDA.PersistenciaException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PersistenciaClub {

    private static final String carpeta = "AEPDA";
    private static final String separador = File.separator;
    private static final String fitxerUsuaris = carpeta + separador + "usuaris.csv";
    private static final String fitxerActivitats = carpeta + separador + "activitats.csv";
    private static final String fitxerAssignacions = carpeta + separador + "assignacions.csv";

    private static void crearCarpeta() {
        File c = new File(carpeta);
        if (!c.exists()) {
            c.mkdir();
        }
    }

    public static void guardarUsuaris(ArrayList<Usuari> usuaris) throws PersistenciaException {
        crearCarpeta();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerUsuaris))) {
            for (Usuari u : usuaris) {
                bw.write(u.getDni() + ";" + u.getNom() + ";" + u.getEmail() + ";" + u.esSoci() + ";" + u.getMesosMembresia());
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al guardar usuarios", e);
        }
    }

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
                Usuari u = new Usuari(dni, nom, email);
                if (soci) {
                    u.ferSoci(mesos);
                }
                usuaris.add(u);
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al cargar usuarios", e);
        }
        return usuaris;
    }

    public static void guardarActivitats(ArrayList<Activitat> activitats) throws PersistenciaException {
        crearCarpeta();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerActivitats))) {
            for (Activitat a : activitats) {
                bw.write(a.getNom() + ";" + a.getData());
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al guardar actividades", e);
        }
    }

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
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate data = LocalDate.parse(d[1], df);
                activitats.add(new Activitat(nom, data));
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al cargar actividades", e);
        }
        return activitats;
    }

    public static void guardarAssignacions(List<Asignacion> assignacons) throws PersistenciaException {
        crearCarpeta();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerAssignacions))) {
            for (Asignacion a : assignacons) {
                bw.write(a.getBalda().getId() + ";" + a.getSocio().getDni() + ";" + a.getFechaAsignacion() + ";" + a.getFechaVencimiento() + ";" + a.isActiva());
                bw.newLine();
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al guardar asignaciones", e);
        }
    }

    public static ArrayList<String[]> carregarAssignacions() throws PersistenciaException {
        crearCarpeta();
        ArrayList<String[]> assignacons = new ArrayList<>();
        File f = new File(fitxerAssignacions);
        if (!f.exists()) {
            return assignacons;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] d = linia.split(";");
                assignacons.add(d);
            }
        } catch (java.io.IOException e) {
            throw new PersistenciaException("Error al cargar asignaciones", e);
        }
        return assignacons;
    }
}