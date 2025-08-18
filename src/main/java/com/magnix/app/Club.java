package com.magnix.app;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.magnix.app.singleton.Equipment;
import com.magnix.app.singleton.GameFormat;
import com.magnix.app.singleton.ListSports;
import com.magnix.app.bridge.Community;
import com.magnix.app.bridge.IPostBridge;
import com.magnix.app.bridge.Post;
import com.magnix.app.bridge.PostType;
import com.magnix.app.factory.FactorySport;
import com.magnix.app.factory.Team;
import com.magnix.app.factory.concreteFactories.*;
import com.magnix.app.factory.abstractProducts.ABooking;
import com.magnix.app.factory.abstractProducts.ATournament;
// import com.magnix.app.bridge.IPost;
import com.magnix.app.singleton.Sport;



public class Club {
    private String name;
    private String address;

    private ListSports listSport;
    private ArrayList<ABooking> listBooking = new ArrayList<>();
    private ArrayList<ATournament> listTournament = new ArrayList<>();
    // private ArrayList<IPost> feedCommunity = new ArrayList<>();

    private FactorySport factorySport;

    public Club(String name, String address) {
        this.name = name;
        this.address = address;
        this.listSport = ListSports.getInstance();
    }

    public void addSport(){
        try {
            // 📥 Datos básicos del deporte
            String name = JOptionPane.showInputDialog("Nombre del deporte:");
            int spaces = Integer.parseInt(JOptionPane.showInputDialog("Número de espacios:"));
            double cost = Double.parseDouble(JOptionPane.showInputDialog("Costo por sesión:"));

            // 🎮 Selección de formato de juego
            String[] formats = {"SINGLE", "TEAM", "BOTH"};
            String formatInput = (String) JOptionPane.showInputDialog(
                    null,
                    "Formato de juego:",
                    "Selecciona formato",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    formats,
                    formats[0]
            );
            GameFormat format = GameFormat.valueOf(formatInput.toUpperCase());

            // 🧰 Equipamiento
            ArrayList<Equipment> equipmentList = new ArrayList<>();
            int addMore;
            do {
                String equipName = JOptionPane.showInputDialog("Nombre del equipamiento:");
                equipmentList.add(new Equipment(equipName));
                addMore = JOptionPane.showConfirmDialog(null, "¿Agregar otro equipamiento?", "Equipamiento", JOptionPane.YES_NO_OPTION);
            } while (addMore == JOptionPane.YES_OPTION);

            // 🏗️ Crear Sport y agregar a la lista
            Sport sport = new Sport(name, spaces, cost, format);
            sport.setEquipment(equipmentList);
            listSport.addSport(sport);

            JOptionPane.showMessageDialog(null, "¡Deporte creado y agregado exitosamente!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el deporte: " + e.getMessage());
        }
    }

    public void showCommunity(){
        IPostBridge theme = Community.chooseTheme(); // Elige tema visual

        String author = JOptionPane.showInputDialog("👤 Ingresa tu nombre:");
        String content = JOptionPane.showInputDialog("📝 Escribe tu publicación:");

        String[] tipos = {"SPONSOR", "IMAGE", "VIDEO", "TEXT"};
        String tipoSeleccionado = (String) JOptionPane.showInputDialog(
            null, "🏷️ Tipo de publicación:", "Clasificación",
            JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);

        PostType tipo = PostType.valueOf(tipoSeleccionado);

        Post post = new Post(author, content, tipo, theme);
        post.display();
    }

    public void viewSports() {
        List<Sport> sports = listSport.getSports();

        if (sports.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay deportes registrados.");
            return;
        }

        // 🧭 Selección de deporte
        String[] sportNames = sports.stream().map(Sport::getName).toArray(String[]::new);
        String selectedName = (String) JOptionPane.showInputDialog(
                null,
                "Selecciona un deporte:",
                "Lista de Deportes",
                JOptionPane.QUESTION_MESSAGE,
                null,
                sportNames,
                sportNames[0]
        );

        if (selectedName == null) return;

        Sport selectedSport = listSport.findByName(selectedName);

        // 🔧 Menú de acciones
        String[] actions = {"Editar", "Eliminar", "Reservar", "Crear Torneo", "Cancelar"};
        String selectedAction = (String) JOptionPane.showInputDialog(
                null,
                "¿Qué deseas hacer con " + selectedSport.getName() + "?",
                "Acciones disponibles",
                JOptionPane.QUESTION_MESSAGE,
                null,
                actions,
                actions[0]
        );

        if (selectedAction == null || selectedAction.equals("Cancelar")) return;

        factorySport = getFactoryForSport(selectedSport);

        switch (selectedAction) {
            case "Editar": {manageSport(selectedSport);
            break;}
            case "Eliminar" : {removeSport(selectedSport);
            break;}
            case "Reservar": {addBooking();
            break;}
            case "Crear Torneo": {addTournament();
            break;}
        }
    }

    public void showBooking() {
        if (listBooking == null || listBooking.isEmpty()) {
            JOptionPane.showMessageDialog(null, "📭 No hay listBooking disponibles.", "Sin listBooking", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int index = 0;
        boolean continuar = true;

        while (continuar) {
            ABooking reservaActual = listBooking.get(index);

            // Mostrar detalles
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("🔢 Reserva ").append(index + 1).append(" de ").append(listBooking.size()).append("\n\n");
            reservaActual.seeBooking(); // Ya tienes este método

            // Opciones
            String[] opciones = {
                "⏭️ Siguiente",
                "⏮️ Anterior",
                "✏️ Completar",
                "❌ Cancelar",
                "🚪 Salir"
            };

            int eleccion = JOptionPane.showOptionDialog(null,
                "¿Qué deseas hacer con esta reserva?",
                "Opciones de Reserva",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

            switch (eleccion) {
                case 0: // Siguiente
                    index = (index + 1) % listBooking.size();
                    break;
                case 1: // Anterior
                    index = (index - 1 + listBooking.size()) % listBooking.size();
                    break;
                case 2: // Modificar
                    reservaActual.completedBooking(); // Debes implementar este método
                    break;
                case 3: // Cancelar
                    int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas cancelar esta reserva?",
                        "Confirmar cancelación",
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        reservaActual.cancelBooking(); // Debes implementar este método
                        listBooking.remove(index);
                        if (listBooking.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "✅ Todas las reservas han sido canceladas.", "Sin listBooking", JOptionPane.INFORMATION_MESSAGE);
                            continuar = false;
                        } else {
                            index = index % listBooking.size(); // Ajustar índice
                        }
                    }
                    break;
                case 4: // Salir
                default:
                    continuar = false;
                    break;
            }
        }
    }

    public void showTournament() {
        if (listTournament == null || listTournament.isEmpty()) {
            JOptionPane.showMessageDialog(null, "📭 No hay torneos disponibles.", "Sin torneos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int index = 0;
        boolean continuar = true;

        while (continuar) {
            ATournament torneoActual = listTournament.get(index);

            // Mostrar detalles
            StringBuilder mensaje = new StringBuilder();
            mensaje.append("🎯 Torneo ").append(index + 1).append(" de ").append(listTournament.size()).append("\n\n");
            mensaje.append("📆 Inicio: ").append(torneoActual.getStartDate()).append("\n");
            mensaje.append("🏁 Fin: ").append(torneoActual.getEndDate()).append("\n");
            mensaje.append("📜 Reglas: ").append(torneoActual.getRules() != null ? torneoActual.getRules().getSummary() : "No definidas").append("\n");
            mensaje.append("🧩 Bracket: ").append(torneoActual.getBracket() != null ? torneoActual.getBracket().getStructureName() : "No definido").append("\n");
            mensaje.append("🔓 Registro: ").append(torneoActual.registrationOpen ? "Abierto" : "Cerrado").append("\n");
            mensaje.append("\n👥 Equipos:\n");
            for (Team team : torneoActual.getTeams()) {
                mensaje.append("- ").append(team.getName()).append("\n");
            }

            // Opciones
            String[] opciones = {
                "⏭️ Siguiente",
                "⏮️ Anterior",
                "🧩 Bracket",
                "🔁 Registro",
                "🗑️ Eliminar",
                "🚪 Salir"
            };

            int eleccion = JOptionPane.showOptionDialog(null,
                mensaje.toString(),
                "Carrusel de Torneos",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                opciones,
                opciones[0]);

            switch (eleccion) {
                case 0: // Siguiente
                    index = (index + 1) % listTournament.size();
                    break;
                case 1: // Anterior
                    index = (index - 1 + listTournament.size()) % listTournament.size();
                    break;
                case 2: // Bracket
                    torneoActual.manageBracket();
                    break;
                case 3: // Registro
                    if (torneoActual.registrationOpen) {
                        torneoActual.closeRegistration();
                        JOptionPane.showMessageDialog(null, "🚪 Registro cerrado.");
                    } else {
                        torneoActual.openRegistration();
                        JOptionPane.showMessageDialog(null, "🚪 Registro abierto.");
                    }
                    break;
                case 4: // Eliminar
                    int confirm = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas eliminar este torneo?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        listTournament.remove(index);
                        if (listTournament.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "✅ Todos los torneos han sido eliminados.", "Sin torneos", JOptionPane.INFORMATION_MESSAGE);
                            continuar = false;
                        } else {
                            index = index % listTournament.size(); // Ajustar índice
                        }
                    }
                    break;
                case 5: // Salir
                default:
                    continuar = false;
                    break;
            }
        }
    }

    public FactorySport getFactoryForSport(Sport sport) {
        String name = sport.getName().toLowerCase();
        FactorySport factory;

        switch (name) {
            case "tennis":
                factory = new FactoryTennis();
                break;
            case "basket":
                factory = new FactoryBasket();
                break;
            case "soccer":
                factory = new FactorySoccer();
                break;
            default:
                factory = null;
                break;
        }

        return factory;
    }

    private void manageSport(Sport sport) {
        while (true) {
            // 🧾 Mostrar información actual
            StringBuilder info = new StringBuilder();
            info.append("📌 Información del deporte:\n");
            info.append("Nombre: ").append(sport.getName()).append("\n");
            info.append("Espacios: ").append(sport.getNumberSpaces()).append("\n");
            info.append("Costo: $").append(sport.getCost()).append("\n");
            info.append("Formato: ").append(sport.getGameFormat()).append("\n");
            info.append("Equipamiento:\n");

            if (sport.getEquipment().isEmpty()) {
                info.append("  - Sin equipamiento registrado\n");
            } else {
                for (Equipment eq : sport.getEquipment()) {
                    info.append("  - ").append(eq.getName()).append("\n");
                }
            }

            // 🧭 Menú de edición
            String[] options = {
                "Editar espacios",
                "Editar costo",
                "Agregar equipamiento",
                "Eliminar equipamiento",
                "Salir"
            };

            String choice = (String) JOptionPane.showInputDialog(
                null,
                info.toString() + "\n¿Qué deseas hacer?",
                "Editar deporte",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
            );

            if (choice == null || choice.equals("Salir")) break;

            try {
                switch (choice) {
                    case "Editar espacios": {
                        int newSpaces = Integer.parseInt(JOptionPane.showInputDialog("Nuevo número de espacios:", sport.getNumberSpaces()));
                        sport.setNumberSpaces(newSpaces);
                        JOptionPane.showMessageDialog(null, "Espacios actualizados.");
                        break;
                    }
                    case "Editar costo": {
                        double newCost = Double.parseDouble(JOptionPane.showInputDialog("Nuevo costo:", sport.getCost()));
                        sport.setCost(newCost);
                        JOptionPane.showMessageDialog(null, "Costo actualizado.");
                        break;
                    }
                    case "Agregar equipamiento":{
                        String newEquip = JOptionPane.showInputDialog("Nombre del nuevo equipamiento:");
                        if (newEquip != null && !newEquip.trim().isEmpty()) {
                            sport.addEquipment(new Equipment(newEquip.trim()));
                            JOptionPane.showMessageDialog(null, "Equipamiento agregado.");
                        }
                        break;
                    }
                    case "Eliminar equipamiento":{
                        List<Equipment> equipmentList = sport.getEquipment();
                        if (equipmentList.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No hay equipamiento para eliminar.");
                            break;
                        }
                        String[] equipNames = equipmentList.stream().map(Equipment::getName).toArray(String[]::new);
                        String toRemove = (String) JOptionPane.showInputDialog(
                            null,
                            "Selecciona el equipamiento a eliminar:",
                            "Eliminar equipamiento",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            equipNames,
                            equipNames[0]
                        );
                        if (toRemove != null) {
                            Equipment eqToRemove = equipmentList.stream()
                                .filter(e -> e.getName().equals(toRemove))
                                .findFirst()
                                .orElse(null);
                            if (eqToRemove != null) {
                                sport.removeEquipment(eqToRemove);
                                JOptionPane.showMessageDialog(null, "Equipamiento eliminado.");
                            }
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    public void removeSport(Sport selectedSport){
        int confirm = JOptionPane.showConfirmDialog(null, "¿Eliminar " + selectedSport.getName() + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            ListSports.getInstance().removeSport(selectedSport);
            JOptionPane.showMessageDialog(null, "Deporte eliminado.");
        }     
    }

    public void addTournament() {
        if (factorySport == null) return;
        ATournament tournament = factorySport.createTournament();
        listTournament.add(tournament);
    }

    public void addBooking() {
        if (factorySport == null) return;
        ABooking booking = factorySport.createBooking();
        listBooking.add(booking);
    }

    public String getName() { return name; }
    public String getAddress() { return address; }
}
