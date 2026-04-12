/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.model;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Clase abstracta que representa una actividad del club.
 * Contiene el nombre, la fecha y la lista de participantes.
 */
public abstract class Activitat {
    
    protected String nom;
    protected LocalDate data;
    protected ArrayList<Usuari> participants;

    /**
     * Constructor de la actividad.
     * @param nom nombre de la actividad
     * @param data fecha de la actividad
     */
    public Activitat(String nom, LocalDate data) {
        this.nom = nom;
        this.data = data;
        this.participants = new ArrayList<>();
    }

    /**
     * Obtiene el nombre de la actividad.
     * @return el nombre de la actividad
     */
    public String getNom() {
        return nom;
    }

    /**
     * Obtiene la fecha de la actividad.
     * @return la fecha de la actividad
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Establece la fecha de la actividad.
     * @param data nueva fecha
     */
    public void setData(LocalDate data){
       this.data = data;
    }

    /**
     * Obtiene los participantes de la actividad.
     * @return lista de participantes
     */
    public ArrayList<Usuari> getParticipants() {
        return participants;
    }

    /**
     * Añade un participante a la actividad.
     * @param u usuario a añadir
     */
    public void afegirParticipant(Usuari u) {
        if (!participants.contains(u)) {
            participants.add(u);
        }
    }

    /**
     * Define el comportamiento de la actividad.
     */
    public abstract void gestionarActivitat();
}
