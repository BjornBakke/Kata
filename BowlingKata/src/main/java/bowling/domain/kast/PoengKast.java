package bowling.domain.kast;

import bowling.domain.BowlingBetegnelse;

public class PoengKast implements Kast {
    private final int poeng;
    protected BowlingBetegnelse bowlingBetegnelse;

    public PoengKast(int antallKjegler) {
        this.poeng = antallKjegler;
        this.bowlingBetegnelse = BowlingBetegnelse.get(String.valueOf(antallKjegler));
    }

    public int getPoeng() {
        return poeng;
    }

    public BowlingBetegnelse getType() {
        return bowlingBetegnelse;
    }
}
