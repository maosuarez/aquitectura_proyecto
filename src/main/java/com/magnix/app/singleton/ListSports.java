package com.magnix.app.singleton;
/**
 * Singleton de la lista de deportes.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListSports {

    // 🧩 Lista interna de deportes
    private final List<Sport> list;

    // 🔒 Instancia única del singleton
    private static ListSports instance;

    // 🚫 Constructor privado
    private ListSports() {
        list = new ArrayList<>();
    }

    // 🔓 Acceso público y estático a la instancia
    public static synchronized ListSports getInstance() {
        if (instance == null) {
            instance = new ListSports();
        }
        return instance;
    }

    // 📥 Retorno seguro de la lista
    public List<Sport> getSports() {
        return Collections.unmodifiableList(list);
    }

    // ➕ Agregar deporte
    public boolean addSport(Sport sport) {
        if (sport != null && !list.contains(sport)) {
            return list.add(sport);
        }
        return false;
    }

    // ➖ Eliminar deporte
    public boolean removeSport(Sport sport) {
        return list.remove(sport);
    }

    // 🔍 Buscar deporte por nombre
    public Sport findByName(String name) {
        for (Sport sport : list) {
            if (sport.getName().equalsIgnoreCase(name)) {
                return sport;
            }
        }
        return null;
    }

    // 🧹 Limpiar lista (opcional)
    public void clear() {
        list.clear();
    }

    // 📊 Contador
    public int size() {
        return list.size();
    }
}
