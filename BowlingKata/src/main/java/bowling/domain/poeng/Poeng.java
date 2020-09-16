package bowling.domain.poeng;

import bowling.domain.Runde;

import java.util.List;

public interface Poeng {
    int beregn(List<Runde> utførtekast);
}
