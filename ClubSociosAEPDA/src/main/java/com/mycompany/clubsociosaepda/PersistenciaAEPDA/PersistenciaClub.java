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
import java.time.format.DateTimeFormatter;


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
    /**
     * Saves all users to the CSV file.
     *
     * @param usuaris the list of users to save
     * @throws IOException if an I/O error occurs while writing the file
     */

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
    /**
     * Loads all users from the CSV file.
     *
     * @return a list of loaded users
     * @throws IOException if an I/O error occurs while reading the file
     */

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

            // Limpieza básica
            if (linia == null) {
                // no debería pasar, pero por seguridad
            } else {
                linia = linia.trim();
                if (!linia.isEmpty()) {

                    String[] d = linia.split(";");

                    // Comprobamos que la línea tiene los 5 campos
                    if (d.length == 5) {
                        String dni = d[0].trim().replace("\uFEFF", "");
                        String nom = d[1].trim();
                        String email = d[2].trim();
                        boolean soci = Boolean.parseBoolean(d[3].trim());
                        int mesos = Integer.parseInt(d[4].trim());

                        Usuari u = new Usuari(dni, nom, email);
                        if (soci) {
                            u.ferSoci(mesos);
                        }
                        usuaris.add(u);
                    } else {
                        // Aquí ves exactamente qué línea está mal formateada
                        System.out.println("Línia amb format incorrecte a usuaris.csv: [" + linia + "]");
                    }
                }
            }
        }
        br.close();
    }
    return usuaris;
}

    /**
     * Saves all activities to the CSV file.
     *
     * @param activitats the list of activities to save
     * @throws IOException if an I/O error occurs while writing the file
     */

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
    /**
     * Loads all activities from the CSV file.
     *
     * @return a list of loaded activities
     * @throws IOException if an I/O error occurs while reading the file
     */

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
            DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(d[1], df);


            activitats.add(new Activitat(nom, data));
        }
        br.close();
    }
    return activitats;
}

}

