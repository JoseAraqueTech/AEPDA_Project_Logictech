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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que gestiona toda la lógica del club.
 * Se encarga de gestionar usuarios, actividades, baldas,
 * inscripciones y persistencia de datos.
 */
public class GestorClub {

    private ArrayList<Usuari> usuaris;
    private ArrayList<Activitat> activitats;
    private Map<Integer, Balda> baldas;
    

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
      
    }
/**
     * Creates all shelves in the system.
     */
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
 
    /**
     * Returns all active shelf assignments.
     */
    private ArrayList<Asignacion> getAsignacionesActivas() {
        ArrayList<Asignacion> lista = new ArrayList<>();
        for (Balda b : baldas.values()) {
            if (b.estaOcupada()) {
                lista.add(b.getAsignacionActual());
            }
        }
        return lista;
    }
     /**
     * Loads shelf assignments from persistence.
     */
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

    

    
/**
     * Searches a user by DNI.
     */
    private Usuari buscarUsuari(String dni) {
        for (Usuari u : usuaris) {
            if (u.getDni().equalsIgnoreCase(dni)) {
                return u;
            }
        }
        return null;
    }
/**
     * Searches an activity by name.
     */
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


    /**
     * Convierte un usuario en socio.
     * Solicita el DNI del usuario y los meses de membresía.
     * @throws AEDPAException si no hay usuarios, no existe el usuario
     * o ya es socio
     */
    public void ferSoci(String dni, int mesos) throws AEDPAException {
        if (usuaris.isEmpty()) {
            throw new AEDPAException("No hi ha usuaris.");
        }
        Usuari u = buscarUsuari(dni);
        if (u == null) {
            throw new AEDPAException("Usuari no trobat.");
        }
        if (u.esSoci()) {
            throw new AEDPAException("Ja es soci.");
        }
        u.ferSoci(mesos);
    }

    /**
     * Finaliza la membresía de un usuario.
     * @throws AEDPAException si no hay usuarios, no existe el usuario
     * o no es socio
     */
    public void finalitzarMembresia(String dni) throws AEDPAException {
         Usuari u = buscarUsuari(dni);

        if (u == null)
            throw new AEDPAException("Usuari no trobat.");
        
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

        activitats.add(a);
    }

    /**
     * Elimina una actividad del sistema.
     * @throws AEDPAException si no hay actividades o no se encuentra la actividad
     * @throws PersistenciaException si hay error al guardar los datos
     */
    public void eliminarActivitat(String nom) throws AEDPAException, PersistenciaException {
        Activitat a = buscarActivitat(nom);

        if (a == null)
            throw new AEDPAException("Activitat no trobada.");

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

        PersistenciaClub.guardarActivitats(activitats);
    }

    /**
     * Muestra todas las actividades registradas.
     */
    public ArrayList<String> mostrarActivitats() {
        ArrayList<String> llista = new ArrayList<>();

        for (Activitat a : activitats) {
            llista.add(a.getNom() + " - " + a.getData());
        }

        return llista;
    }

    /**
     * Muestra la información de una actividad concreta junto a sus participantes.
     * @throws AEDPAException si no hay actividades o no se encuentra
     */
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
    }

    /**
     * Muestra todas las baldas del sistema junto a su estado
     * (ocupada o libre) y el usuario asignado si corresponde.
     */    
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
    

    /**
     * Muestra el número de baldas libres y ocupadas.
     */
    public String mostrarDisponibilitatBaldas() {
        int lliures = 0;
        int ocupades = 0;

        for (Balda b : baldas.values()) {
            if (b.estaOcupada()) ocupades++;
            else lliures++;
        }

        return "Lliures: " + lliures + " | Ocupades: " + ocupades;
    }

    /**
     * Asigna una balda a un usuario socio durante un número de meses.
     * @throws AEDPAException si no hay usuarios, la balda no existe,
     * está ocupada o el usuario no es válido
     */
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
    }

    /**
     * Libera una balda ocupada.
     * @throws AEDPAException si la balda no existe o ya está libre
     */
    public void liberarBalda(int id) throws AEDPAException {
       Balda b = baldas.get(id);
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