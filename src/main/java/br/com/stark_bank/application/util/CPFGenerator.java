package br.com.stark_bank.application.util;

import java.util.concurrent.ThreadLocalRandom;

public final class CPFGenerator {

    private static final String[] CPFS = {
            "157.883.890-89", "015.006.860-34", "816.733.760-02", "969.254.740-08", "353.384.980-57", "447.474.600-71", "662.148.080-63", "201.968.990-10"
    };


    public static String randomCPF() {
        return CPFS[ThreadLocalRandom.current().nextInt(CPFS.length)];
    }

}
