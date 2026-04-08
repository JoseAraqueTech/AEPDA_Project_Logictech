/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.PersistenciaAEPDA;

import com.mycompany.clubsociosaepda.ClasesAEPDA.Activitat;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Usuari;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;


/**
 *
 * @author juan-
 */
public class PersistenciaClub {

    private static final String carpeta = "AEPDA";
    private static final String separador = File.separator;
    private static final String fitxerUsuaris = carpeta + separador + "usuaris.csv";
    private static final String fitxerActivitats = carpeta + separador + "activitats.csv";

    private static void crearCarpeta() {
        File c = new File(carpeta);
        if (!c.exists()) {
            c.mkdir();
        }
    }

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
            LocalDate data = LocalDate.parse(d[1]); // ← CONVERSIÓ CORRECTA

            activitats.add(new Activitat(nom, data));
        }
        br.close();
    }
    return activitats;
}

}

