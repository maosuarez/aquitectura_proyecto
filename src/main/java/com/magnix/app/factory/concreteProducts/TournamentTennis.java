package com.magnix.app.factory.concreteProducts;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.magnix.app.factory.abstractProducts.ATournament;
import com.magnix.app.factory.dependencies.Bracket;
import com.magnix.app.factory.dependencies.RuleSet;

public class TournamentTennis extends ATournament {

    public TournamentTennis(){
        super();
    }

    @Override
    public void openRegistration() {
        registrationOpen = true;
        JOptionPane.showMessageDialog(null, "📢 Registro abierto para el torneo de tenis.");
    }

    @Override
    public void closeRegistration() {
        registrationOpen = false;
        JOptionPane.showMessageDialog(null, "🚫 Registro cerrado para el torneo de tenis.");
    }

    @Override
    public void manageBracket() {
        bracket = new Bracket();
        JOptionPane.showMessageDialog(null, "🧩 Bracket configurado: " + bracket.getStructureName());
    }

    @Override
    public void setRules() {
        JTextField descField = new JTextField();
        JTextField catField = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Descripción del torneo de tenis:"));
        panel.add(descField);
        panel.add(new JLabel("Categoría (Ej: Masculino, Femenino, Mixto):"));
        panel.add(catField);

        int result = JOptionPane.showConfirmDialog(null, panel, "🎾 Reglas de Tenis", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            rules = new RuleSet(
                descField.getText(),
                1, // mínimo por equipo (individual)
                2, // máximo por equipo (dobles)
                catField.getText()
            );
            JOptionPane.showMessageDialog(null, "✅ Reglas de tenis configuradas.");
        }
    }
}

