/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.exception;

/**
 * Excepción general del sistema AEPDA.
 * Se utiliza para errores de lógica de la aplicación.
 */
public class AEDPAException extends Exception {
    public AEDPAException(String mensaje) {
        super(mensaje);
    }
}
