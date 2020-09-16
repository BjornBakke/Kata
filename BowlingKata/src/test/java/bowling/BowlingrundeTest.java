package bowling;

import bowling.domain.Bowlingrunde;
import bowling.domain.kast.Bom;
import bowling.domain.kast.PoengKast;
import bowling.domain.kast.Spare;
import bowling.domain.kast.Strike;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingrundeTest {

    @Test
    void poeng_førStart_ingenKjeglerNede() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();
        int poengsum = bowlingRunde.getPoeng();

        assertThat(poengsum, is(0));
    }

    @Test
    void poeng_førseRundeBom_0() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();
        bowlingRunde.add(new Bom());

        int poengsum = bowlingRunde.getPoeng();

        assertThat(poengsum, is(0));
    }

    @Test
    void poeng_halvferdigførsteRunde_5() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();
        bowlingRunde.add(new PoengKast(5));

        int poengsum = bowlingRunde.getPoeng();

        assertThat(poengsum, is(5));
    }

    @Test()
    void poeng_ikkeStrikeIkkeSpareOgEttKastIgjen_ufullstendigRunde() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();
        bowlingRunde.add(new PoengKast(5));

        assertThrows(RuntimeException.class, () -> bowlingRunde.add(new PoengKast(5)));
    }

    @Test
    void poeng_strike_10() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();
        bowlingRunde.add(new Strike());

        int poengsum = bowlingRunde.getPoeng();

        assertThat(poengsum, is(10));
    }

    @Test
    void getPoeng_alleNed_maksPoengsum() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();

        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());
        // bonuskast ved   tot  12 x 10 = 120 poeng
        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());

        bowlingRunde.skrivUtpoeng();

        int poengsum = bowlingRunde.getPoeng();
        assertThat(poengsum, is(300));
    }

    @Test
    void spare_rundbaut_() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();

        bowlingRunde.add(new PoengKast(5), new Spare());
        bowlingRunde.add(new PoengKast(5), new Spare());
        bowlingRunde.add(new PoengKast(5), new Spare());
        bowlingRunde.add(new PoengKast(5), new Spare());
        bowlingRunde.add(new PoengKast(5), new Spare());
        bowlingRunde.add(new PoengKast(5), new Spare());
        bowlingRunde.add(new PoengKast(5), new Spare());
        bowlingRunde.add(new PoengKast(5), new Spare());
        bowlingRunde.add(new PoengKast(5), new Spare());
        bowlingRunde.add(new PoengKast(5), new Spare());

        // bonuskast ved Spare
        bowlingRunde.add(new PoengKast(5));


        int poengsum = bowlingRunde.getPoeng();
        assertThat(poengsum, is(150));
    }

    @Test
    void tilfeldig_runde_korrektPoengsum() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();

        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(0), new Spare());
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new Bom());
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));


        int poengsum = bowlingRunde.getPoeng();
        assertThat(poengsum, is(100));
    }

    @Test
    void tilfeldig_runde2_korrektPoengsum() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();

        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));
        bowlingRunde.add(new PoengKast(0), new Spare());
        bowlingRunde.add(new Bom(), new PoengKast(0));
        bowlingRunde.add(new PoengKast(9), new Bom());
        bowlingRunde.add(new PoengKast(9), new PoengKast(0));

        bowlingRunde.skrivUtpoeng();

        int poengsum = bowlingRunde.getPoeng();
        assertThat(poengsum, is(82));
    }

    @Test
    void midlertidig_strikePoengberegning() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();

        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());
        bowlingRunde.add(new Strike());


        int poengsum = bowlingRunde.getPoeng();
        assertThat(poengsum, is(60));
    }

    @Test
    void spare_minusEnSisteKast() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();

        bowlingRunde.add(new PoengKast(5), new PoengKast(5)); //spare
        bowlingRunde.add(new PoengKast(5), new Spare()); // spare
        bowlingRunde.add(new PoengKast(5), new PoengKast(5));
        bowlingRunde.add(new PoengKast(5), new PoengKast(5));
        bowlingRunde.add(new PoengKast(5), new PoengKast(5));
        bowlingRunde.add(new PoengKast(5), new Spare());
        bowlingRunde.add(new PoengKast(5), new PoengKast(5));
        bowlingRunde.add(new PoengKast(5), new PoengKast(5));
        bowlingRunde.add(new PoengKast(5), new PoengKast(5));
        bowlingRunde.add(new PoengKast(5), new PoengKast(4));
        // feks: validering på at ikke flere kast gjøres

        int poengsum = bowlingRunde.getPoeng();
        assertThat(poengsum, is(144));
        bowlingRunde.skrivUtpoeng();
    }

    @Test
    void midlertidigPoengBeregning() {
        Bowlingrunde bowlingRunde = new Bowlingrunde();

        bowlingRunde.add(new Strike());
        bowlingRunde.add(new PoengKast(5), new PoengKast(5));
        bowlingRunde.add(new PoengKast(5), new PoengKast(4));

        int poengsum = bowlingRunde.getPoeng();
        assertThat(poengsum, is(53));
    }

    @Test
    void to_spillere_enVinner() {
        Bowlingrunde spillerA = new Bowlingrunde();
        Bowlingrunde spillerB = new Bowlingrunde();

        spillerA.add(new PoengKast(5), new Spare());
        spillerA.add(new PoengKast(5), new Spare());
        spillerA.add(new PoengKast(5), new Spare());
        spillerA.add(new PoengKast(5), new Spare());
        spillerA.add(new PoengKast(5), new Spare());
        spillerA.add(new PoengKast(5), new Spare());
        spillerA.add(new PoengKast(5), new Spare());
        spillerA.add(new PoengKast(5), new Spare());
        spillerA.add(new PoengKast(5), new Spare());
        spillerA.add(new PoengKast(5), new Spare());

        // bonuskast ved Spare
        spillerA.add(new PoengKast(5));

        spillerB.add(new PoengKast(5), new Spare());
        spillerB.add(new PoengKast(5), new Spare());
        spillerB.add(new PoengKast(5), new Spare());
        spillerB.add(new PoengKast(5), new Spare());
        spillerB.add(new PoengKast(5), new Spare());
        spillerB.add(new PoengKast(5), new Spare());
        spillerB.add(new PoengKast(5), new Spare());
        spillerB.add(new PoengKast(5), new Spare());
        spillerB.add(new PoengKast(5), new Spare());
        spillerB.add(new PoengKast(5), new Spare());

        // bonuskast ved Spare
        spillerB.add(new PoengKast(4));


        int poengsum = spillerA.getPoeng();
        int poengsumB = spillerB.getPoeng();
        assertThat(poengsum, is(greaterThan(poengsumB)));
    }
}