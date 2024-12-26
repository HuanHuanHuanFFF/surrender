package com.hf.utlis;

import java.util.Random;

public class RandomID {
    static char[] chars = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
    };

    /**
     * 生成10位ID,格式为XXXXXX-XXXX,其中后四位为字母或数字的概率相同
     */
    public static String getRandomID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(chars[random.nextInt(36)]);
        }
        sb.append("-");
        for (int i = 0; i < 4; i++) {
            if (random.nextBoolean()) sb.append(chars[random.nextInt(26)]);
            else sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
