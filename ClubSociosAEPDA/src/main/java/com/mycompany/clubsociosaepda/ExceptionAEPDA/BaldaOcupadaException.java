package com.mycompany.clubsociosaepda.ExceptionAEPDA;

/**
 * Catch an already assigned balda
 * Catch if the user introduce an ID of a balda that is already assigned to other user
 * 
 * 
 * @author josea
 */
public class BaldaOcupadaException extends Exception {
    public BaldaOcupadaException(int idBalda) {
        super("La balda con ID " + idBalda + " ya está ocupada");
    }
}