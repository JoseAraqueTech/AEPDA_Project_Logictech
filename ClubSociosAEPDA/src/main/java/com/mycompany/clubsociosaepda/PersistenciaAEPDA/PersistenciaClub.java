/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.PersistenciaAEPDA;

import com.mycompany.clubsociosaepda.ClasesAEPDA.Activitat;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Asignacion;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Balda;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Usuari;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles file-based persistence for the club.
 * Saves and loads users, activities and shelf assignments to/from CSV files.
 * 
 * @author juan-
 */
public class PersistenciaClub {

    private static final String carpeta = "AEPDA";
    private static final String separador = File.separator;
    private static final String fitxerUsuaris = carpeta + separador + "usuaris.csv";
    private static final String fitxerActivitats = carpeta + separador + "activitats.csv";
    private static final String fitxerAssignacions = carpeta + separador + "assignacions.csv";

    /** Creates the data folder if it doesn't exist */
private static void crearCarpeta() {
        File c = new File(carpeta);
        if (!c.exists()) {
            c.mkdir();
        }
    }

    /** Saves all users to file */
public static void guardarUsuaris(ArrayList<Usuari> usuaris) throws IOException {
        crearCarpeta();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerUsuaris));
        for (Usuari u : usuaris) {
            bw.write(
                    u.getDni() + ";"
                    + u.getNom() + ";"
                    + u.getEmail() + ";"
                    + u.esSoci() + ";"
                    + u.getMesosMembresia()
            );
            bw.newLine();
        }
        bw.close();
    }

    /** @return list of saved users */
public static ArrayList<Usuari> carregarUsuaris() throws IOException {
        crearCarpeta();
        ArrayList<Usuari> usuaris = new ArrayList<>();
        File f = new File(fitxerUsuaris);
        if (!f.exists()) {
            return usuaris;
        } else {
            BufferedReader br = new BufferedReader(new FileReader(f));
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
            br.close();
        }
        return usuaris;
    }

    /** Saves all activities to file */
public static void guardarActivitats(ArrayList<Activitat> activitats) throws IOException {
        crearCarpeta();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerActivitats));
        for (Activitat a : activitats) {
            bw.write(
                    a.getNom() + ";"
                    + a.getData()
            );
            bw.newLine();
        }
        bw.close();
    }

    /** @return list of saved activities */
public static ArrayList<Activitat> carregarActivitats() throws IOException {
        crearCarpeta();
        ArrayList<Activitat> activitats = new ArrayList<>();
        File f = new File(fitxerActivitats);
        if (!f.exists()) {
            return activitats;
        } else {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] d = linia.split(";");
                String nom = d[0];
                String data = d[1];
                activitats.add(new Activitat(nom, data));
            }
            br.close();
        }
        return activitats;
}

    /** Saves all assignments to file */
    public static void guardarAssignacons(List<Asignacion> assignacions) throws IOException {
        crearCarpeta();
        BufferedWriter bw = new BufferedWriter(new FileWriter(fitxerAssignacions));
        for (Asignacion a : assignacions) {
            bw.write(
                    a.getBalda().getId() + ";"
                    + a.getSocio().getDni() + ";"
                    + a.getFechaAsignacion() + ";"
                    + a.getFechaVencimiento() + ";"
                    + a.isActiva()
            );
            bw.newLine();
        }
bw.close();
    }

    /** @return list of saved assignments as string arrays */
    public static ArrayList<String[]> carregarAssignacons() throws IOException {
        crearCarpeta();
        ArrayList<String[]> assignacons = new ArrayList<>();
        File f = new File(fitxerAssignacions);
        if (!f.exists()) {
            return assignacons;
        } else {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String linia;
            while ((linia = br.readLine()) != null) {
                String[] d = linia.split(";");
                assignacons.add(d);
            }
            br.close();
        }
        return assignacons;
    }
}

