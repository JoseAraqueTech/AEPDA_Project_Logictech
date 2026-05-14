/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.clubsociosaepda;

import com.mycompany.clubsociosaepda.view.gui.Principal;
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
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Principal gui = new Principal();
        gui.setVisible(true);
    }
    
}

