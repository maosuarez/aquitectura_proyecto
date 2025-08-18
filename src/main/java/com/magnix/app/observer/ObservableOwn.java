package com.magnix.app.observer;

import java.util.List;
import java.util.ArrayList;

public abstract class ObservableOwn {

    private State state;
    private List<Observer> observers = new ArrayList<>();

    //Getter
    public State getState() {
        return state;
    }

    //Setter
    public void setState(State state) {
        this.state = state;
        notifyObserver(getEstadoMensaje()); // Cada vez que cambie se notifica
    }

    public String getEstadoMensaje() {
        switch (state) {
            case PENDING:
                return "⏳ La reserva está pendiente de confirmación.";
            case CONFIRMED:
                return "✅ La reserva ha sido confirmada.";
            case CANCELLED:
                return "❌ La reserva fue cancelada.";
            case COMPLETED:
                return "🏁 La reserva ha sido completada exitosamente.";
            default:
                return "❓ Estado desconocido.";
        }
    }

    public void attach(Observer o) {
        observers.add(o);
    }

    public void detach(Observer o) {
        observers.remove(o);
    }

    public void notifyObserver(String mensaje) {
        for(Observer o: observers) {
            o.update(mensaje);
        }
    }
    
}
