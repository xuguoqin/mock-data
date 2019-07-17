package com.haizhi.mock.data.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author xuguoqin
 * @date 2019/7/17 10:05 AM
 */
public class RandomUtils {

    /**
     * 生成0到指定bound的随机数
     *
     * @param bound
     * @return
     */
    public static int nextIntClosed(int bound) {
        return ThreadLocalRandom.current().nextInt(bound + 1);
    }

    /**
     * 生成origin到bound之间的随机数
     *
     * @param origin
     * @param bound
     * @return
     */
    public static int nextIntClosed(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound + 1);
    }
}
