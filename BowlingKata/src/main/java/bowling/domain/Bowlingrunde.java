package bowling.domain;

import bowling.domain.kast.Kast;

public class Bowlingrunde {
    private MineBowlingkast utførtekast = new MineBowlingkast();

    public void add(Kast kast) {
        utførtekast.getRundeListe().add(new Runde(kast));
    }

    public void add(Kast førsteKast, Kast sisteKast) {
        utførtekast.getRundeListe().add(new Runde(førsteKast, sisteKast));
    }

    public int getPoeng() {
        return utførtekast.getRundepoeng() + utførtekast.getBonuspoeng();
    }

    public void skrivUtpoeng() {
        utførtekast.printRunde();
    }
}

