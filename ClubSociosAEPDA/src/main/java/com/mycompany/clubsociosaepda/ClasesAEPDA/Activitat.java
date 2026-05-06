/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.ClasesAEPDA;

import java.time.LocalDate;
import java.util.ArrayList;

    /**
     * Creates a new activity with the specified name and date.
     *
     * @param nom the activity name
     * @param data the activity date as a LocalDate
     */

public class Activitat {

    private String nom;
    private LocalDate data;
    private ArrayList<Usuari> participants;

    public Activitat(String nom, LocalDate data) {
        this.nom = nom;
        this.data = data;
        this.participants = new ArrayList<>();
    }
    /**
     * Gets the activity name.
     *
     * @return the activity name
     */

    public String getNom() {
        return nom;
    }
    /**
     * Gets the activity date.
     *
     * @return the date of the activity
     */

    public LocalDate getData() {
        return data;
    }
        /**
     * Sets the activity date.
     *
     * @param data the new date for the activity
     */

    public void setData(LocalDate data){
       this.data = data;
    }
    /**
     * Gets the list of participants registered in the activity.
     *
     * @return a list of users participating in the activity
     */

    public ArrayList<Usuari> getParticipants() {
        return participants;
    }
    /**
     * Adds a user as a participant in the activity.
     *
     * @param u the user to add
     */

    public void afegirParticipant(Usuari u) {
        participants.add(u);
    }
}
