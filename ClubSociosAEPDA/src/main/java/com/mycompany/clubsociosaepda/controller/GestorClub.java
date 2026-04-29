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
<<<<<<< HEAD
    
=======
    private Scanner sc;
>>>>>>> main

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
<<<<<<< HEAD
      
    }
/**
     * Creates all shelves in the system.
     */
=======
        sc = new Scanner(System.in);
    }

>>>>>>> main
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
<<<<<<< HEAD
 
    /**
     * Returns all active shelf assignments.
     */
=======

>>>>>>> main
    private ArrayList<Asignacion> getAsignacionesActivas() {
        ArrayList<Asignacion> lista = new ArrayList<>();
        for (Balda b : baldas.values()) {
            if (b.estaOcupada()) {
                lista.add(b.getAsignacionActual());
            }
        }
        return lista;
    }
<<<<<<< HEAD
     /**
     * Loads shelf assignments from persistence.
     */
=======
    
>>>>>>> main
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

<<<<<<< HEAD
    

    
/**
     * Searches a user by DNI.
     */
=======
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

>>>>>>> main
    private Usuari buscarUsuari(String dni) {
        for (Usuari u : usuaris) {
            if (u.getDni().equalsIgnoreCase(dni)) {
                return u;
            }
        }
        return null;
    }
<<<<<<< HEAD
/**
     * Searches an activity by name.
     */
=======

>>>>>>> main
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
<<<<<<< HEAD
    public void altaUsuari(String dni, String nom, String email) throws AEDPAException {

        if (!Usuari.esDniValid(dni)) {
            throw new AEDPAException("DNI no valid.");
    }

         if (buscarUsuari(dni) != null) {
            throw new AEDPAException("Aquest usuari ja existeix.");
    }

         if (!Usuari.esEmailValid(email)) {
            throw new AEDPAException("Email no valid.");
    }

    usuaris.add(new Usuari(dni, nom, email));
}


=======
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

>>>>>>> main
    /**
     * Convierte un usuario en socio.
     * Solicita el DNI del usuario y los meses de membresía.
     * @throws AEDPAException si no hay usuarios, no existe el usuario
     * o ya es socio
     */
<<<<<<< HEAD
    public void ferSoci(String dni, int mesos) throws AEDPAException {
        if (usuaris.isEmpty()) {
            throw new AEDPAException("No hi ha usuaris.");
        }
        Usuari u = buscarUsuari(dni);
=======
    public void ferSoci() throws AEDPAException {
        if (usuaris.isEmpty()) {
            throw new AEDPAException("No hi ha usuaris.");
        }
        Usuari u = buscarUsuari(demanarText("DNI: "));
>>>>>>> main
        if (u == null) {
            throw new AEDPAException("Usuari no trobat.");
        }
        if (u.esSoci()) {
            throw new AEDPAException("Ja es soci.");
        }
<<<<<<< HEAD
        u.ferSoci(mesos);
=======
        u.ferSoci(demanarEnterMajorZero("Mesos: "));
>>>>>>> main
    }

    /**
     * Finaliza la membresía de un usuario.
     * @throws AEDPAException si no hay usuarios, no existe el usuario
     * o no es socio
     */
<<<<<<< HEAD
    public void finalitzarMembresia(String dni) throws AEDPAException {
         Usuari u = buscarUsuari(dni);

        if (u == null)
            throw new AEDPAException("Usuari no trobat.");
        
=======
    public void finalitzarMembresia() throws AEDPAException {
        if (usuaris.isEmpty()) {
            throw new AEDPAException("No hi ha usuaris.");
        }
        Usuari u = buscarUsuari(demanarText("DNI: "));
        if (u == null) {
            throw new AEDPAException("Usuari no trobat.");
        }
>>>>>>> main
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
<<<<<<< HEAD
    public void altaActivitat(String nom, LocalDate data, int tipus, String professor) throws AEDPAException {
       if (buscarActivitat(nom) != null)
            throw new AEDPAException("Ja existeix aquesta activitat.");

        int any = data.getYear();
        if (any < 1980 || any > 2050)
            throw new AEDPAException("L'any ha d'estar entre 1980 i 2050.");

        Activitat a;

        if (tipus == 1) {
            a = new Torneig(nom, data);
        } else if (tipus == 2) {
            if (professor == null || professor.isEmpty())
                throw new AEDPAException("El professor no pot estar buit.");
            a = new CursPintura(nom, data, professor);
        } else {
            throw new AEDPAException("Tipus d'activitat invalid.");
        }

=======
    public void altaActivitat() throws AEDPAException {
        String nom = demanarText("Nom: ");
        if (buscarActivitat(nom) != null) {
            throw new AEDPAException("Ja existeix aquesta activitat.");
        }
      LocalDate data = null;
boolean dataCorrecta = false;

while (!dataCorrecta) {
    try {
        data = LocalDate.parse(demanarText("Data (YYYY-MM-DD): "));
        int any = data.getYear();
        if (any >= 1980 && any <= 2050) {
            dataCorrecta = true;
        } else {
            System.out.println("ERROR: L'any ha d'estar entre 1980 i 2050.");
        }
        } catch (DateTimeParseException e) {
            throw new AEDPAException("Format de data incorrecte.");
        }
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
>>>>>>> main
        activitats.add(a);
    }

    /**
     * Elimina una actividad del sistema.
     * @throws AEDPAException si no hay actividades o no se encuentra la actividad
     * @throws PersistenciaException si hay error al guardar los datos
     */
<<<<<<< HEAD
    public void eliminarActivitat(String nom) throws AEDPAException, PersistenciaException {
        Activitat a = buscarActivitat(nom);

        if (a == null)
            throw new AEDPAException("Activitat no trobada.");

=======
    public void eliminarActivitat() throws AEDPAException, PersistenciaException {
        if (activitats.isEmpty()) {
            throw new AEDPAException("No hi ha activitats.");
        }
        Activitat a = buscarActivitat(demanarText("Nom: "));
        if (a == null) {
            throw new AEDPAException("No trobada.");
        }
>>>>>>> main
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
<<<<<<< HEAD
    
     public void inscriureActivitat(String dni, String nomAct) throws AEDPAException, PersistenciaException {

        Usuari u = buscarUsuari(dni);
        if (u == null)
            throw new AEDPAException("Usuari no trobat.");

        if (!u.potParticipar())
            throw new AEDPAException("Ha superat el limit de participacions.");

        Activitat a = buscarActivitat(nomAct);
        if (a == null)
            throw new AEDPAException("Activitat no trobada.");

        a.afegirParticipant(u);
        u.incrementarParticipaciones();

=======
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
>>>>>>> main
        PersistenciaClub.guardarActivitats(activitats);
    }

    /**
     * Muestra todas las actividades registradas.
     */
<<<<<<< HEAD
    public ArrayList<String> mostrarActivitats() {
        ArrayList<String> llista = new ArrayList<>();

        for (Activitat a : activitats) {
            llista.add(a.getNom() + " - " + a.getData());
        }

        return llista;
=======
    public void mostrarActivitats() {
        for (Activitat a : activitats) {
            System.out.println(a.getNom() + " - " + a.getData());
        }
>>>>>>> main
    }

    /**
     * Muestra la información de una actividad concreta junto a sus participantes.
     * @throws AEDPAException si no hay actividades o no se encuentra
     */
<<<<<<< HEAD
    public String mostrarActivitatEspecifica(String nom) throws AEDPAException {
        Activitat a = buscarActivitat(nom);

        if (a == null)
            throw new AEDPAException("Activitat no trobada.");

        String resultat = a.getNom() + "\n";

        if (a.getParticipants().isEmpty()) {
            resultat += "Sense participants.";
        } else {
            for (Usuari u : a.getParticipants()) {
                resultat += u.getNom() + "\n";
            }
        }

        return resultat;
=======
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
>>>>>>> main
    }

    /**
     * Muestra todas las baldas del sistema junto a su estado
     * (ocupada o libre) y el usuario asignado si corresponde.
     */    
<<<<<<< HEAD
   public ArrayList<String> mostrarBaldas() {
        ArrayList<String> llista = new ArrayList<>();

        for (Balda b : baldas.values()) {
            String estat = b.estaOcupada() ? "OCUPADA" : "LLIURE";
            String text = "Balda " + b.getId() + " - " + estat;

            if (b.estaOcupada()) {
                text += " (Soci: " + b.getAsignacionActual().getSocio().getNom() + ")";
            }

            llista.add(text);
        }

        return llista;
    }
    
=======
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
>>>>>>> main

    /**
     * Muestra el número de baldas libres y ocupadas.
     */
<<<<<<< HEAD
    public String mostrarDisponibilitatBaldas() {
        int lliures = 0;
        int ocupades = 0;

        for (Balda b : baldas.values()) {
            if (b.estaOcupada()) ocupades++;
            else lliures++;
        }

        return "Lliures: " + lliures + " | Ocupades: " + ocupades;
=======
    public void mostrarDisponibilidadBaldas() {
        int libres = 0;
        int ocupadas = 0;
        for (Balda b : baldas.values()) {
            if (b.estaOcupada()) ocupadas++;
            else libres++;
        }
        System.out.println("Lliures: " + libres + " | Ocupades: " + ocupadas);
>>>>>>> main
    }

    /**
     * Asigna una balda a un usuario socio durante un número de meses.
     * @throws AEDPAException si no hay usuarios, la balda no existe,
     * está ocupada o el usuario no es válido
     */
<<<<<<< HEAD
     public void asignarBalda(int id, String dni, int mesos) throws AEDPAException {
        Balda b = baldas.get(id);

        if (b == null)
            throw new AEDPAException("Balda no existeix.");

        if (b.estaOcupada())
            throw new AEDPAException("Balda ocupada.");

        Usuari u = buscarUsuari(dni);

        if (u == null || !u.esSoci())
            throw new AEDPAException("Usuari no valid.");

        if (mesos <= 0)
            throw new AEDPAException("Els mesos han de ser majors que 0.");

        b.asignar(new Asignacion(b, u, mesos));
=======
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
>>>>>>> main
    }

    /**
     * Libera una balda ocupada.
     * @throws AEDPAException si la balda no existe o ya está libre
     */
<<<<<<< HEAD
    public void liberarBalda(int id) throws AEDPAException {
       Balda b = baldas.get(id);
=======
    public void liberarBalda() throws AEDPAException {
        Balda b = baldas.get(demanarEnterMajorZero("ID: "));
>>>>>>> main
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
