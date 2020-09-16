package bowling.domain.kast;

import bowling.domain.BowlingBetegnelse;

public class Spare implements Kast {
    private int resterendeKjegler;

    public Spare() {
    }

    @Override
    public int getPoeng() {
        return resterendeKjegler;
    }

    public void setPoeng(int rest) {
        resterendeKjegler = rest;
    }

    @Override
    public BowlingBetegnelse getType() {
        return BowlingBetegnelse.SPARE;
    }
}
