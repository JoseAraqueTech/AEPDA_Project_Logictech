/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.clubsociosaepda;

import com.mycompany.clubsociosaepda.view.Menu;


/**
 * Clase principal de la aplicación.
 * 
 * <p>Se encarga de iniciar el programa.</p>
 */
public class ClubSociosAEPDA {

    /**
     * Método principal.
     * 
     * @param args argumentos de ejecución
     */
    public static void main(String[] args) {

        try {

            Menu menu = new Menu();
            menu.start();

        } catch (Exception e) {

            System.out.println("ERROR inesperado: " + e.getMessage());
        }
        
    }
    
}

