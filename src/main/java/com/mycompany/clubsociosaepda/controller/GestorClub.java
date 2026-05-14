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
import com.mycompany.clubsociosaepda.exception.AEDPAException;
import com.mycompany.clubsociosaepda.exception.PersistenciaException;
import com.mycompany.clubsociosaepda.model.enums.NivellPintura;
import com.mycompany.clubsociosaepda.persistence.AepdaDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que gestiona toda la lógica del club. Se encarga de gestionar usuarios,
 * actividades, baldas, inscripciones y persistencia de datos.
 */
public class GestorClub {

    private AepdaDAO aepdadao;

    /**
     * Constructor del gestor del club. Inicializa el DAO de acceso a la base de
     * datos.
     *
     * @throws PersistenciaException si hay error al cargar datos
     * @throws AEDPAException si ocurre un error de logica
     */
    public GestorClub() throws PersistenciaException, AEDPAException {
        aepdadao = new AepdaDAO();
    }

    // =========================================================================
    // USUARIOS
    // =========================================================================

    /**
     * Registra un nuevo usuario en el sistema. Valida el DNI, el email y que no
     * exista ya un usuario con el mismo DNI.
     *
     * @param dni   DNI del usuario
     * @param nom   nombre del usuario
     * @param email correo electrónico del usuario
     * @throws AEDPAException si los datos son incorrectos o el usuario ya existe
     * @throws SQLException   si ocurre un error de base de datos
     */
    public void altaUsuari(String dni, String nom, String email)
            throws AEDPAException, SQLException {
        if (!Usuari.esDniValid(dni)) {
            throw new AEDPAException("DNI no valid.");
        }
        if (aepdadao.buscarUsuari(dni)) {
            throw new AEDPAException("Aquest usuari ja existeix.");
        }
        if (!Usuari.esEmailValid(email)) {
            throw new AEDPAException("Email no valid.");
        }
        aepdadao.insertarUsuari(dni, nom, email);
    }

    /**
     * Convierte un usuario en socio.
     *
     * @param dni   DNI del usuario
     * @param mesos meses de membresía
     * @throws AEDPAException si el usuario no existe o ya es socio
     * @throws SQLException   si ocurre un error de base de datos
     */
    public void ferSoci(String dni, int mesos) throws AEDPAException, SQLException {
        if (!aepdadao.buscarUsuari(dni)) {
            throw new AEDPAException("Usuari no trobat.");
        }
        if (aepdadao.esSoci(dni)) {
            throw new AEDPAException("Ja es soci.");
        }
        aepdadao.ferSoci(dni, mesos);
    }

    /**
     * Finaliza la membresía de un usuario.
     *
     * @param dni DNI del usuario
     * @throws AEDPAException si el usuario no existe o no es socio
     * @throws SQLException   si ocurre un error de base de datos
     */
    public void finalitzarMembresia(String dni) throws AEDPAException, SQLException {
        if (!aepdadao.buscarUsuari(dni)) {
            throw new AEDPAException("Usuari no trobat.");
        }
        if (!aepdadao.esSoci(dni)) {
            throw new AEDPAException("No es soci.");
        }
        aepdadao.finalitzarMembresia(dni);
    }

    /**
     * Muestra la información de un usuario a partir de su DNI.
     *
     * @param dni DNI del usuario
     * @return lista con el usuario encontrado (vacía si no existe)
     * @throws AEDPAException si el usuario no existe
     * @throws SQLException   si ocurre un error de base de datos
     */
    public List<Usuari> mostrarUsuari(String dni) throws AEDPAException, SQLException {
        if (!aepdadao.buscarUsuari(dni)) {
            throw new AEDPAException("Usuari no trobat.");
        }
        return aepdadao.mostrarUsuari(dni);
    }

    /**
     * Elimina un usuario del sistema.
     * NUEVO: faltaba en GestorClub; Menu.java lo llamaba.
     *
     * @param dni DNI del usuario a eliminar
     * @throws AEDPAException      si el usuario no existe
     * @throws PersistenciaException si ocurre un error de persistencia
     * @throws SQLException         si ocurre un error de base de datos
     */
    public void eliminarUsuari(String dni) throws AEDPAException, PersistenciaException, SQLException {
        if (!aepdadao.buscarUsuari(dni)) {
            throw new AEDPAException("Usuari no trobat.");
        }
        aepdadao.eliminarUsuari(dni);
    }

    // =========================================================================
    // ACTIVIDADES
    // =========================================================================

    /**
     * Crea una nueva actividad en el sistema.
     *
     * @param id_activitat identificador de la actividad
     * @param nom          nombre de la actividad
     * @param data         fecha de la actividad
     * @param tipus        tipo (1 = Torneig, 2 = CursPintura)
     * @param professor    profesor (solo para CursPintura)
     * @param nivell       nivel de curso de pintura
     * @throws AEDPAException si la actividad ya existe, la fecha es inválida o el tipo es incorrecto
     * @throws SQLException   si ocurre un error de base de datos
     */
    public void altaActivitat(int id_activitat, String nom, LocalDate data, int tipus, String professor, NivellPintura nivell)
            throws AEDPAException, SQLException {
        if (aepdadao.buscarActivitat(id_activitat)) {
            throw new AEDPAException("Ja existeix aquesta activitat.");
        }
        int any = data.getYear();
        if (any < 1980 || any > 2050) {
            throw new AEDPAException("L'any ha d'estar entre 1980 i 2050.");
        }
        Activitat a;
        if (tipus == 1) {
            a = new Torneig(id_activitat, nom, data);
        } else if (tipus == 2) {
            if (professor == null || professor.isEmpty()) {
                throw new AEDPAException("El professor no pot estar buit.");
            }
            a = new CursPintura(id_activitat, nom, data, professor, nivell);
        } else {
            throw new AEDPAException("Tipus d'activitat invalid.");
        }
        aepdadao.insertarActivitat(a);
    }

    /**
     * Elimina una actividad del sistema.
     *
     * @param id_activitat identificador de la actividad
     * @throws AEDPAException si la actividad no existe
     * @throws SQLException   si ocurre un error de base de datos
     */
    public void eliminarActivitat(int id_activitat) throws AEDPAException, SQLException {
        if (!aepdadao.buscarActivitat(id_activitat)) {
            throw new AEDPAException("Activitat no trobada.");
        }
        aepdadao.eliminarActivitat(id_activitat);
    }

    /**
     * Comprueba si una actividad es de tipo Torneig.
     *
     * @param id_activitat identificador de la actividad
     * @return true si es Torneig, false si es CursPintura
     * @throws AEDPAException si la actividad no existe
     * @throws SQLException   si ocurre un error de base de datos
     */
    public boolean esTorneig(int id_activitat) throws AEDPAException, SQLException {
        if (!aepdadao.buscarActivitat(id_activitat)) {
            throw new AEDPAException("Activitat no trobada.");
        }
        return aepdadao.esTorneig(id_activitat);
    }

    /**
     * Inscribe a un usuario en una actividad.
     *
     * @param id_activitat identificador de la actividad
     * @param dni          DNI del usuario
     * @throws AEDPAException      si el usuario o la actividad no existen
     * @throws PersistenciaException si ocurre un error de persistencia
     * @throws SQLException         si ocurre un error de base de datos
     */
    public void inscriureActivitat(int id_activitat, String dni)
            throws AEDPAException, PersistenciaException, SQLException {
        if (!aepdadao.buscarUsuari(dni)) {
            throw new AEDPAException("Usuari no trobat.");
        }
        if (!aepdadao.buscarActivitat(id_activitat)) {
            throw new AEDPAException("Activitat no trobada.");
        }
        aepdadao.inscribirUsuari(id_activitat, dni);
    }

    /**
     * Muestra las actividades del sistema. Si id_activitat es 0 lista todas;
     * si se indica un id concreto muestra solo esa actividad.
     *
     * @param id_activitat id de la actividad (0 = todas)
     * @return 
     * @throws AEDPAException      si la actividad concreta no existe
     * @throws SQLException         si ocurre un error de base de datos
     */
    public List<Activitat> mostrarActivitats(int id_activitat) throws AEDPAException, SQLException {
        if (id_activitat == 0) {
            return aepdadao.listarActivitats();
        } else {
            if (!aepdadao.buscarActivitat(id_activitat)) {
                throw new AEDPAException("Activitat no trobada.");
            }
            return aepdadao.listaActivitat(id_activitat);
        }
    }

    /**
     * Muestra la información de una actividad buscándola por nombre.
     *
     * @param nom nombre de la actividad
     * @return representación en texto de la actividad
     * @throws AEDPAException si no se encuentra ninguna actividad con ese nombre
     * @throws SQLException   si ocurre un error de base de datos
     */
    public String mostrarActivitatEspecifica(String nom) throws AEDPAException, SQLException {
        List<Activitat> totes = aepdadao.listarActivitats();
        for (Activitat a : totes) {
            if (a.getNom().equalsIgnoreCase(nom)) {
                return a.toString();
            }
        }
        throw new AEDPAException("Activitat '" + nom + "' no trobada.");
    }

    // =========================================================================
    // BALDAS
    // =========================================================================

    /**
     * Crea una nueva balda.
     *
     * @param id       identificador de la balda
     * @param ubicacion ubicación de la balda
     * @throws AEDPAException si la balda ya existe
     * @throws SQLException   si ocurre un error de base de datos
     */
    public void crearBalda(int id, String ubicacion) throws AEDPAException, SQLException {
        if (aepdadao.buscarBalda(id)) {
            throw new AEDPAException("La balda ja existeix.");
        }
        aepdadao.insertarBalda(id, ubicacion);
    }

    /**
     * Modifica la ubicación de una balda existente.
     *
     * @param id       identificador de la balda a modificar
     * @param ubicacion nueva ubicación
     * @throws AEDPAException      si la balda no existe
     * @throws PersistenciaException si ocurre un error de persistencia
     * @throws SQLException         si ocurre un error de base de datos
     */
    public void modBalda(int id, String ubicacion)
            throws AEDPAException, PersistenciaException, SQLException {
        if (!aepdadao.buscarBalda(id)) {
            throw new AEDPAException("Balda no trobada.");
        }
        aepdadao.actualizarBalda(id, ubicacion);
    }

    /**
     * Devuelve la lista de todas las baldas formateadas como cadenas de texto.
     *
     * @return lista de strings con la información de cada balda
     */
    public List<String> mostrarBaldas() {
        List<String> resultat = new ArrayList<>();
        try {
            List<Balda> baldas = aepdadao.listarBaldas();
            for (Balda b : baldas) {
                resultat.add(b.toString());
            }
        } catch (SQLException e) {
            resultat.add("Error carregant baldes: " + e.getMessage());
        }
        return resultat;
    }

    /**
     * Devuelve un resumen de la disponibilidad de baldas (libres / ocupadas).
     *
     * @return texto con el número de baldas totales, ocupadas y libres
     */
    public String mostrarDisponibilitatBaldas() {
        try {
            List<Balda> totes = aepdadao.listarBaldas();
            List<Balda> ocupades = aepdadao.listarBaldasOcupadas();
            int total = totes.size();
            int ocupades_num = ocupades.size();
            int lliures = total - ocupades_num;
            return "Total baldes: " + total
                    + " | Ocupades: " + ocupades_num
                    + " | Lliures: " + lliures;
        } catch (SQLException e) {
            return "Error carregant disponibilitat: " + e.getMessage();
        }
    }

    /**
     * Asigna una balda a un socio durante un número de meses.
     *
     * @param id    identificador de la balda
     * @param dni   DNI del socio
     * @param mesos número de meses de asignación
     * @throws AEDPAException si la balda no existe, el usuario no existe o no es socio
     * @throws SQLException   si ocurre un error de base de datos
     */
    public void asignarBalda(int id, String dni, int mesos) throws AEDPAException, SQLException {
        if (!aepdadao.buscarBalda(id)) {
            throw new AEDPAException("Balda no trobada.");
        }
        if (!aepdadao.buscarUsuari(dni)) {
            throw new AEDPAException("Usuari no trobat.");
        }
        if (!aepdadao.esSoci(dni)) {
            throw new AEDPAException("L'usuari no es soci. Nomes els socis poden tenir una balda.");
        }
        aepdadao.asignarBalda(dni, id);
    }

    /**
     * Libera una balda ocupada.
     *
     * @param id identificador de la balda
     * @throws AEDPAException si la balda no existe
     * @throws SQLException   si ocurre un error de base de datos
     */
    public void liberarBalda(int id) throws AEDPAException, SQLException {
        if (!aepdadao.buscarBalda(id)) {
            throw new AEDPAException("Balda no trobada.");
        }
        aepdadao.liberarBalda(id);
    }
}