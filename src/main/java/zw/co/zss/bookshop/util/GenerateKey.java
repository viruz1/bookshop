package zw.co.zss.bookshop.util;

import java.util.Random;

public class GenerateKey {
    private static final char[] symbols = new char[36];

    static {
        for (int idx = 0; idx < 10; ++idx)
            symbols[idx] = (char) ('0' + idx);
        for (int idx = 10; idx < 36; ++idx)
            symbols[idx] = (char) ('a' + idx - 10);
    }

    private static final Random random = new Random();

    private static String nextString(int length){
        final char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }


    public static String generateReference() {
        Long l = System.currentTimeMillis();
        Integer randomNumber = (int)(Math.random() * 1000);
        String entityId = nextString(4)+l.toString() + randomNumber;

        return entityId.toUpperCase();
    }
}
