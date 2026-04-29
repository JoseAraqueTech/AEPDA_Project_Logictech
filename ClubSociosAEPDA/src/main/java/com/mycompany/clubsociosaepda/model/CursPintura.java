/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.model;
import java.time.LocalDate;


/**
 * Representa una actividad de tipo curso de pintura.
 * Este tipo de actividad tiene un profesor y no incluye compra de comida.
 * @author Andrés/Juan/Jose/Enric
 */
public class CursPintura extends Activitat {

    private String professor;
    /**
     * Constructor del curso de pintura.
     * @param nom nombre del curso
     * @param data fecha del curso
     * @param professor nombre del profesor
     */
    public CursPintura(String nom, LocalDate data, String professor) {
        super(nom, data);
        this.professor = professor;
    }

    /**
     * Obtiene el nombre del profesor.
     * @return nombre del profesor
     */
    public String getProfessor() {
        return professor;
    }

    /**
     * Gestiona el funcionamiento del curso.
     */
<<<<<<< HEAD
   @Override
    public String gestionarActivitat() {
    String resultat = "";

      if (participants.isEmpty()) {
        resultat += "No hi ha participants en el curs.\n";
    } else {
        resultat += "Curs impartit per: " + professor + "\n";
        resultat += "Participants inscrits: " + participants.size() + "\n";
  }

      resultat += "En aquest curs no es compra menjar.";

    return resultat;
}
=======
    public void gestionarActivitat() {
        if (participants.isEmpty()) {
            System.out.println("No hi ha participants en el curs.");
        } else {
            System.out.println("Curs impartit per: " + professor);
            System.out.println("Participants inscrits: " + participants.size());
        }

        System.out.println("En aquest curs no es compra menjar.");
    }
>>>>>>> main
}