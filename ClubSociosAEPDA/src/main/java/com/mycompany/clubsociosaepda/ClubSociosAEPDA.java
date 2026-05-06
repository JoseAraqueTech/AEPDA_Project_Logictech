/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.clubsociosaepda;

import java.io.IOException;
import java.util.Scanner;

/**
 * Entry point of the Club AEPDA management application.
 * <p>
 * This class initializes the system and launches the main menu loop.
 * </p>
 *
 * @author Andrés/Jose/Enric/Juan
 * @version 1.0
 * @since 2026-04-08
 */


public class ClubSociosAEPDA {
    /**
     * Starts the application by creating a club manager and displaying the menu.
     *
     * @param args command-line arguments (not used)
     * @throws IOException if loading or saving data fails
     */

    public static void main(String[] args) throws IOException {
        GestorClub gestor = new GestorClub();
        Scanner sc = new Scanner(System.in);
        int opcio;
        do {
            gestor.menu();
            if (!sc.hasNextInt()) {
                System.out.println("Opcio incorrecta.");
                sc.nextLine();
                opcio = -1;
            } else {
                opcio = sc.nextInt();
                sc.nextLine();
                switch (opcio) {
                    case 1:
                        gestor.altaUsuari();
                        break;
                    case 2:
                        gestor.ferSoci();
                        break;
                    case 3:
                        gestor.finalitzarMembresia();
                        break;
                    case 4:
                        gestor.altaActivitat();
                        break;
                    case 5:
                        gestor.eliminarActivitat();
                        break;
                    case 6:
                        gestor.inscriureActivitat();
                        break;
                    case 7:
                        gestor.mostrarActivitats();
                        break;
                    case 8:
                        gestor.mostrarActivitatEspecifica();
                        break;
                    case 0:
                        gestor.guardar();
                        System.out.println("Sortint de la aplicacio de gestio AEPDA...");
                        break;
                    default:
                        System.out.println("Opcio incorrecta.");
                }
            }
        } while (opcio != 0);
    }
}
