package com.magnix.app.factory.concreteFactories;

import com.magnix.app.factory.FactorySport;
import com.magnix.app.factory.abstractProducts.*;
import com.magnix.app.factory.concreteProducts.TournamentSoccer;
import com.magnix.app.factory.concreteProducts.BookingSoccer;

public class FactorySoccer extends FactorySport{
    @Override
    public ATournament createTournament() {
        return new TournamentSoccer();
    }

    @Override
    public ABooking createBooking() {
        return new BookingSoccer();
    }
}
