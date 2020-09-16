package bowling.domain.poeng;

import bowling.domain.Runde;

import java.util.List;

import static java.lang.Math.min;

public class Bonuspoeng implements Poeng {
    private static final int MAKS_RUNDER = 9; // uten bonuskast: 0 basert


    public int beregn(List<Runde> utførtekast) {
        int bonuspoeng = 0;
        Runde[] runderAsArray = utførtekast.toArray(new Runde[0]);
        int i = 0;
        while (i < min(runderAsArray.length, MAKS_RUNDER)) {
            int gjeldendeRunde = i;
            int førsteRundeEtterGjeldendeIndex = gjeldendeRunde + 1;
            int nesteRundeEtterFørsteIndex = førsteRundeEtterGjeldendeIndex + 1;

            if (runderAsArray[gjeldendeRunde].isStike()) {
                bonuspoeng += getStrikePoeng(runderAsArray, førsteRundeEtterGjeldendeIndex);
                bonuspoeng += getStrikePoeng(runderAsArray, nesteRundeEtterFørsteIndex);
            }

            if (runderAsArray[gjeldendeRunde].isSpare()) {
                bonuspoeng += runderAsArray[førsteRundeEtterGjeldendeIndex].getPoengFørsteRundeKast();
            }
            i++;
        }

        return bonuspoeng;
    }

    /**
     * la det smelle hvis vi er mitt i et spill og ikke i stand til å kalkulere bonus
     */
    private int getStrikePoeng(Runde[] runder, int index) {
        try {
            return runder[index].getTilgjengeligeRundePoeng();
        } catch (Exception e) {
            return 0;
        }
    }
}
