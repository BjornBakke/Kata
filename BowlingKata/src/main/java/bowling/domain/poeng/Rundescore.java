package bowling.domain.poeng;

import bowling.domain.Runde;
import bowling.domain.kast.Kast;

import java.util.List;

public class Rundescore implements Poeng {

    public int beregn(List<Runde> utførtekast) {
        return utførtekast.stream()
                .map(runde -> runde.getRundensKast()
                        .stream()
                        .map(Kast::getPoeng)
                        .reduce(0, Integer::sum))
                .reduce(0, Integer::sum);
    }
}
