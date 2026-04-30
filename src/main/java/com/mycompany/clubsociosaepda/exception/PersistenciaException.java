package com.mycompany.clubsociosaepda.exception;

/**
 * Excepción para errores de lectura/escritura en ficheros.
 */
public class PersistenciaException extends Exception {

    public PersistenciaException(String mensaje) {
        super(mensaje);
    }

    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}