package com.haizhi.mock.data;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.stream.IntStream;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Slf4j
public class MockDataApplicationTests {

    private static final int LOOP_TIME = 100000;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testString() {
        long start = System.currentTimeMillis();

        IntStream.rangeClosed(1, LOOP_TIME)
                .forEach(i -> {
                    String s = "123" + i;
                });

        log.info("testString 耗时 {} ms", System.currentTimeMillis() - start);
    }

    @Test
    public void testStringFormat() {
        long start = System.currentTimeMillis();

        IntStream.rangeClosed(1, LOOP_TIME)
                .forEach(i -> {
                    String s = String.format("123%s", i);
                });

        log.info("testStringFormat 耗时 {} ms", System.currentTimeMillis() - start);
    }
}
