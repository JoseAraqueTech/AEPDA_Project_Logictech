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
    private static GestorClub gestor;
    private static Scanner sc;

    public static void main(String[] args) throws IOException {
        gestor = new GestorClub();
        sc = new Scanner(System.in);
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
                    case 9:
                        mostrarBaldes();
                        break;
                    case 10:
                        assignarBalda();
                        break;
                    case 11:
                        alliberarBalda();
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
    
 private static void mostrarBaldes() {
    System.out.println(gestor.mostrarBaldes());
}

private static void assignarBalda() {
    System.out.print("DNI del soci: ");
    String dni = sc.nextLine();
    System.out.print("Codi de la balda (ex: A-1): ");
    String codi = sc.nextLine();
    System.out.println(gestor.assignarBalda(dni, codi));
}

private static void alliberarBalda() {
    System.out.print("Codi de la balda a alliberar: ");
    String codi = sc.nextLine();
    System.out.println(gestor.alliberarBalda(codi));
}
}
