package com.magnix.app.factory.concreteFactories;

import com.magnix.app.factory.FactorySport;
import com.magnix.app.factory.abstractProducts.*;
import com.magnix.app.factory.concreteProducts.TournamentBasket;
import com.magnix.app.factory.concreteProducts.BookingBasket;


public class FactoryBasket extends FactorySport {
    @Override
    public ATournament createTournament() {
        return new TournamentBasket();
    }

    @Override
    public ABooking createBooking() {
        return new BookingBasket();
    }
}
