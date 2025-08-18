package com.magnix.app.factory;

import com.magnix.app.factory.abstractProducts.ABooking;
import com.magnix.app.factory.abstractProducts.ATournament;


public abstract class FactorySport {
    public abstract ATournament createTournament();
    public abstract ABooking createBooking();
}
