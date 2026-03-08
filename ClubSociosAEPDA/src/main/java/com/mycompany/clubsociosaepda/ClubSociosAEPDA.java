/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clubsociosaepda;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author josea
 */
public class ClubSociosAEPDA {
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
                        gestor.activarMembresia();
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 0:
                        System.out.println("Sortint de la aplicacio de gestio AEPDA...");
                        break;
                    default:
                        System.out.println("Opcio incorrecta.");
                }
            }
        } while (opcio != 0);
    }
}

