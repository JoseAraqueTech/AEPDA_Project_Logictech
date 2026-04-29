/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.model;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Representa un usuario del club con identificación,
 * información de contacto y estado de membresía.
 * Controla también el número de participaciones en actividades.
 */

public class Usuari {

    protected String dni;
    protected String nom;
    protected String email;
    protected boolean soci;
    protected int mesosMembresia;
    private int participaciones;

     /**
     * Constructor del usuario.
     * @param dni DNI del usuario
     * @param nom nombre del usuario
     * @param email correo electrónico
     */
    public Usuari(String dni, String nom, String email) {
        this.dni = dni;
        this.nom = nom;
        this.email = email;
        this.soci = false;
        this.mesosMembresia = 0;
        this.participaciones = 0;
    }

    /**
     * Obtiene el DNI del usuario.
     * @return el DNI en formato mayúsculas
     */
    public String getDni() {
        return dni;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return el nombre del usuario
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtiene el email del usuario.
     * @return el correo electrónico
     */
    public String getEmail() {
        return email;
    }

    /**
     * Comprueba si el usuario es socio.
     * @return true si es socio, false en caso contrario
     */
    public boolean esSoci() {
        return soci;
    }

    /**
     * Obtiene los meses de membresía.
     * @return número de meses de membresía
     */
    public int getMesosMembresia() {
        return mesosMembresia;
    }

    /**
<<<<<<< HEAD
=======
     * Obtiene las participaciones del usuario
     * @return número de participaciones del usuario
     */
    public int getParticipaciones() {
        return participaciones;
    }
    
    /**
>>>>>>> main
     * Convierte al usuario en socio durante un número de meses.
     * @param mesos número de meses de membresía
     */
    public void ferSoci(int mesos) {
        soci = true;
        mesosMembresia = mesos;
    }

    /**
     * Finaliza la membresía del usuario.
     */
    public void finalitzarMembresia() {
        soci = false;
        mesosMembresia = 0;
    }

    /**
     * Indica si el usuario puede participar.
     * @return true si es socio o tiene menos de 3 participaciones
     */
    public boolean potParticipar() {
        return soci || participaciones < 3;
    }

    /**
     * Incrementa el número de participaciones.
     */
    public void incrementarParticipaciones() {
        participaciones++;
    }

    public boolean necesitaSerSoci() {
    return !soci && participaciones >= 3;
    }
    
    /**
     * Valida el formato de un email.
     * @param email email a validar
     * @return true si es válido
     */
    public static boolean esEmailValid(String email) {
        Pattern p = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        return p.matcher(email).matches();
    }

    /**
     * Valida un DNI usando el algoritmo oficial de la letra de control.
     * @param nif el DNI a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean esDniValid(String nif) {
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
