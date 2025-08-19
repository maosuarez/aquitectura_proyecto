package com.magnix.app.factory.dependencies;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.magnix.app.factory.Team;

public class Bracket {

    private ArrayList<Match> matches;
    private int rounds;
    private String structureName; // 🆕 Tipo de estructura


    // Constructor interactivo
    public Bracket() {
        this.matches = new ArrayList<>();

        JTextField roundsField = new JTextField();
        JTextField structureField = new JTextField();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("🎯 ¿Cuántas rondas tendrá el bracket?"));
        panel.add(roundsField);
        panel.add(new JLabel("📘 Tipo de estructura (Ej: Eliminación directa, Grupos, Mixto):"));
        panel.add(structureField);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Crear Bracket", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int r = Integer.parseInt(roundsField.getText());
                if (r <= 0) throw new NumberFormatException();
                this.rounds = r;
            } catch (NumberFormatException e) {
                this.rounds = 1;
                JOptionPane.showMessageDialog(null,
                        "⚠️ Entrada inválida. Se asigna 1 ronda por defecto.",
                        "Advertencia", JOptionPane.WARNING_MESSAGE);
            }

            this.structureName = structureField.getText().isEmpty()
                    ? "Estructura no especificada"
                    : structureField.getText();

            JOptionPane.showMessageDialog(null,
                    "✅ Bracket creado con " + rounds + " rondas y estructura: " + structureName,
                    "Confirmación", JOptionPane.INFORMATION_MESSAGE);
        } else {
            this.rounds = 1;
            this.structureName = "Estructura cancelada";
            JOptionPane.showMessageDialog(null,
                    "🚫 Creación de bracket cancelada.",
                    "Cancelado", JOptionPane.WARNING_MESSAGE);
        }
    }

    // 🆕 Método solicitado
    public String getStructureName() {
        return structureName;
    }

    // Generar matches con JOptionPane
    public void generateMatches(ArrayList<Team> teams) {
        if (teams == null || teams.size() < 2) {
            JOptionPane.showMessageDialog(null,
                "❌ No hay suficientes equipos para generar partidos.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        StringBuilder resumen = new StringBuilder();
        resumen.append("🎮 Generando partidos para ").append(rounds).append(" rondas...\n\n");

        for (int i = 0; i < teams.size() - 1; i += 2) {
            Team t1 = teams.get(i);
            Team t2 = teams.get(i + 1);

            Match match = new Match(t1, t2, LocalDateTime.now());
            matches.add(match);

            resumen.append("🆚 ").append(t1.getName()).append(" vs ").append(t2.getName()).append("\n");
        }

        if (teams.size() % 2 != 0) {
            Team bye = teams.get(teams.size() - 1);
            resumen.append("\n🚀 ").append(bye.getName()).append(" avanza automáticamente (bye).\n");
        }

        JOptionPane.showMessageDialog(null,
            resumen.toString(),
            "Partidos generados",
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Gestionar un match con resultado
    public void gestionarMatch(Match m, MatchResult result) {
        if (m == null) {
            JOptionPane.showMessageDialog(null,
                "❌ El partido es inválido.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!matches.contains(m)) {
            matches.add(m);
            JOptionPane.showMessageDialog(null,
                "📥 Partido agregado al bracket.",
                "Gestión de partido",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                "🔄 Gestionando partido existente...",
                "Gestión de partido",
                JOptionPane.INFORMATION_MESSAGE);
        }

        if (result != null) {
            m.newResult(result);
            JOptionPane.showMessageDialog(null,
                "✅ Resultado actualizado para el partido:\n" +
                m.getPlayer1().getName() + " vs " + m.getPlayer2().getName(),
                "Resultado",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                "⚠️ No se proporcionó resultado para este partido.",
                "Sin resultado",
                JOptionPane.WARNING_MESSAGE);
        }
    }

    // Getters y Setters
    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }
}

