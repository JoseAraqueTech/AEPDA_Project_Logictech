/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.view;
import com.mycompany.clubsociosaepda.controller.GestorClub;
import com.mycompany.clubsociosaepda.exception.AEDPAException;
import com.mycompany.clubsociosaepda.exception.PersistenciaException;
import java.util.Scanner;

/**
 * Clase encargada de la interacción con el usuario.
 * Muestra el menú y gestiona las opciones introducidas.
 */
public class Menu {
    private GestorClub gestor;
    private Scanner sc;

    public Menu() throws PersistenciaException {
        gestor = new GestorClub();
        sc = new Scanner(System.in);
    }

    /**
     * Inicia la ejecución del menú.
     * Muestra las opciones disponibles y procesa la entrada del usuario
     * hasta que se selecciona la opción de salida.
     * @throws PersistenciaException si ocurre un error al guardar los datos
     */
    public void start() throws PersistenciaException {
        int opcio;
        do {
            mostrarMenu();
            if (!sc.hasNextInt()) {
                System.out.println("Opcio incorrecta.");
                sc.nextLine();
                opcio = -1;
            } else {
                opcio = sc.nextInt();
                sc.nextLine();
                try {
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
                            break;
                        default:
                            System.out.println("Opcio incorrecta.");
                    }
                } catch (AEDPAException e) {
                    System.out.println("ERROR: " + e.getMessage());
                }
            }
        } while (opcio != 0);
    }

    /**
    * Muestra por pantalla todas las opciones disponibles del menú.
    */
    private void mostrarMenu() {
        System.out.println("----- Gestio Club AEPDA -----");
        System.out.println("1. Alta usuari");
        System.out.println("2. Fer soci");
        System.out.println("3. Finalitzar membresia");
        System.out.println("4. Alta activitat");
        System.out.println("5. Eliminar activitat");
        System.out.println("6. Inscriure usuari");
        System.out.println("7. Mostrar activitats");
        System.out.println("8. Mostrar activitat concreta");
        System.out.println("9. Mostrar baldes");
        System.out.println("10. Disponibilitat baldes");
        System.out.println("11. Assignar balda");
        System.out.println("12. Alliberar balda");
        System.out.println("0. Sortir");
        System.out.print("Opcio: ");
    }
}
