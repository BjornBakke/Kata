package bowling.domain;

import java.util.HashMap;
import java.util.Map;

public enum BowlingBetegnelse {
    BOM("-"), SPARE("/"), STRIKE("X"),
    EN("1"),
    TO("2"),
    TRE("3"),
    FIRE("4"),
    FEM("5"),
    SEKS("6"),
    SYV("7"),
    ÅTTE("8"),
    NI("9");

    private static final Map<String, BowlingBetegnelse> lookup = new HashMap<>();

    static {
        for (BowlingBetegnelse betegnelse : BowlingBetegnelse.values()) {
            lookup.put(betegnelse.getVisningssymbol(), betegnelse);
        }
    }

    private final String visningssymbol;

    BowlingBetegnelse(String s) {
        visningssymbol = s;
    }

    public static BowlingBetegnelse get(String symbol) {
        if ("0".equals(symbol)) {
            return BOM;
        }
        if ("10".equals(symbol)) {
            return STRIKE;
        }
        return lookup.get(symbol);
    }

    public String getVisningssymbol() {
        return visningssymbol;
    }
}
