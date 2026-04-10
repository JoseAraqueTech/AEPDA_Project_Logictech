package com.mycompany.clubsociosaepda.ClasesAEPDA;

import java.time.LocalDate;

/**
 * Represents a shelf in the club storage.
 * Stores the shelf ID, location, occupancy status and current assignment.
 * 
 * @author josea
 */
public class Balda {
    private final int id;
    private final String ubicacion;
    private boolean ocupada;
    private Asignacion asignacionActual;

    /**
 * Creates a new shelf with the given ID and location.
 * 
 * @param id the shelf ID
 * @param ubicacion the shelf location
 */
public Balda(int id, String ubicacion) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.ocupada = false;
        this.asignacionActual = null;
    }

    /**
     * Gets the Balda's ID.
     * 
     * @return the balda's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the balda's location.
     * 
     * @return the balda's location 
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Gets the balda's state assigned
     * 
     * @return the balda's state assigned
     */
    public boolean estaOcupada() {
        return ocupada;
    }

    /**
     * Gets the balda's actual assignment
     * 
     * @return the balda's actual assignment
     */
    public Asignacion getAsignacionActual() {
        return asignacionActual;
    }

    /**
 * Assigns a shelf to a member with the given assignment data.
 * 
 * @param asignacion the assignment object
 */
public void asignar(Asignacion asignacion) {
        this.ocupada = true;
        this.asignacionActual = asignacion;
    }

    /**
     * Gets the balda's state
     * 
     * Gets the balda's state from assigned to free
     */
    public void liberar() {
        this.ocupada = false;
        this.asignacionActual = null;
    }

    /**
     * Gets the balda's state
     * 
     * @return the balda's state assigned or free
     */
    public String getEstado() {
        return ocupada ? "ocupada" : "libre";
    }
}