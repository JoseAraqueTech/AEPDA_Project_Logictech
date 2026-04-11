package com.mycompany.clubsociosaepda.ExceptionAEPDA;

public class PersistenciaException extends Exception {
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }

    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}