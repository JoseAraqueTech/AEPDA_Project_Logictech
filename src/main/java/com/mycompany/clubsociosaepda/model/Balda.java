package com.mycompany.clubsociosaepda.model;

/**
 * Representa una balda del almacén del club.
 * Almacena el identificador, la ubicación, el estado (ocupada o libre)
 * y la asignación actual en caso de estar ocupada.
 * @author josea
 */
public class Balda {
    private int id;
    private String ubicacion;
    private boolean ocupada;

    /**
     * Constructor de la balda.
     * @param id identificador de la balda
     * @param ubicacion ubicación de la balda
     */
    public Balda(int id, String ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.ocupada = false;
    }

    /**
     * Obtiene el identificador de la balda.
     * @return id de la balda
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    

    /**
     * Obtiene la ubicación de la balda.
     * @return ubicación de la balda
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Indica si la balda está ocupada.
     * @return true si está ocupada, false en caso contrario
     */
    public boolean estaOcupada() {
        return ocupada;
    }


    /**
     * Obtiene el estado de la balda en formato texto.
     * @return "ocupada" si está ocupada o "libre" si está disponible
     */
    public String getEstado() {
        return ocupada ? "ocupada" : "libre";
    }
    
    @Override
    public String toString() {
        return "Balda " + id + " - " + ubicacion;
    }
}