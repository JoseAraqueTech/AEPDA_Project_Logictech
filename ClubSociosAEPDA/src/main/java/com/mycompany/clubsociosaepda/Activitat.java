/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda;

import java.util.ArrayList;

/**
 *
 * @author juan-
 */
public class Activitat {

    private String nom;
    private String data;
    private ArrayList<Usuari> participants;

    public Activitat(String nom, String data) {
        this.nom = nom;
        this.data = data;
        this.participants = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getData() {
        return data;
    }

    public ArrayList<Usuari> getParticipants() {
        return participants;
    }

    public void afegirParticipant(Usuari u) {
        participants.add(u);
    }
}
