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
                        gestor.mostrarBaldas();
                        break;
                    case 10:
                        gestor.mostrarDisponibilidadBaldas();
                        break;
                    case 11:
                        gestor.asignarBalda();
                        break;
                    case 12:
                        gestor.liberarBalda();
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
