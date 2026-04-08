/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda;

import com.mycompany.clubsociosaepda.ClasesAEPDA.Usuari;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Activitat;
import com.mycompany.clubsociosaepda.PersistenciaAEPDA.PersistenciaClub;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;



/**
 * Provides the main logic for managing users, memberships and activities.
 * <p>
 * This class handles all menu operations, including user registration,
 * membership activation, activity creation, and participant management.
 * It also interacts with the persistence layer to load and save data.
 * </p>
 *
 * @author Andrés/Jose/Enric/Juan
 * @version 1.0
 * @since 2026-04-08
 */


public class GestorClub {

    private ArrayList<Usuari> usuaris;
    private ArrayList<Activitat> activitats;
    private Scanner sc;
    /**
     * Creates a new club manager and loads all users and activities from disk.
     *
     * @throws IOException if an error occurs while loading stored data
     */

    public GestorClub() throws IOException {
        usuaris = PersistenciaClub.carregarUsuaris();
        activitats = PersistenciaClub.carregarActivitats();
        sc = new Scanner(System.in);
    }
    /**
     * Displays the main menu and processes user input until the program ends.
     */

    public void menu() {
        System.out.println("----- Gestio Club AEPDA -----");
        System.out.println("1. Alta usuari");
        System.out.println("2. Fer soci a usuari");
        System.out.println("3. Finalitzar membresia");
        System.out.println("4. Alta activitat");
        System.out.println("5. Eliminar activitat");
        System.out.println("6. Inscriure soci a activitat");
        System.out.println("7. Mostrar activitats");
        System.out.println("8. Mostrar activitat especifica");
        System.out.println("0. Sortir");
        System.out.print("Opcio: ");
    }
    /**
     * Requests a non-empty text string from the user.
     * <p>
     * The method continues asking until the user enters a valid non-blank value.
     * </p>
     *
     * @param missatge the message displayed to the user
     * @return the text entered by the user
     */

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
    
        /**
     * Requests an integer value greater than zero from the user.
     * <p>
     * The method repeatedly asks for input until a valid positive number is entered.
     * </p>
     *
     * @param missatge the message displayed to the user
     * @return a positive integer value
     */

    private int demanarEnterMajorZero(String msg) {
        int n = 0;
        do {
            System.out.print(msg);
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine();
                if (n <= 0) {
                    System.out.println("ERROR: El numero ha de ser major que 0.");
                }

            } else {
                System.out.println("ERROR: Introdueix un numero valid.");
                sc.nextLine();
            }
        } while (n <= 0);
        return n;
    }
    
        /**
     * Searches for a user by DNI.
     * <p>
     * The search is case-insensitive and returns the first matching user.
     * </p>
     *
     * @param dni the DNI of the user to search for
     * @return the matching user, or {@code null} if no user is found
     */

    private Usuari buscarUsuari(String dni) {
        Usuari resultat = null;
        for (Usuari u : usuaris) {
            if (u.getDni().equalsIgnoreCase(dni)) {
                resultat = u;
            }
        }
        return resultat;
    }

        /**
     * Searches for an activity by its name.
     * <p>
     * The comparison is case-insensitive and returns the first matching activity.
     * </p>
     *
     * @param nom the name of the activity to search for
     * @return the matching activity, or {@code null} if no activity is found
     */

    private Activitat buscarActivitat(String nom) {
        Activitat resultat = null;
        for (Activitat a : activitats) {
            if (a.getNom().equalsIgnoreCase(nom)) {
                resultat = a;
            }
        }
        return resultat;
    }
    /**
     * Registers a new user after validating that the DNI is unique.
     * The DNI is validated using the official control-letter algorithm.
     */

   
    public void altaUsuari() {
        String dni = demanarText("DNI: ");
        Usuari existent = buscarUsuari(dni);
        if (existent != null) {
            System.out.println("ERROR: Aquest usuari ja existeix.");
        } else {
            String nom = demanarText("Nom: ");
            String email = demanarText("Email: ");
            usuaris.add(new Usuari(dni, nom, email));
            System.out.println("Usuari registrat correctament.");
        }
    }

       /**
     * Activates the membership of an existing user for a specified number of months.
     * The user must already exist in the system.
     */

    public void ferSoci() {
        if (usuaris.isEmpty()) {
            System.out.println("No hi ha usuaris registrats.");
        } else {
            String dni = demanarText("DNI usuari: ");
            Usuari u = buscarUsuari(dni);
            if (u == null) {
                System.out.println("Usuari no trobat.");
            } else {
                if (u.esSoci()) {
                    System.out.println("Aquest usuari ja es soci.");
                } else {
                    int mesos = demanarEnterMajorZero("Duracio membresia (mesos): ");
                    u.ferSoci(mesos);
                    System.out.println("Usuari convertit en soci correctament.");
                }
            }
        }
    }

    
      /**
     * Ends the membership of an existing user and resets the membership duration.
     */

    public void finalitzarMembresia() {
        if (usuaris.isEmpty()) {
            System.out.println("No hi ha usuaris registrats.");
        } else {
            String dni = demanarText("DNI soci: ");
            Usuari u = buscarUsuari(dni);
            if (u == null) {
                System.out.println("Usuari no trobat.");
            } else {
                if (!u.esSoci()) {
                    System.out.println("Aquest usuari no es soci.");
                } else {
                    u.finalitzarMembresia();
                    System.out.println("Membresia finalitzada correctament.");
                }
            }
        }
    }

       /**
     * Creates a new activity after validating that the name is not already in use.
     * The activity date is entered by the user and parsed as a LocalDate.
     */

   public void altaActivitat() {
    String nom = demanarText("Nom activitat: ");
    Activitat existent = buscarActivitat(nom);

    if (existent != null) {
        System.out.println("ERROR: Aquesta activitat ja existeix.");
    } else {
        LocalDate data = null;
        boolean dataValida = false;

        do {
            String dataText = demanarText("Data activitat (YYYY-MM-DD): ");
            try {
                data = LocalDate.parse(dataText);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("ERROR: Format de data incorrecte. Exemple correcte: 2024-05-10");
            }
        } while (!dataValida);

        activitats.add(new Activitat(nom, data));
        System.out.println("Activitat creada correctament.");
    }
}


      /**
     * Removes an existing activity from the system.
     * The activity must exist in order to be deleted.
     */

    public void eliminarActivitat() throws IOException {
        if (activitats.isEmpty()) {
            System.out.println("No hi ha activitats registrades.");
        } else {
            String nom = demanarText("Nom de l'activitat a eliminar: ");
            Activitat a = buscarActivitat(nom);
            if (a == null) {
                System.out.println("Activitat no trobada.");
            } else {
                activitats.remove(a);
                PersistenciaClub.guardarActivitats(activitats);
                System.out.println("Activitat eliminada correctament.");
            }
        }
    }

       /**
     * Registers a user as a participant in a specific activity.
     * Both the user and the activity must exist.
     */

    public void inscriureActivitat() throws IOException {
        if (usuaris.isEmpty()) {
            System.out.println("No hi ha usuaris registrats.");
        } else {
            if (activitats.isEmpty()) {
                System.out.println("No hi ha activitats registrades.");
            } else {
                String dni = demanarText("DNI usuari: ");
                Usuari u = buscarUsuari(dni);
                if (u == null) {
                    System.out.println("Usuari no trobat.");
                } else {
                    if (!u.esSoci()) {
                        System.out.println("ERROR: Només els socis poden inscriure's.");
                    } else {
                        String nomAct = demanarText("Nom activitat: ");
                        Activitat a = buscarActivitat(nomAct);
                        if (a == null) {
                            System.out.println("Activitat no trobada.");

                        } else {
                            a.afegirParticipant(u);
                            PersistenciaClub.guardarUsuaris(usuaris);
                            PersistenciaClub.guardarActivitats(activitats);
                            System.out.println("Soci inscrit correctament.");
                        }
                    }
                }
            }
        }
    }
    /**
     * Displays a list of all registered activities with their basic information.
     */

    public void mostrarActivitats() {
        if (activitats.isEmpty()) {
            System.out.println("No hi ha activitats registrades.");
        } else {
            System.out.println("----- Activitats -----");
            for (Activitat a : activitats) {
                System.out.println(
                        a.getNom() + " - " + a.getData()
                );
            }
        }
    }

        /**
     * Displays detailed information about a specific activity, including participants.
     */

    public void mostrarActivitatEspecifica() {
        if (activitats.isEmpty()) {
            System.out.println("No hi ha activitats registrades.");
        } else {
            String nom = demanarText("Nom activitat: ");
            Activitat a = buscarActivitat(nom);
            if (a == null) {
                System.out.println("Activitat no trobada.");
            } else {
                System.out.println("Activitat: " + a.getNom());
                System.out.println("Data: " + a.getData());
                if (a.getParticipants().isEmpty()) {
                    System.out.println("No hi ha participants.");
                } else {
                    System.out.println("--- Participants ---");
                    for (Usuari u : a.getParticipants()) {
                        System.out.println(
                                u.getDni() + " - " + u.getNom()
                        );
                    }
                }
            }
        }
    }

        /**
     * Saves all users and activities to disk.
     *
     * @throws IOException if an error occurs while writing the data files
     */

    public void guardar() throws IOException {
        PersistenciaClub.guardarUsuaris(usuaris);
        PersistenciaClub.guardarActivitats(activitats);
        System.out.println("Dades guardades correctament.");
    }
}