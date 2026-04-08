/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.ClasesAEPDA;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author juan-
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

    public String getNom() {
        return nom;
    }

    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data){
       this.data = data;
    }

    public ArrayList<Usuari> getParticipants() {
        return participants;
    }

    public void afegirParticipant(Usuari u) {
        participants.add(u);
    }
}
