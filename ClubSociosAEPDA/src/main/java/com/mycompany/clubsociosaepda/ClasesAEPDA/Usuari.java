/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.ClasesAEPDA;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Represents a club user with identification, contact information and membership status.
 * 
 * This class stores the user's DNI, name, email and membership details. It also
 * validates the DNI using the official control letter algorithm.
 * 
 *
 * @author Andrés/Juan/Jose/Enric
 * @version 1.0
 * @since 2026-04-08
 */
/**
     * Creates a new user with the specified DNI, name and email.
     * The DNI is validated before being stored.
     *
     * @param dni the user's DNI in uppercase format
     * @param nom the user's name
     * @param email the user's email address
     * @throws IllegalArgumentException if the DNI is not valid
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
        /**
     * Gets the user's DNI.
     *
     * @return the DNI in uppercase format
     */


    public String getDni() {
        return dni;
    }
    /**
     * Gets the user's name.
     *
     * @return the user's name
     */

    public String getNom() {
        return nom;
    }
    /**
     * Gets the user's email.
     *
     * @return the user's email address
     */

    public String getEmail() {
        return email;
    }
    /**
     * Checks whether the user is currently a club member.
     *
     * @return true if the user is a member, false otherwise
     */

    public boolean esSoci() {
        return soci;
    }
    /**
     * Gets the number of months of active membership.
     *
     * @return the membership duration in months
     */

    public int getMesosMembresia() {
        return mesosMembresia;
    }
    /**
     * Activates the user's membership for the specified number of months.
     *
     * @param mesos the number of months of membership
     */

    public void ferSoci(int mesos) {
        soci = true;
        mesosMembresia = mesos;
    }
    /**
     * Ends the user's membership and resets the membership duration.
     */

    public void finalitzarMembresia() {
        soci = false;
        mesosMembresia = 0;
    }
        /**
     * Validates a DNI using the official control letter algorithm.
     *
     * @param nif the DNI to validate
     * @return true if the DNI is valid, false otherwise
     */

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
