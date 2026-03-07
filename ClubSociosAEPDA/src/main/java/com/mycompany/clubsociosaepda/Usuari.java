/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda;

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
}
