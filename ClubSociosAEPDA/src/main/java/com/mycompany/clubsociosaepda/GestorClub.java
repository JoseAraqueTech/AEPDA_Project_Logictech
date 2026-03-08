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

    private Usuari buscarUsuari(String Nom) {
        for (Usuari u : usuaris) {
            if (u.getNom().equalsIgnoreCase(Nom)) {
                return u;
            }
        }
        return null;
    }
    
    private Usuari buscarDni(String Dni) {
        for (Usuari u : usuaris) {
            if (u.getDni().equalsIgnoreCase(Dni)) {
                return u;
            }
        }
        return null;
    }

    private String demanarText(String msg) {
        String t;
        do {
            System.out.print(msg);
            t = sc.nextLine();
            if (t.isEmpty()) {
                System.out.println("ERROR: No pot estar buit.");
            }
        } while (t.isEmpty());
        return t;
    }

    private int demanarIntPositiu(String msg) {
        int n = 0;
        do {
            System.out.print(msg);
            if (sc.hasNextInt()) {
                n = sc.nextInt();
            } else {
                System.out.println("ERROR: Introdueix un numero valid.");
            }
            sc.nextLine();
        } while (n <= 0);
        return n;
    }

    private String demanarDni() {
        String dni;
        boolean correcte;

        do {
            correcte = true;
            dni = demanarText("DNI: ");

            int llargada = dni.length();

            // Comprobar longitud
            if (llargada < 8 || llargada > 9) {
                correcte = false;
            } else {
                int pos = 0;

                // Comprueba que todos son digitos excepto el ultimo
                for (char c : dni.toCharArray()) {
                    if (pos < llargada - 1) {
                        if (c < '0' || c > '9') {
                            correcte = false;
                        }
                    }
                    pos++;
                }

                // Comprueba que el ultimo sea una letra en mayúsculas
                char ultim = dni.toCharArray()[llargada - 1];
                boolean maj = (ultim >= 'A' && ultim <= 'Z');

                if (!maj) {
                    correcte = false;
                }
            }

            if (!correcte) {
                System.out.println("ERROR: Format incorrecte. Exemple: 12345678Z");
            }

        } while (!correcte);

        return dni;
    }

    public void altaUsuari() {
        String nom = demanarText("Nom: ");
        if (buscarUsuari(nom) == null) {
            String dni = demanarDni();
            String email = demanarText("Email: ");
            usuaris.add(new Usuari(dni, nom, email));
            System.out.println("Usuari registrat.");
        } else {
            System.out.println("ERROR: Ja existeix aquesta matricula.");
        }
    }

    public void activarMembresia() {
        String mat = demanarText("Dni: ");
        Usuari c = buscarDni(mat);
        if (c != null) {
             System.out.print("Introdueix els mesos de membresia: ");
                Scanner sc = new Scanner(System.in);
                int mesos = sc.nextInt();

                c.ferSoci(mesos);

                System.out.println("L'usuari ara és soci.");
                return; // Termina el método
        } else {
            System.out.println("ERROR: Usuari no trobat.");
        }
    }


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