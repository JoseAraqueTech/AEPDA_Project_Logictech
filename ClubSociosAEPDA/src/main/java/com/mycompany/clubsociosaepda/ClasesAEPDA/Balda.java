/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.ClasesAEPDA;

/**
 *
 * @author andre
 */
public class Balda {
    
    public String codi;
    private boolean ocupada;
    private Usuari assignada;
    
    public Balda(String codi) {
        this.codi = codi;
        this.ocupada = false;
        this.assignada = null;
    }
    
    public String getcodi(){
        return codi;
    }
    public boolean isOcupada(){
        return ocupada;
    }
    public Usuari getAssignada(){
        return assignada;
    }
    
    public void assignar(Usuari u){
        this.ocupada =  true;
        this.assignada = u;
    }
    public void alliberar() {
        this.ocupada = false;
        this.assignada = null;
    }
    @Override
    public String toString() {
        if (ocupada) {
            return codi + " -Ocupada per: " + assignada.getNom();
        }else{
            return codi + " - Lliure";
        }
    }
}
