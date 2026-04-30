/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.view;
import com.mycompany.clubsociosaepda.controller.GestorClub;
import com.mycompany.clubsociosaepda.exception.AEDPAException;
import com.mycompany.clubsociosaepda.exception.PersistenciaException;
import java.time.LocalDate;
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
                            altaUsuari();
                            break;
                        case 2:
                            ferSoci();
                            break;
                        case 3:
                            finalitzarMembresia();
                            break;
                        case 4:
                            altaActivitat();
                            break;
                        case 5:
                            eliminarActivitat();
                            break;
                        case 6:
                            inscriureActivitat();
                            break;
                        case 7:
                            mostrarActivitats();
                            break;
                        case 8:
                            mostrarActivitatEspecifica();
                            break;
                        case 9:
                            mostrarBaldas();
                            break;
                        case 10:
                            mostrarDisponibilidadBaldas();
                            break;
                        case 11:
                            asignarBalda();
                            break;
                        case 12:
                            liberarBalda();
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
      /**
     * Registers a new user.
     */
    private void altaUsuari() throws AEDPAException {
        String dni = demanarText("DNI: ");
        String nom = demanarText("Nom: ");
        String email = demanarText("Email: ");

        gestor.altaUsuari(dni, nom, email);
        System.out.println("Usuari creat correctament.");
    }

    /**
     * Converts a user into a member.
     */
    private void ferSoci() throws AEDPAException {
        String dni = demanarText("DNI: ");
        int mesos = demanarEnter("Mesos: ");

        gestor.ferSoci(dni, mesos);
        System.out.println("Usuari convertit en soci.");
    }

    /**
     * Ends a user's membership.
     */
    private void finalitzarMembresia() throws AEDPAException {
        String dni = demanarText("DNI: ");
        gestor.finalitzarMembresia(dni);
        System.out.println("Membresia finalitzada.");
    }

    /**
     * Creates a new activity.
     */
    private void altaActivitat() throws AEDPAException {
        String nom = demanarText("Nom activitat: ");
        LocalDate data = LocalDate.parse(demanarText("Data (YYYY-MM-DD): "));
        int tipus = demanarEnter("Tipus (1=Torneig, 2=Curs): ");

        String professor = null;
        if (tipus == 2) {
            professor = demanarText("Professor: ");
        }

        gestor.altaActivitat(nom, data, tipus, professor);
        System.out.println("Activitat creada correctament.");
    }

    /**
     * Deletes an activity.
     */
    private void eliminarActivitat() throws AEDPAException, PersistenciaException {
        String nom = demanarText("Nom activitat: ");
        gestor.eliminarActivitat(nom);
        System.out.println("Activitat eliminada.");
    }

    /**
     * Registers a user in an activity.
     */
    private void inscriureActivitat() throws AEDPAException, PersistenciaException {
        String dni = demanarText("DNI: ");
        String nomAct = demanarText("Nom activitat: ");

        gestor.inscriureActivitat(dni, nomAct);
        System.out.println("Usuari inscrit correctament.");
    }

    /**
     * Displays all activities.
     */
    private void mostrarActivitats() {
        for (String s : gestor.mostrarActivitats()) {
            System.out.println(s);
        }
    }

    /**
     * Displays detailed information of a specific activity.
     */
    private void mostrarActivitatEspecifica() throws AEDPAException {
        String nom = demanarText("Nom activitat: ");
        System.out.println(gestor.mostrarActivitatEspecifica(nom));
    }

    /**
     * Displays all shelves.
     */
    private void mostrarBaldas() {
        for (String s : gestor.mostrarBaldas()) {
            System.out.println(s);
        }
    }

    /**
     * Displays shelf availability.
     */
    private void mostrarDisponibilidadBaldas() {
        System.out.println(gestor.mostrarDisponibilitatBaldas());
    }

    /**
     * Assigns a shelf to a member.
     */
    private void asignarBalda() throws AEDPAException {
        int id = demanarEnter("ID balda: ");
        String dni = demanarText("DNI: ");
        int mesos = demanarEnter("Mesos: ");

        gestor.asignarBalda(id, dni, mesos);
        System.out.println("Balda assignada.");
    }

    /**
     * Frees a shelf.
     */
    private void liberarBalda() throws AEDPAException {
        int id = demanarEnter("ID balda: ");
        gestor.liberarBalda(id);
        System.out.println("Balda alliberada.");
    }

    /**
     * Reads a text input from the user.
     */
    private String demanarText(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }
private int demanarEnterMajorZero(String msg) {
    int n = 0;

    do {
        System.out.print(msg);
        try {
            n = Integer.parseInt(sc.nextLine());
            if (n <= 0) {
                System.out.println("ERROR: Més gran que 0.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: Invalid number.");
        }
    } while (n <= 0);

    return n;
}
    /**
     * Reads an integer input from the user.
     */
    private int demanarEnter(String msg) {
        System.out.print(msg);
        return Integer.parseInt(sc.nextLine());
    }
}