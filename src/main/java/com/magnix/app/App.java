package com.magnix.app;

import java.awt.Dimension;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
        UIEnhancer.enhanceLookAndFeel();

        Club club = new Club("UniSabana", "Calle 22 #13 - 77");

        String mensaje = "👋 ¡Hola! Bienvenido a *Magnix Sport Manager*\n\n"
                       + "🏟️ Club: " + club.getName() + "\n"
                       + "📍 Dirección: " + club.getAddress();

        // Crear área de texto con estilo
        JTextArea area = new JTextArea(mensaje);
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBackground(null);

        // Envolver en scroll para evitar cortes
        JScrollPane scroll = new JScrollPane(area);
        scroll.setPreferredSize(new Dimension(450, 200)); // Ajusta según tu pantalla

        // Mostrar opción con botón personalizado
        String[] opciones = {"Comenzar"};
        JOptionPane.showOptionDialog(
            null,
            scroll,
            "Bienvenida",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );


        menu(club);
    }

    static void menu(Club club){

        String[] opciones = {"1. Nuevo Deporte", "2. Ver Deportes", "3. Ver Reservas", "4. Ver Torneos", "5. Community", "Salir"};

        while (true) {
            int seleccion = JOptionPane.showOptionDialog(
                null,
                "Selecciona una opción:",
                "Menú",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]
            );

            if (seleccion == -1 || seleccion == 5) { // Cerrar ventana o "Salir"
                JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                break;
            }

            switch (seleccion) {
                case 0 : {
                    club.addSport();
                    break;
                }
                case 1 : {
                    club.viewSports();;
                    break;
                }
                case 2 : {
                    club.showBooking();
                    break;
                }
                case 3 : {
                    club.showTournament();
                    break;
                }
                case 4: {
                    club.showCommunity();
                    break;
                }
            }
        }

    }
}
