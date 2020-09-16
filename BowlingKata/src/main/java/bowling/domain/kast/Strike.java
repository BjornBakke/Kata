package bowling.domain.kast;

import bowling.domain.BowlingBetegnelse;

public class Strike implements Kast {
    public Strike() {

    }

    @Override
    public int getPoeng() {
        return 10;
    }

    @Override
    public BowlingBetegnelse getType() {
        return BowlingBetegnelse.STRIKE;
    }
}
