package bowling.domain.kast;

import bowling.domain.BowlingBetegnelse;

import static bowling.domain.BowlingBetegnelse.BOM;

public class Bom implements Kast {
    public Bom() {

    }

    @Override
    public int getPoeng() {
        return 0;
    }

    @Override
    public BowlingBetegnelse getType() {
        return BOM;
    }
}
