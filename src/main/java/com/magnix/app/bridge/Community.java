package com.magnix.app.bridge;

import javax.swing.JOptionPane;

import com.magnix.app.bridge.theme.Dark;
import com.magnix.app.bridge.theme.Light;

public class Community {
    public static IPostBridge chooseTheme() {
        String[] opciones = {"Claro", "Oscuro"};
        int seleccion = JOptionPane.showOptionDialog(
            null, "🎨 Elige un tema visual:",
            "Preferencias de Estilo",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null, opciones, opciones[0]);

        return (seleccion == 1) ? new Dark() : new Light();
    }
}
