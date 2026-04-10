/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda;

import com.mycompany.clubsociosaepda.ClasesAEPDA.Usuari;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Activitat;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Balda;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Asignacion;
import com.mycompany.clubsociosaepda.PersistenciaAEPDA.PersistenciaClub;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author juan-
 */
/**
 * Provides the main logic for managing users, memberships and activities.
 * <p>
 * This class handles all menu operations, including user registration,
 * membership activation, activity creation, and participant management. It also
 * interacts with the persistence layer to load and save data.
 * </p>
 *
 * @author Andrés/Jose/Enric/Juan
 * @version 1.0
 * @since 2026-04-08
 */
public class GestorClub {

    private ArrayList<Usuari> usuaris;
    private ArrayList<Activitat> activitats;
    private Map<Integer, Balda> baldas;
    private List<Asignacion> historialAsignaciones;
    private Scanner sc;

    
        /**
     * Creates a new club manager and loads all users and activities from disk.
     *
     * @throws IOException if an error occurs while loading stored data
     */

    public GestorClub() throws IOException {
        usuaris = PersistenciaClub.carregarUsuaris();
        activitats = PersistenciaClub.carregarActivitats();
        baldas = new HashMap<>();
        historialAsignaciones = new ArrayList<>();
        inicializarBaldas();
        carregarAssignacions();
        sc = new Scanner(System.in);
    }

    /**
     * Initializes the default set of shelves
     */
    private void inicializarBaldas() {
        crearBalda(1, "Pasillo 1 - Estante A");
        crearBalda(2, "Pasillo 1 - Estante B");
        crearBalda(3, "Pasillo 2 - Estante A");
        crearBalda(4, "Pasillo 2 - Estante B");
        crearBalda(5, "Pasillo 3 - Estante A");
        crearBalda(6, "Pasillo 3 - Estante B");
        crearBalda(7, "Pasillo 4 - Estante A");
        crearBalda(8, "Pasillo 4 - Estante B");
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
        System.out.println("9. Mostrar baldes");
        System.out.println("10. Mostrar disponibilitat baldes");
        System.out.println("11. Assignar balda a soci");
        System.out.println("12. Alliberar balda");
        System.out.println("0. Sortir");
        System.out.print("Opcio: ");
    }


    /**
     * Requests a non-empty text string from the user.
     * <p>
     * The method continues asking until the user enters a valid non-blank
     * value.
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
     * The method repeatedly asks for input until a valid positive number is
     * entered.
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
     * The comparison is case-insensitive and returns the first matching
     * activity.
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
     * Registers a new user after validating that the DNI is unique. The DNI is
     * validated using the official control-letter algorithm.
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
     * Activates the membership of an existing user for a specified number of
     * months. The user must already exist in the system.
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
     * Ends the membership of an existing user and resets the membership
     * duration.
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
     * Creates a new activity after validating that the name is not already in
     * use. The activity date is entered by the user and parsed as a LocalDate.
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
     * Removes an existing activity from the system. The activity must exist in
     * order to be deleted.
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
     * Registers a user as a participant in a specific activity. Both the user
     * and the activity must exist.
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
     * Displays a list of all registered activities with their basic
     * information.
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
     * Displays detailed information about a specific activity, including
     * participants.
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
     * Saves all users, activities and assignations to disk.
     *
     * @throws IOException if an error occurs while writing the data files
     */
    public void guardar() throws IOException {
        PersistenciaClub.guardarUsuaris(usuaris);
        PersistenciaClub.guardarActivitats(activitats);
        PersistenciaClub.guardarAssignacions(getAsignacionesActivas());
        System.out.println("Dades guardades correctament.");
    }

    /**
     * Displays shelf availability summary
     */
    public void mostrarDisponibilidadBaldas() {
        System.out.println(getDisponibilidad());
    }

    /**
     * Prompts user to assign a shelf to a member
     */
    public void asignarBalda() {
        if (usuaris.isEmpty()) {
            System.out.println("No hi ha usuaris registrats.");
            return;
        }

        mostrarDisponibilidadBaldas();
        int idBalda = demanarEnterMajorZero("ID balda a assignar: ");

        try {
            Balda balda = buscarBalda(idBalda);
            if (balda.estaOcupada()) {
                System.out.println("ERROR: La balda ja esta ocupada.");
                return;
            }

            String dni = demanarText("DNI del soci: ");
            Usuari soci = buscarUsuari(dni);
            if (soci == null) {
                System.out.println("ERROR: Soci no trobat.");
                return;
            }
            if (!soci.esSoci()) {
                System.out.println("ERROR: L'usuari no es soci.");
                return;
            }

            int meses = demanarEnterMajorZero("Duracio assignacio (mesos): ");
            assignarBalda(idBalda, soci, meses);
            PersistenciaClub.guardarAssignacions(getAsignacionesActivas());
            System.out.println("Balda assignada correctament.");

        } catch (com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaNoEncontradaException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaOcupadaException e) {
            System.out.println("ERROR: " + e.getMessage());
        } catch (java.io.IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Prompts user to release an occupied shelf
     */
    public void liberarBalda() {
        mostrarDisponibilidadBaldas();
        int idBalda = demanarEnterMajorZero("ID balda a alliberar: ");

        try {
            Balda balda = buscarBalda(idBalda);
            if (balda.estaOcupada()) {
                System.out.println("ERROR: La balda esta ocupada. No es pot alliberar.");
                return;
            }

            System.out.println("ERROR: La balda ja esta lliure.");

        } catch (com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaNoEncontradaException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Displays all shelves with their status
     */
    public void mostrarBaldas() {
        List<Balda> baldas = listarBaldas();
        if (baldas.isEmpty()) {
            System.out.println("No hi ha baldes registrades.");
        } else {
            System.out.println("----- Baldes -----");
            for (Balda b : baldas) {
                String estado = b.estaOcupada() ? "OCUPADA" : "LLIURE";
                String info = "Balda #" + b.getId() + " - " + b.getUbicacion() + " [" + estado + "]";
                if (b.estaOcupada()) {
                    String nomSoci = b.getAsignacionActual().getSocio().getNom();
                    info += " -> Soci: " + nomSoci;
                }
                System.out.println(info);
            }
        }
    }

    /**
     * Creates a new shelf with the given ID and location.
     */
    private void crearBalda(int id, String ubicacion) {
        baldas.put(id, new Balda(id, ubicacion));
    }

    /**
     * @return list of all shelves
     */
    public List<Balda> listarBaldas() {
        return new ArrayList<>(baldas.values());
    }

    /**
     * Finds a shelf by ID. @throws BaldaNoEncontradaException if not found
     */
    private Balda buscarBalda(int id) throws com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaNoEncontradaException {
        Balda balda = baldas.get(id);
        if (balda == null) {
            throw new com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaNoEncontradaException(id);
        }
        return balda;
    }

    /**
     * Assigns a shelf to a member for a given duration.
     *
     * @throws BaldaNoEncontradaException if shelf not found
     * @throws BaldaOcupadaException if shelf is already occupied
     */
    private void assignarBalda(int idBalda, Usuari soci, int mesesDuracion)
            throws com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaNoEncontradaException,
            com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaOcupadaException {
        Balda balda = buscarBalda(idBalda);

        if (balda.estaOcupada()) {
            throw new com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaOcupadaException(idBalda);
        }

        Asignacion asignacion = new Asignacion(balda, soci, mesesDuracion);
        balda.asignar(asignacion);
        historialAsignaciones.add(asignacion);
    }

    /**
     * Releases a shelf making it available again.
     */
    private void liberarBalda(int idBalda) throws com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaNoEncontradaException {
        Balda balda = buscarBalda(idBalda);
        balda.liberar();
    }

    /**
     * @return list of available shelves
     */
    private List<Balda> getBaldasLibres() {
        List<Balda> libres = new ArrayList<>();
        for (Balda balda : baldas.values()) {
            if (!balda.estaOcupada()) {
                libres.add(balda);
            }
        }
        return libres;
    }

    /**
     * @return list of occupied shelves
     */
    private List<Balda> getBaldasOcupadas() {
        List<Balda> ocupadas = new ArrayList<>();
        for (Balda balda : baldas.values()) {
            if (balda.estaOcupada()) {
                ocupadas.add(balda);
            }
        }
        return ocupadas;
    }

    /**
     * @return availability summary string
     */
    private String getDisponibilidad() {
        int libres = getBaldasLibres().size();
        int ocupadas = getBaldasOcupadas().size();
        return "Lliures: " + libres + " | Ocupades: " + ocupadas + " | Total: " + baldas.size();
    }

    /**
     * @return list of active assignments
     */
    private List<Asignacion> getAsignacionesActivas() {
        List<Asignacion> activas = new ArrayList<>();
        for (Balda balda : baldas.values()) {
            Asignacion a = balda.getAsignacionActual();
            if (a != null && a.isActiva()) {
                activas.add(a);
            }
        }
        return activas;
    }

    /**
     * @return map of all shelves by ID
     */
    public Map<Integer, Balda> getBaldas() {
        return baldas;
    }

    private void carregarAssignacions() throws IOException {
        ArrayList<String[]> dades = PersistenciaClub.carregarAssignacions();
        Map<Integer, Balda> mapBaldas = baldas;
        for (String[] d : dades) {
            int idBalda = Integer.parseInt(d[0]);
            String dniSocio = d[1];
            LocalDate fechaAsignacion = LocalDate.parse(d[2]);
            LocalDate fechaVencimiento = LocalDate.parse(d[3]);
            boolean activa = Boolean.parseBoolean(d[4]);

            Balda balda = mapBaldas.get(idBalda);
            Usuari soci = buscarUsuari(dniSocio);

            if (balda != null && soci != null && !balda.estaOcupada()) {
                try {
                    long mesesDuracion = java.time.temporal.ChronoUnit.MONTHS.between(fechaAsignacion, fechaVencimiento);
                    int meses = (int) mesesDuracion;
                    if (meses <= 0) {
                        meses = 1;
                    }
                    assignarBalda(idBalda, soci, meses);
                    if (!activa) {
                        Asignacion a = balda.getAsignacionActual();
                        if (a != null) {
                            a.cancelar();
                        }
                    }
                } catch (com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaNoEncontradaException e) {
                } catch (com.mycompany.clubsociosaepda.ExceptionAEPDA.BaldaOcupadaException e) {
                }
            }
        }
    }
}
