/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clubsociosaepda;

import com.mycompany.clubsociosaepda.ClasesAEPDA.Usuari;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Activitat;
import com.mycompany.clubsociosaepda.ClasesAEPDA.Balda;
import com.mycompany.clubsociosaepda.PersistenciaAEPDA.PersistenciaClub;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author juan-
 */
public class GestorClub {

    private ArrayList<Usuari> usuaris;
    private ArrayList<Activitat> activitats;
    private ArrayList<Balda> baldes;
    private Scanner sc;

    public GestorClub() throws IOException {
        usuaris = PersistenciaClub.carregarUsuaris();
        activitats = PersistenciaClub.carregarActivitats();
        baldes = generarBaldes();
        sc = new Scanner(System.in);
    }

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
        System.out.println("9. Mostrar balda");
        System.out.println("10. Assignar balda");
        System.out.println("11. Alliberar balda");
        System.out.println("0. Sortir");
        System.out.print("Opcio: ");
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
    
    //Método que comprueba que el numero entero qeu se introduzca sea mayor a cero
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
    
    //Método que recorre el arrayList de Usuari buscando que 
    //El valor coincida con los datos introducidos
    private Usuari buscarUsuari(String dni) {
        Usuari resultat = null;
        for (Usuari u : usuaris) {
            if (u.getDni().equalsIgnoreCase(dni)) {
                resultat = u;
            }
        }
        return resultat;
    }

    //Método que recorre el arrayList de Activitat buscando que 
    //El valor coincida con los datos introducidos
    private Activitat buscarActivitat(String nom) {
        Activitat resultat = null;
        for (Activitat a : activitats) {
            if (a.getNom().equalsIgnoreCase(nom)) {
                resultat = a;
            }
        }
        return resultat;
    }

    //Método que pide, Dni, nombre y email para luego
    //De confirmar que no haya conflictos crear un nuevo usuario
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

    //Método para darse de alta como socio, comprueba que hayan usuarios
    //Pedirá el dni para buscar al usuario específico, si no hay
    //Conflictos se pedirá la duracion de la memebresia y se dará de alta
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

    
    //Método que finalizará la membresia, pedira lo mismo que el metodo fersoci
    //Si no hay conflictos, finalizará la membresía
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

    //Dar de alta actividades, pedirá, nombre
    //Si no hay conflictos, fecha de la actividad y la dará de alta
    public void altaActivitat() {
        String nom = demanarText("Nom activitat: ");
        Activitat existent = buscarActivitat(nom);
        if (existent != null) {
            System.out.println("ERROR: Aquesta activitat ja existeix.");
        } else {
            String data = demanarText("Data activitat: ");
            activitats.add(new Activitat(nom, data));
            System.out.println("Activitat creada correctament.");
        }
    }

    //Dar de baja la actividad, comprueba que hayan actividades creadas
    //Pedirá el nombre de la actividad y la buscara por el arrayList de Activitat
    //Si la encuentra borrará la actividad y guardará los datos actualizados
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

    //Comprueba que hayan usuarios creados previamente
    //Comprueba que hayan actividades creadas previamente
    //Pedirá DNI y lo buscará por el arrayList
    //Si no es socio no lo dejará inscribirse
    //si es socio pedirá el nombre de la actividad y
    //Agregará al usuario y guardará los datos
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

    //Comprueba actividades existentes, pide nombre
    // y comprueba que existe en el arraylist
    //Mostrará el nombre, fecha y participantes(DNI y nombre)
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

    //Método donde se guardan los datos de usuarios y actividades
    public void guardar() throws IOException {
        PersistenciaClub.guardarUsuaris(usuaris);
        PersistenciaClub.guardarActivitats(activitats);
        System.out.println("Dades guardades correctament.");
    }
    
    //Método para buscar baldas y que no de error si introduce la letra en mayuscula   minuscula
    
    private Balda buscarBalda(String codi){
        Balda resultat = null;
        for (Balda b : baldes) {
            if (b.getcodi().equalsIgnoreCase(codi)){
                resultat = b;
            }
        }
        return resultat;
    }
    
    //Este método crea todas las baldas de las tres zonas automaticamente
    
    private ArrayList<Balda> generarBaldes() {
        ArrayList<Balda> llista =  new ArrayList<>();
        
        String[] zona1 = { "A","B","C","D","E"};
        for (String lletra : zona1) {
            for (int i = 1; i <=18; i++) {
                llista.add(new Balda(lletra + "-" + i));
            }
        }
        //Zona 2: V-Z numeros 1 al 18
        String[] zona2 = {"V","W","X","Y","Z"};
        for (String lletra : zona2) {
            for (int i = 1; i <=8; i++) {
                llista.add(new Balda(lletra + "-" +i));
            }
        }
        //zona3 H -L i numeros del 1 al 13
        String [] zona3 = {"H", "I", "J","K","L"};
        for (String lletra : zona3){
            for (int i = 1; i <=13; i++) {
                llista.add(new Balda(lletra + "-" + i));
            }
        }
        return llista;
    }
    
  public String mostrarBaldes() {
    String resultat = "----- Baldes -----\n";
    for (Balda b : baldes) {
        resultat += b.toString() + "\n";
    }
    return resultat;
}

public String assignarBalda(String dni, String codi) {
    Usuari u = buscarUsuari(dni);
    if (u == null) {
        return "Usuari no trobat.";
    } else if (!u.esSoci()) {
        return "ERROR: L'usuari no es soci.";
    } else if (u.getMesosMembresia() < 3) {
        return "ERROR: Mínim 3 mesos de membresía per tenir balda.";
    }
    Balda b = buscarBalda(codi);
    if (b == null) {
        return "Balda no trobada.";
    } else if (b.isOcupada()) {
        return "ERROR: Aquesta balda ja està ocupada.";
    }
    b.assignar(u);
    return "Balda " + codi.toUpperCase() + " assignada a " + u.getNom();
}

public String alliberarBalda(String codi) {
    Balda b = buscarBalda(codi);
    if (b == null) {
        return "Balda no trobada.";
    } else if (!b.isOcupada()) {
        return "Aquesta balda ja està lliure.";
    }
    b.alliberar();
    return "Balda " + codi.toUpperCase() + " alliberada correctament.";
}
}