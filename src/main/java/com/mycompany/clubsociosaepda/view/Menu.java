/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.view;

import com.mycompany.clubsociosaepda.controller.GestorClub;
import com.mycompany.clubsociosaepda.exception.AEDPAException;
import com.mycompany.clubsociosaepda.exception.PersistenciaException;
import com.mycompany.clubsociosaepda.model.Activitat;
import com.mycompany.clubsociosaepda.model.enums.NivellPintura;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 * Clase encargada de la interacción con el usuario. Muestra el menú y gestiona
 * las opciones introducidas.
 */
public class Menu {

    private GestorClub gestor;
    private AskData ask;

    /**
     * Constructor del menú. Inicializa el gestor y el lector de datos.
     *
     * @throws PersistenciaException si ocurre un error cargando datos
     * @throws AEDPAException si ocurre un error de lógica al iniciar
     */
    public Menu() throws PersistenciaException, AEDPAException {
        gestor = new GestorClub();
        ask = new AskData();
    }

    /**
     * Inicia la ejecución del menú. Muestra las opciones disponibles y procesa
     * la entrada del usuario hasta que se selecciona la opción de salida.
     */
    public void start() {
        int opcio;
        do {
            mostrarMenu();
            try {
                opcio = ask.askInt("");
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
                    case 13:
                        altaBalda();
                        break;
                    case 14:
                        modificarBalda();
                        break;
                    case 15:
                        mostrarUsuari();
                        break;
                    case 16:
                        eliminarUsuari();
                        break;
                    case 0:
                        break;
                    default:
                        System.out.println("Opcio incorrecta.");
                }
            } catch (AEDPAException | PersistenciaException | IOException | SQLException e) {
                System.out.println("ERROR: " + e.getMessage());
                opcio = -1;
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
        System.out.println("13. Alta balda");
        System.out.println("14. Modificar balda");
        System.out.println("15. Mostrar usuari");
        System.out.println("16. Eliminar usuari");
        System.out.println("0. Sortir");
        System.out.print("Opcio: ");
    }

    /**
     * Registra un nuevo usuario.
     *
     * @throws AEDPAException si ocurre un error de lógica
     * @throws IOException si ocurre un error leyendo datos
     * @throws SQLException si ocurre un error de base de datos
     */
    private void altaUsuari() throws AEDPAException, IOException, SQLException {
        String dni = ask.askString("DNI: ");
        String nom = ask.askString("Nom: ");
        String email = ask.askString("Email: ");
        double saldo = ask.askDouble("Saldo soci:");
        gestor.altaUsuari(dni, nom, email, saldo);
        System.out.println("Usuari creat correctament.");
    }

    /**
     * Convierte un usuario en socio.
     *
     * @throws AEDPAException si ocurre un error de lógica
     * @throws IOException si ocurre un error leyendo datos
     * @throws SQLException si ocurre un error de base de datos
     */
    private void ferSoci() throws AEDPAException, IOException, SQLException {
        String dni = ask.askString("DNI: ");
        int mesos = ask.askInt("Mesos: ", "Ha de ser >= 1", 1);
        gestor.ferSoci(dni, mesos);
        System.out.println("Usuari convertit en soci.");
    }

    /**
     * Finaliza la membresía de un usuario.
     *
     * @throws AEDPAException si ocurre un error de lógica
     * @throws IOException si ocurre un error leyendo datos
     * @throws SQLException si ocurre un error de base de datos
     */
    private void finalitzarMembresia() throws AEDPAException, IOException, SQLException {
        String dni = ask.askString("DNI: ");
        gestor.finalitzarMembresia(dni);
        System.out.println("Membresia finalitzada.");
    }

    /**
     * Crea una nueva actividad.
     *
     * @throws AEDPAException si ocurre un error de lógica
     * @throws IOException si ocurre un error leyendo datos
     * @throws SQLException si ocurre un error de base de datos
     */
    private void altaActivitat() throws AEDPAException, IOException, SQLException {
        int id_activitat = ask.askInt("Id activitat: ");
        String nom = ask.askString("Nom activitat: ");
        LocalDate data = LocalDate.parse(ask.askString("Data (YYYY-MM-DD): "));
        int tipus = ask.askInt("Tipus (1 = Torneig, 2 = Curs): ", "Tipus invalid", 1, 2);
        String professor = null;
        NivellPintura nivell = null;
        if (tipus == 2) {
            professor = ask.askString("Professor: ");
            String nivellText = ask.askString("Nivell (PRINCIPIANT/MITJA/AVANCAT): ").toUpperCase();
            nivell = NivellPintura.valueOf(nivellText);
        }
        gestor.altaActivitat(id_activitat, nom, data, tipus, professor, nivell);
        System.out.println("Activitat creada correctament.");
    }

    /**
     * Elimina una actividad.
     *
     * @throws AEDPAException si ocurre un error de lógica
     * @throws PersistenciaException si ocurre un error guardando datos
     * @throws IOException si ocurre un error leyendo datos
     * @throws SQLException si ocurre un error de base de datos
     */
    private void eliminarActivitat() throws AEDPAException, PersistenciaException, IOException, SQLException {
        int id_activitat = ask.askInt("Id activitat: ");
        gestor.eliminarActivitat(id_activitat);
        System.out.println("Activitat eliminada.");
    }

    /**
     * Inscribe un usuario en una actividad.
     *
     * @throws AEDPAException si ocurre un error de lógica
     * @throws PersistenciaException si ocurre un error guardando datos
     * @throws IOException si ocurre un error leyendo datos
     * @throws SQLException si ocurre un error de base de datos
     */
    private void inscriureActivitat() throws AEDPAException, PersistenciaException, IOException, SQLException {
        int id_activitat = ask.askInt("Id activitat: ");
        String dni = ask.askString("DNI: ");
        if (gestor.esTorneig(id_activitat)) {
            gestor.inscriureActivitat(id_activitat, dni);
            System.out.println("Usuari inscrit correctament (amb menjar).");
        } else {
            gestor.inscriureActivitat(id_activitat, dni);
            System.out.println("Usuari inscrit correctament.");
        }
    }

    /**
     * Muestra todas las actividades registradas.
     *
     * @throws AEDPAException si la actividad no existe
     * @throws PersistenciaException si ocurre un error de persistencia
     * @throws IOException si ocurre un error leyendo datos
     * @throws SQLException si ocurre un error de base de datos
     */
    private void mostrarActivitats() throws AEDPAException, PersistenciaException, IOException, SQLException {
        int id_activitat = ask.askInt("Id activitat (0 per veure totes): ");
        List<Activitat> activitats = gestor.mostrarActivitats(id_activitat);
        if (activitats.isEmpty()) {
            System.out.println("No hi ha activitats.");
        } else {
            for (Activitat a : activitats) {
                System.out.println(a);
            }
        }
    }

    /**
     * Muestra la información de una actividad concreta.
     *
     * @throws AEDPAException si la actividad no existe
     * @throws IOException si ocurre un error leyendo datos
     */
    private void mostrarActivitatEspecifica() throws AEDPAException, IOException, SQLException {
        String nom = ask.askString("Nom activitat: ");
        System.out.println(gestor.mostrarActivitatEspecifica(nom));
    }

    /**
     * Muestra todas las baldas del sistema.
     */
    private void mostrarBaldas() {
        for (String s : gestor.mostrarBaldas()) {
            System.out.println(s);
        }
    }

    /**
     * Muestra la disponibilidad de baldas.
     */
    private void mostrarDisponibilidadBaldas() {
        System.out.println(gestor.mostrarDisponibilitatBaldas());
    }

    /**
     * Asigna una balda a un socio.
     *
     * @throws AEDPAException si ocurre un error de lógica
     * @throws IOException si ocurre un error leyendo datos
     */
    private void asignarBalda() throws AEDPAException, IOException, SQLException {
        int id = ask.askInt("ID balda: ");
        String dni = ask.askString("DNI: ");
        int mesos = ask.askInt("Mesos: ", "Ha de ser >= 1", 1);
        gestor.asignarBalda(id, dni, mesos);
        System.out.println("Balda assignada.");
    }

    /**
     * Libera una balda ocupada.
     *
     * @throws AEDPAException si ocurre un error de lógica
     * @throws IOException si ocurre un error leyendo datos
     */
    private void liberarBalda() throws AEDPAException, IOException, SQLException {
        int id = ask.askInt("ID balda: ");
        gestor.liberarBalda(id);
        System.out.println("Balda alliberada.");
    }

    /**
     * Crea una nueva balda.
     *
     * @throws IOException si ocurre un error leyendo datos
     * @throws AEDPAException si ocurre un error de lógica
     */
    private void altaBalda() throws IOException, AEDPAException, SQLException {
        int id = ask.askInt("ID: ");
        String ubicacion = ask.askString("Ubicacion: ");
        gestor.crearBalda(id, ubicacion);
        System.out.println("Balda creada correctament.");
    }

    /**
     * Modifica una balda existente.
     *
     * @throws IOException si ocurre un error leyendo datos
     * @throws AEDPAException si ocurre un error de lógica
     * @throws PersistenciaException si ocurre un error de persistencia
     */
    private void modificarBalda() throws IOException, AEDPAException, PersistenciaException, SQLException {
        int id = ask.askInt("ID de la balda a canviar: ");
        String ubicacion = ask.askString("Nova ubicacio: ");
        gestor.modBalda(id, ubicacion);
        System.out.println("Balda modificada correctament.");
    }

    /**
     * Elimina un usuario.
     *
     * @throws IOException si ocurre un error leyendo datos
     * @throws AEDPAException si ocurre un error de lógica
     * @throws PersistenciaException si ocurre un error de persistencia
     */
    private void eliminarUsuari() throws AEDPAException, PersistenciaException, IOException, SQLException {
        String dni = ask.askString("DNI de l'usuari a eliminar: ");
        gestor.eliminarUsuari(dni);
        System.out.println("Usuari eliminat correctament.");
    }

    /**
     * Muestra la información de un usuario.
     *
     * @throws IOException si ocurre un error leyendo datos
     * @throws AEDPAException si ocurre un error de lógica
     * @throws SQLException si ocurre un error de base de datos
     */
    private void mostrarUsuari() throws IOException, AEDPAException, SQLException {
        String dni = ask.askString("Introdueix el DNI de l'usuari: ");
        System.out.println("\n INFORMACIO DE L'USUARI");
        List<com.mycompany.clubsociosaepda.model.Usuari> usuaris = gestor.mostrarUsuari(dni);
        if (usuaris.isEmpty()) {
            System.out.println("Usuari no trobat.");
        } else {
            for (com.mycompany.clubsociosaepda.model.Usuari u : usuaris) {
                System.out.println(u);
            }
        }
    }
}