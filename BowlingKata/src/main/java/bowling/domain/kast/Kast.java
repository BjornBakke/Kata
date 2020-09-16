package bowling.domain.kast;

import bowling.domain.BowlingBetegnelse;

public interface Kast {
    int getPoeng();

    default void setPoeng(int rest) {

    }

    BowlingBetegnelse getType();
}
