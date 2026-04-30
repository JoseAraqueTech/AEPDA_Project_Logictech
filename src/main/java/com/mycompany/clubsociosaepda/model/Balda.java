package com.mycompany.clubsociosaepda.model;

/**
 * Representa una balda del almacén del club.
 * Almacena el identificador, la ubicación, el estado (ocupada o libre)
 * y la asignación actual en caso de estar ocupada.
 * @author josea
 */
public class Balda {
    private final int id;
    private final String ubicacion;
    private boolean ocupada;
    private Asignacion asignacionActual;

    /**
     * Constructor de la balda.
     * @param id identificador de la balda
     * @param ubicacion ubicación de la balda
     */
    public Balda(int id, String ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.ocupada = false;
        this.asignacionActual = null;
    }

    /**
     * Obtiene el identificador de la balda.
     * @return id de la balda
     */
    public int getId() {
        return id;
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
     * Obtiene la asignación actual de la balda.
     * @return asignación actual o null si está libre
     */
    public Asignacion getAsignacionActual() {
        return asignacionActual;
    }

    /**
     * Asigna la balda a un usuario mediante una asignación.
     * @param asignacion objeto de asignación
     */
    public void asignar(Asignacion asignacion) {
            this.ocupada = true;
            this.asignacionActual = asignacion;
    }

    /**
     * Libera la balda dejándola disponible.
     */
    public void liberar() {
        this.ocupada = false;
        this.asignacionActual = null;
    }

    /**
     * Obtiene el estado de la balda en formato texto.
     * @return "ocupada" si está ocupada o "libre" si está disponible
     */
    public String getEstado() {
        return ocupada ? "ocupada" : "libre";
    }
}