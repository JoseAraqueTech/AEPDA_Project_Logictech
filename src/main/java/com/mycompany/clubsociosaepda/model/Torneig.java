/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.model;
import java.time.LocalDate;

/**
 *
 * @author juan-
 */

/**
 * Representa una actividad de tipo torneo.
 * En los torneos, si el número de participantes es impar,
 * uno de ellos no juega. Además, siempre se compra comida.
 * @author Andrés/Juan/Jose/Enric
 */
public class Torneig extends Activitat {

    /**
     * Constructor del torneo.
     * @param nom nombre del torneo
     * @param data fecha del torneo
     */
    public Torneig(String nom, LocalDate data) {
        super(nom, data);
    }

    /**
     * Gestiona el funcionamiento del torneo.
     * Comprueba si el número de participantes es par o impar
     * y muestra la información correspondiente.
     */
   @Override
    public String gestionarActivitat() {
    String resultat = "";
     int total = participants.size();

        if (total == 0) {
        resultat += "No hi ha participants en el torneig.\n";
    } else {
        if (total % 2 == 0) {
            resultat += "Tots els participants juguen.\n";
        } else {
            resultat += "Un participant queda sense jugar.\n";
        }
        resultat += "S'ha comprat menjar per al torneig.\n";
  }

    return resultat;
}

}