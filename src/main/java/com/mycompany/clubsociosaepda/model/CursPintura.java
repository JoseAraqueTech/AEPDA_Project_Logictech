/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.model;

import com.mycompany.clubsociosaepda.model.enums.NivellPintura;
import java.time.LocalDate;

/**
 * Representa una actividad de tipo curso de pintura. Este tipo de actividad
 * tiene un profesor y no incluye compra de comida.
 *
 * @author Andrés/Juan/Jose/Enric
 */
public class CursPintura extends Activitat {

    private String professor;
    private NivellPintura nivell;

    /**
     * Constructor del curso de pintura.
     *
     * @param id_activitat identificador de la actividad
     * @param nom nombre del curso
     * @param data fecha del curso
     * @param professor nombre del profesor
     * @param nivell nivel del curso de pintura
     */
    public CursPintura(int id_activitat, String nom, LocalDate data, String professor, NivellPintura nivell) {
        super(id_activitat, nom, data);
        this.professor = professor;
        this.nivell = nivell;
    }

    /**
     * Obtiene el nombre del profesor.
     *
     * @return nombre del profesor
     */
    public String getProfessor() {
        return professor;
    }
    
    /**
     * Obtiene el nivel del curso de pintura
     *
     * @return nivel del curso de pintura
     */
    public NivellPintura getNivell() {
        return nivell;
    }

    /**
     * Gestiona el funcionamiento del curso.
     */
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
}
