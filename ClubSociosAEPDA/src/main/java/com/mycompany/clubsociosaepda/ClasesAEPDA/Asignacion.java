package com.mycompany.clubsociosaepda.ClasesAEPDA;

import java.time.LocalDate;

/**
 * Represents the assignment of a shelf to a member.
 * Stores the shelf, member, assignment dates and active status.
 * 
 * @author josea
 */
public class Asignacion {
    private final Balda balda;
    private final Usuari socio;
    private final LocalDate fechaAsignacion;
    private final LocalDate fechaVencimiento;
    private boolean activa;

    /**
 * Creates a new assignment with the given shelf, member and duration.
 * 
 * @param balda the shelf to assign
 * @param socio the member to assign the shelf to
 * @param mesesDuracion duration in months
 */
public Asignacion(Balda balda, Usuari socio, int mesesDuracion) {
        this.balda = balda;
        this.socio = socio;
        this.fechaAsignacion = LocalDate.now();
        this.fechaVencimiento = fechaAsignacion.plusMonths(mesesDuracion);
        this.activa = true;
    }

    /**
 * @return the assigned shelf
 */
public Balda getBalda() {
        return balda;
    }

    /**
 * @return the assigned member
 */
public Usuari getSocio() {
        return socio;
    }

    /**
 * @return the date the assignment was created
 */
public LocalDate getFechaAsignacion() {
        return fechaAsignacion;
    }

    /**
 * @return the expiration date of the assignment
 */
public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
 * @return true if the assignment is active
 */
public boolean isActiva() {
        return activa;
    }

    /**
 * Cancels the assignment, marking it as inactive
 */
public void cancelar() {
        this.activa = false;
    }
}