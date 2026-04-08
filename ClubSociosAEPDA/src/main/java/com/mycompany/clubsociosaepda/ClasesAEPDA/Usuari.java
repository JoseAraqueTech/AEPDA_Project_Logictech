/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.ClasesAEPDA;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 *
 * @author juan-
 */
public class Usuari {
    private String dni;
    private String nom;
    private String email;

    private boolean soci;
    private int mesosMembresia;

    public Usuari(String dni, String nom, String email) {
        if (!validateNif(dni.toUpperCase())){
            throw new IllegalArgumentException("DNI  no vàlid." +  dni);
        }
        this.dni = dni;
        this.nom = nom;
        this.email = email;
        this.soci = false;
        this.mesosMembresia = 0;
    }

    public String getDni() {
        return dni;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }

    public boolean esSoci() {
        return soci;
    }

    public int getMesosMembresia() {
        return mesosMembresia;
    }

    public void ferSoci(int mesos) {
        soci = true;
        mesosMembresia = mesos;
    }

    public void finalitzarMembresia() {
        soci = false;
        mesosMembresia = 0;
    }
    private boolean validateNif(String nif) {
        Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
        String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
        String[] INVALIDOS = new String[]{"00000000T", "00000001R", "99999999R"};

        return Arrays.binarySearch(INVALIDOS, nif) < 0
                && REGEXP.matcher(nif).matches()
                && nif.charAt(8) == DIGITO_CONTROL.charAt(
                        Integer.parseInt(nif.substring(0, 8)) % 23
                );
    }
}
