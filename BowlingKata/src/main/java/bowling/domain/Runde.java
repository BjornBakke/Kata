package bowling.domain;

import bowling.domain.kast.Kast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static bowling.domain.BowlingBetegnelse.SPARE;
import static bowling.domain.BowlingBetegnelse.STRIKE;

// En runde består av inntil 2 kast.
public class Runde {
    private static final int MAKS_ANTALL_FORSØK = 2;
    private List<Kast> rundensPoengKast = new ArrayList<>();

    public Runde(Kast strike) {
        rundensPoengKast.add(strike);
    }

    public Runde(Kast førsteKast, Kast sisteKast) {
        validerAntallKjegler(førsteKast, sisteKast);
        rundensPoengKast.add(førsteKast);
        korrigerForSpare(førsteKast, sisteKast);
        rundensPoengKast.add(sisteKast);
    }

    private void validerAntallKjegler(Kast førsteKast, Kast sisteKast) {
        if (førsteKast.getPoeng() + sisteKast.getPoeng() > 10) {
            throw new IllegalArgumentException("maksimalt 10 kjegler å velte  pr runde");
        }
    }

    public boolean isStike() {
        return rundensPoengKast
                .stream()
                .anyMatch(kast -> STRIKE.equals(kast.getType()));
    }

    public boolean isSpare() {
        return !isStike() && rundensPoengKast
                .stream()
                .map(Kast::getPoeng)
                .reduce(0, Integer::sum) == 10;
    }

    // benyttes feks ved "Spare" bonuskast poengberegning
    public int getPoengFørsteRundeKast() {
        return rundensPoengKast.get(0).getPoeng(); // jepp her smeller det sikkert

    }

    // iterer over "de kastene vi har gjort" til nå
    public int getTilgjengeligeRundePoeng() {
        return rundensPoengKast.stream()
                .map(Kast::getPoeng)
                .reduce(0, Integer::sum);
    }

    public List<BowlingBetegnelse> getVisningType() {
        return rundensPoengKast
                .stream()
                .map(Kast::getType)
                .collect(Collectors.toList());
    }

    public boolean isUfullstendig() {
        return !isSpare() && !isStike() && antallUtførteKast() < MAKS_ANTALL_FORSØK;
    }

    public List<Kast> getRundensKast() {
        return rundensPoengKast;
    }

    // input(4, /) == (4,6)
    private void korrigerForSpare(Kast førsteKast, Kast sisteKast) {
        if (SPARE.equals(sisteKast.getType())) {
            sisteKast.setPoeng(10 - førsteKast.getPoeng());
        }
    }

    private int antallUtførteKast() {
        return rundensPoengKast.size();
    }
}
