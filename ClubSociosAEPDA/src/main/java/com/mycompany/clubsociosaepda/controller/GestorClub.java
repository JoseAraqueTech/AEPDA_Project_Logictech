/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda.controller;

import com.mycompany.clubsociosaepda.model.Usuari;
import com.mycompany.clubsociosaepda.model.Activitat;
import com.mycompany.clubsociosaepda.model.Torneig;
import com.mycompany.clubsociosaepda.model.CursPintura;
import com.mycompany.clubsociosaepda.model.Balda;
import com.mycompany.clubsociosaepda.model.Asignacion;
import com.mycompany.clubsociosaepda.persistence.PersistenciaClub;
import com.mycompany.clubsociosaepda.exception.AEDPAException;
import com.mycompany.clubsociosaepda.exception.PersistenciaException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Clase que gestiona toda la lógica del club.
 * Se encarga de gestionar usuarios, actividades, baldas,
 * inscripciones y persistencia de datos.
 */
public class GestorClub {

    private ArrayList<Usuari> usuaris;
    private ArrayList<Activitat> activitats;
    private Map<Integer, Balda> baldas;
    private Scanner sc;

    /**
     * Constructor del gestor del club.
     * Carga los datos desde los ficheros y inicializa las baldas del sistema.
     * @throws PersistenciaException si hay error al cargar datos
     */
    public GestorClub() throws PersistenciaException {
        usuaris = PersistenciaClub.carregarUsuaris();
        activitats = PersistenciaClub.carregarActivitats();
        baldas = new HashMap<>();
        inicializarBaldas();
        carregarAssignacions(); 
        sc = new Scanner(System.in);
    }

    private void inicializarBaldas() {
        crearBalda(1, "Pasillo 1 - Balda A");
        crearBalda(2, "Pasillo 1 - Balda B");
        crearBalda(3, "Pasillo 2 - Balda A");
        crearBalda(4, "Pasillo 2 - Balda B");
        crearBalda(5, "Pasillo 3 - Balda A");
        crearBalda(6, "Pasillo 3 - Balda B");
        crearBalda(7, "Pasillo 4 - Balda A");
        crearBalda(8, "Pasillo 4 - Balda B");
    }

    private ArrayList<Asignacion> getAsignacionesActivas() {
        ArrayList<Asignacion> lista = new ArrayList<>();
        for (Balda b : baldas.values()) {
            if (b.estaOcupada()) {
                lista.add(b.getAsignacionActual());
            }
        }
        return lista;
    }
    
    private void carregarAssignacions() throws PersistenciaException {
        ArrayList<String[]> dades = PersistenciaClub.carregarAssignacions();
        for (String[] d : dades) {
            int idBalda = Integer.parseInt(d[0]);
            String dni = d[1];
            int mesos = 1;
            Balda b = baldas.get(idBalda);
            Usuari u = buscarUsuari(dni);
            if (b != null && u != null) {
                if (!b.estaOcupada()) {
                    Asignacion a = new Asignacion(b, u, mesos);
                    b.asignar(a);
                }
            }
        }
    }
    
    private void crearBalda(int id, String ubicacion) {
        baldas.put(id, new Balda(id, ubicacion));
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

    private int demanarEnterMajorZero(String msg) {
        int n = 0;
        do {
            System.out.print(msg);
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                sc.nextLine();
                if (n <= 0) {
                    System.out.println("ERROR: Major que 0.");
                }
            } else {
                System.out.println("ERROR: Numero invalid.");
                sc.nextLine();
            }
        } while (n <= 0);
        return n;
    }

    private Usuari buscarUsuari(String dni) {
        for (Usuari u : usuaris) {
            if (u.getDni().equalsIgnoreCase(dni)) {
                return u;
            }
        }
        return null;
    }

    private Activitat buscarActivitat(String nom) {
        for (Activitat a : activitats) {
            if (a.getNom().equalsIgnoreCase(nom)) {
                return a;
            }
        }
        return null;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * Solicita los datos y valida que el DNI y el email sean correctos.
     * También comprueba que no exista ya un usuario con el mismo DNI.
     * @throws AEDPAException si el DNI o email no son válidos o ya existe el usuario
     */
    public void altaUsuari() throws AEDPAException {
        String dni = demanarText("DNI: ");
        if (!Usuari.esDniValid(dni)) {
            throw new AEDPAException("DNI no valid.");
        }
        if (buscarUsuari(dni) != null) {
            throw new AEDPAException("Aquest usuari ja existeix.");
        }
        String nom = demanarText("Nom: ");
        String email = demanarText("Email: ");
        if (!Usuari.esEmailValid(email)) {
            throw new AEDPAException("Email no valid.");
        }
        usuaris.add(new Usuari(dni, nom, email));
    }

    /**
     * Convierte un usuario en socio.
     * Solicita el DNI del usuario y los meses de membresía.
     * @throws AEDPAException si no hay usuarios, no existe el usuario
     * o ya es socio
     */
    public void ferSoci() throws AEDPAException {
        if (usuaris.isEmpty()) {
            throw new AEDPAException("No hi ha usuaris.");
        }
        Usuari u = buscarUsuari(demanarText("DNI: "));
        if (u == null) {
            throw new AEDPAException("Usuari no trobat.");
        }
        if (u.esSoci()) {
            throw new AEDPAException("Ja es soci.");
        }
        u.ferSoci(demanarEnterMajorZero("Mesos: "));
    }

    /**
     * Finaliza la membresía de un usuario.
     * @throws AEDPAException si no hay usuarios, no existe el usuario
     * o no es socio
     */
    public void finalitzarMembresia() throws AEDPAException {
        if (usuaris.isEmpty()) {
            throw new AEDPAException("No hi ha usuaris.");
        }
        Usuari u = buscarUsuari(demanarText("DNI: "));
        if (u == null) {
            throw new AEDPAException("Usuari no trobat.");
        }
        if (!u.esSoci()) {
            throw new AEDPAException("No es soci.");
        }
        u.finalitzarMembresia();
    }

    /**
     * Crea una nueva actividad en el sistema.
     * Permite elegir entre tipo Torneig o Curs de Pintura.
     * @throws AEDPAException si la actividad ya existe o el formato de fecha es incorrecto
     */
    public void altaActivitat() throws AEDPAException {
        String nom = demanarText("Nom: ");
        if (buscarActivitat(nom) != null) {
            throw new AEDPAException("Ja existeix aquesta activitat.");
        }
        LocalDate data;
        try {
            data = LocalDate.parse(demanarText("Data (YYYY-MM-DD): "));
        } catch (DateTimeParseException e) {
            throw new AEDPAException("Format de data incorrecte.");
        }
        System.out.println("1. Torneig");
        System.out.println("2. Curs");
        int tipus;
        boolean correcte = false;
        do {
            tipus = demanarEnterMajorZero("Tipus (1 o 2): ");
            if (tipus == 1 || tipus == 2) {
                correcte = true;
            } else {
                System.out.println("ERROR: Nomes pots introduir 1 o 2.");
            }
        } while (!correcte);
        Activitat a;
        if (tipus == 1) {
            a = new Torneig(nom, data);
        } else {
            String professor = demanarText("Professor: ");
            a = new CursPintura(nom, data, professor);
        }
        activitats.add(a);
    }

    /**
     * Elimina una actividad del sistema.
     * @throws AEDPAException si no hay actividades o no se encuentra la actividad
     * @throws PersistenciaException si hay error al guardar los datos
     */
    public void eliminarActivitat() throws AEDPAException, PersistenciaException {
        if (activitats.isEmpty()) {
            throw new AEDPAException("No hi ha activitats.");
        }
        Activitat a = buscarActivitat(demanarText("Nom: "));
        if (a == null) {
            throw new AEDPAException("No trobada.");
        }
        activitats.remove(a);
        PersistenciaClub.guardarActivitats(activitats);
    }

    
    /**
     * Inscribe a un usuario en una actividad del club.
     * Comprueba que existan usuarios y actividades registradas,
     * valida que el usuario exista y que pueda participar.
     * Si el usuario supera el número máximo de participaciones sin ser socio,
     * se le indica que debe hacerse socio o pagar.
     * @throws AEDPAException si no hay usuarios, no hay actividades,
     * el usuario no existe, la actividad no existe o no puede participar
     * @throws PersistenciaException si ocurre un error al guardar los datos
     */
    public void inscriureActivitat() throws PersistenciaException, AEDPAException {
        if (usuaris.isEmpty()) {
            throw new AEDPAException("No hi ha usuaris.");
        }
        if (activitats.isEmpty()) {
            throw new AEDPAException("No hi ha activitats.");
        }
        String dni = demanarText("DNI: ");
        Usuari u = buscarUsuari(dni);
        if (u == null) {
            throw new AEDPAException("Usuari no trobat.");
        }
        if (!u.potParticipar()) {
            throw new AEDPAException("Ha superat el limit de participacions. Ha de fer-se soci o pagar.");
        }
        String nomAct = demanarText("Activitat: ");
        Activitat a = buscarActivitat(nomAct);
        if (a == null) {
            throw new AEDPAException("Activitat no trobada.");
        }
        a.afegirParticipant(u);
        u.incrementarParticipaciones();
        if (u.necesitaSerSoci()) {
            System.out.println("AVIS: Aquest usuari ha de fer-se soci a partir d'ara.");
        }
        PersistenciaClub.guardarActivitats(activitats);
    }

    /**
     * Muestra todas las actividades registradas.
     */
    public void mostrarActivitats() {
        for (Activitat a : activitats) {
            System.out.println(a.getNom() + " - " + a.getData());
        }
    }

    /**
     * Muestra la información de una actividad concreta junto a sus participantes.
     * @throws AEDPAException si no hay actividades o no se encuentra
     */
    public void mostrarActivitatEspecifica() throws AEDPAException {
        if (activitats.isEmpty()) {
            throw new AEDPAException("No hi ha activitats.");
        }
        Activitat a = buscarActivitat(demanarText("Nom: "));
        if (a == null) throw new AEDPAException("No trobada.");
        System.out.println(a.getNom());
        if (a.getParticipants().isEmpty()) {
            System.out.println("Sense participants.");
        } else {
            for (Usuari u : a.getParticipants()) {
                System.out.println(u.getNom());
            }
        }
    }

    /**
     * Muestra todas las baldas del sistema junto a su estado
     * (ocupada o libre) y el usuario asignado si corresponde.
     */    
    public void mostrarBaldas() {
        if (baldas.isEmpty()) {
            System.out.println("No hi ha baldes.");
        } else {
            for (Balda b : baldas.values()) {
                String estat;
                if (b.estaOcupada()) {
                    estat = "OCUPADA";
                } else {
                    estat = "LLIURE";
                }
                System.out.println("Balda " + b.getId() + " - " + estat);
                if (b.estaOcupada()) {
                    System.out.println("  Soci: " + b.getAsignacionActual().getSocio().getNom());
                }
            }
        }
    }

    /**
     * Muestra el número de baldas libres y ocupadas.
     */
    public void mostrarDisponibilidadBaldas() {
        int libres = 0;
        int ocupadas = 0;
        for (Balda b : baldas.values()) {
            if (b.estaOcupada()) ocupadas++;
            else libres++;
        }
        System.out.println("Lliures: " + libres + " | Ocupades: " + ocupadas);
    }

    /**
     * Asigna una balda a un usuario socio durante un número de meses.
     * @throws AEDPAException si no hay usuarios, la balda no existe,
     * está ocupada o el usuario no es válido
     */
    public void asignarBalda() throws AEDPAException {
        if (usuaris.isEmpty()) {
            throw new AEDPAException("No hi ha usuaris.");
        }
        Balda b = baldas.get(demanarEnterMajorZero("ID: "));
        if (b == null) throw new AEDPAException("No existeix.");
        if (b.estaOcupada()) throw new AEDPAException("Ja ocupada.");
        Usuari u = buscarUsuari(demanarText("DNI: "));
        if (u == null || !u.esSoci()) {
            throw new AEDPAException("Usuari no valid.");
        }
        b.asignar(new Asignacion(b, u, demanarEnterMajorZero("Mesos: ")));
    }

    /**
     * Libera una balda ocupada.
     * @throws AEDPAException si la balda no existe o ya está libre
     */
    public void liberarBalda() throws AEDPAException {
        Balda b = baldas.get(demanarEnterMajorZero("ID: "));
        if (b == null) throw new AEDPAException("No existeix.");
        if (!b.estaOcupada()) throw new AEDPAException("Ja lliure.");
        b.liberar();
    }

    /**
     * Guarda todos los datos del sistema en los ficheros.
     * @throws PersistenciaException si ocurre un error al guardar
     */
    public void guardar() throws PersistenciaException {
        PersistenciaClub.guardarUsuaris(usuaris);
        PersistenciaClub.guardarActivitats(activitats);
        PersistenciaClub.guardarAssignacions(getAsignacionesActivas());
    }
}
