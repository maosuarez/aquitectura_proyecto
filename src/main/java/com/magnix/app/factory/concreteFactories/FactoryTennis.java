package com.magnix.app.factory.concreteFactories;

import com.magnix.app.factory.FactorySport;
import com.magnix.app.factory.abstractProducts.*;
import com.magnix.app.factory.concreteProducts.TournamentTennis;
import com.magnix.app.factory.concreteProducts.BookingTennis;

public class FactoryTennis extends FactorySport{
    @Override
    public ATournament createTournament() {
        return new TournamentTennis();
    }

    @Override
    public ABooking createBooking() {
        return new BookingTennis();
    }
}
