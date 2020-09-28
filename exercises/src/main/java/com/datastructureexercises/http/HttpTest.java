package com.datastructureexercises.http;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 */
public class HttpTest {
    public static final Logger log = LoggerFactory.getLogger(   HttpTest.class);

    @Test
    public void httpTest() {
        log.info("===========");
    }

    public static void main(String[] args) {
        for (int i=0;i<5;i++){
            log.info("这是一条数据"+i);
        }

    }
}
