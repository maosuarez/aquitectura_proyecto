package com.magnix.app;

import javax.swing.*;
import java.awt.*;

public class UIEnhancer {
    public static void enhanceLookAndFeel() {
        // Fuente moderna y grande
        Font modernFont = new Font("SansSerif", Font.PLAIN, 35); // Puedes usar "Roboto", "Noto Sans", etc.

        // Colores personalizados
        Color backgroundColor = new Color(245, 245, 245); // Gris claro
        Color foregroundColor = new Color(33, 33, 33);     // Texto oscuro
        Color buttonBackground = new Color(220, 220, 220); // Botón claro
        Color buttonForeground = new Color(0, 0, 0);       // Texto de botón
        Color comboBoxBackground = new Color(255, 255, 255); // Fondo del combo
        Color comboBoxForeground = new Color(0, 0, 0);        // Texto del combo
        Color comboBoxSelectionBackground = new Color(200, 230, 255); // Fondo al seleccionar
        Color comboBoxSelectionForeground = new Color(0, 0, 0);       // Texto al seleccionar

        // Aplicar fuente
        UIManager.put("OptionPane.messageFont", modernFont);
        UIManager.put("OptionPane.buttonFont", modernFont);
        UIManager.put("TextField.font", modernFont);
        UIManager.put("TextArea.font", modernFont);
        UIManager.put("Label.font", modernFont);
        UIManager.put("Button.font", modernFont);
        UIManager.put("ComboBox.font", modernFont);

        // Aplicar colores
        UIManager.put("OptionPane.background", backgroundColor);
        UIManager.put("Panel.background", backgroundColor);
        UIManager.put("OptionPane.messageForeground", foregroundColor);
        UIManager.put("Button.background", buttonBackground);
        UIManager.put("Button.foreground", buttonForeground);
        UIManager.put("Label.foreground", foregroundColor);
        UIManager.put("TextField.background", Color.WHITE);
        UIManager.put("TextField.foreground", foregroundColor);

        // Estilo para JComboBox
        UIManager.put("ComboBox.background", comboBoxBackground);
        UIManager.put("ComboBox.foreground", comboBoxForeground);
        UIManager.put("ComboBox.selectionBackground", comboBoxSelectionBackground);
        UIManager.put("ComboBox.selectionForeground", comboBoxSelectionForeground);
        UIManager.put("ComboBox.buttonBackground", buttonBackground);
        UIManager.put("ComboBox.buttonForeground", buttonForeground);
    }
}

