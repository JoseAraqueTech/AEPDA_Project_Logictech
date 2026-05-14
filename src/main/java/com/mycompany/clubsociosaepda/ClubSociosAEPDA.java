/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.clubsociosaepda;

import com.mycompany.clubsociosaepda.view.Menu;
import com.mycompany.clubsociosaepda.view.Principal;
import java.io.IOException;


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
    public static void main(String[] args) throws IOException {
        Principal gui = new Principal();
        gui.setVisible(true);
    }
    
}

