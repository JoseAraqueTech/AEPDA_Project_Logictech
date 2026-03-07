/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda;

import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author juan-
 */
public class GestorClub {

    private ArrayList<Usuari> usuaris;
    private ArrayList<Activitat> activitats;
    private Scanner sc;

    public void menu() {
        System.out.println("----- Gestio Club AEPDA -----");
        System.out.println("1. Alta usuari");
        System.out.println("2. Fer soci a usuari");
        System.out.println("3. Finalitzar membresia");
        System.out.println("4. Alta activitat");
        System.out.println("5. Inscriure soci a activitat");
        System.out.println("6. Mostrar activitats");
        System.out.println("7. Mostrar activitat especifica");
        System.out.println("8. Guardar dades");
        System.out.println("0. Sortir");
        System.out.print("Opcio: ");
    }
}
