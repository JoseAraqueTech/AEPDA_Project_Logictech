/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase abstracta que representa una actividad del club.
 * Contiene el nombre, la fecha y la lista de participantes.
 */
public abstract class Activitat {
    
    protected int id_activitat;
    protected String nom;
    protected LocalDate data;
    protected Map<String, Usuari> participants;

    /**
     * Constructor de la actividad.
     * @param id_activitat identificador de la actividad
     * @param nom nombre de la actividad
     * @param data fecha de la actividad
     */
    public Activitat(int id_activitat, String nom, LocalDate data) {
        this.id_activitat = id_activitat;
        this.nom = nom;
        this.data = data;
        this.participants = new HashMap<>();
    }

    /**
     * Obtiene el nombre de la actividad.
     * @return el nombre de la actividad
     */
    public String getNom() {
        return nom;
    }

    public int getIdActivitat() {
        return id_activitat;
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
    public Map<String, Usuari> getParticipants() {
        return participants;
    }

    /**
     * Añade un participante a la actividad.
     * @param id identificador del usuario
     * @param u usuario a añadir
     */
    public void afegirParticipant(String id, Usuari u) {
        if (!participants.containsValue(u)) {
            participants.put(id, u);
        }
    }

    /**
     * Define el comportamiento de la actividad.
     * @return informacion de la actividad
     */
    public abstract String gestionarActivitat();
}