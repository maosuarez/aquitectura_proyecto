package com.magnix.app.factory.concreteProducts;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.magnix.app.factory.abstractProducts.ATournament;
import com.magnix.app.factory.dependencies.Bracket;
import com.magnix.app.factory.dependencies.RuleSet;

public class TournamentBasket extends ATournament {

    public TournamentBasket(){
        super();
    }

    @Override
    public void openRegistration() {
        registrationOpen = true;
        JOptionPane.showMessageDialog(null, "📢 Registro abierto para el torneo de baloncesto.");
    }

    @Override
    public void closeRegistration() {
        registrationOpen = false;
        JOptionPane.showMessageDialog(null, "🚫 Registro cerrado para el torneo de baloncesto.");
    }

    @Override
    public void manageBracket() {
        bracket = new Bracket();
        JOptionPane.showMessageDialog(null, "🧩 Bracket configurado: " + bracket.getStructureName());
    }

    @Override
    public void setRules() {
        JTextField descField = new JTextField();
        JTextField minField = new JTextField();
        JTextField maxField = new JTextField();
        JTextField catField = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Descripción del torneo de baloncesto:"));
        panel.add(descField);
        panel.add(new JLabel("Mínimo de jugadores por equipo (Ej: 5):"));
        panel.add(minField);
        panel.add(new JLabel("Máximo de jugadores por equipo (Ej: 12):"));
        panel.add(maxField);
        panel.add(new JLabel("Categoría (Ej: Universitario, Libre):"));
        panel.add(catField);

        int result = JOptionPane.showConfirmDialog(null, panel, "🏀 Reglas de Baloncesto", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            rules = new RuleSet(
                descField.getText(),
                Integer.parseInt(minField.getText()),
                Integer.parseInt(maxField.getText()),
                catField.getText()
            );
            JOptionPane.showMessageDialog(null, "✅ Reglas de baloncesto configuradas.");
        }
    }

}
