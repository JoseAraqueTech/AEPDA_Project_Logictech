package com.mycompany.clubsociosaepda.ExceptionAEPDA;

/**
 * Catch if user introduce wrong ID
 * Catch if the user introduce a wrong ID of a balda
 * @author josea
 */
public class BaldaNoEncontradaException extends Exception {
    public BaldaNoEncontradaException(int idBalda) {
        super("No se encontró la balda con ID " + idBalda);
    }
}