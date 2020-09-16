package bowling.domain;

import bowling.domain.poeng.Bonuspoeng;
import bowling.domain.poeng.Poeng;
import bowling.domain.poeng.Rundescore;

import java.util.ArrayList;
import java.util.List;

public class MineBowlingkast {
    private Poeng poeng = new Rundescore();
    private Poeng bonuspoeng = new Bonuspoeng();
    private List<Runde> rundeListe = new ArrayList<>(12); // maks 12 kast - skjer  nå alle kast er strike

    public List<Runde> getRundeListe() {
        verifiserRunder();
        return rundeListe;
    }

    public int getRundepoeng() {
        return poeng.beregn(rundeListe);
    }

    public int getBonuspoeng() {
        return bonuspoeng.beregn(rundeListe);
    }

    // sørg for at vi er ferdig med førsteKast runde før vi begynner på neste
    public void verifiserRunder() {
        rundeListe
                .stream()
                .filter(Runde::isUfullstendig)
                .forEach(runde -> {
                    throw new RuntimeException("mugg");
                });
    }

    public void printRunde() {
        rundeListe.stream()
                .map(Runde::getVisningType)
                .forEach(betegnelser -> {
                    betegnelser
                            .stream()
                            .map(BowlingBetegnelse::getVisningssymbol)
                            .forEach(System.out::print);
                    System.out.println();
                });
    }
}
