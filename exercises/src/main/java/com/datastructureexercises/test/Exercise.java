package com.datastructureexercises.test;

import org.junit.Test;

import java.util.UUID;

public class Exercise {
    @Test
    public void heTest()  {
//        System.out.println(Integer.parseInt("".toString()));
        System.out.println(String.valueOf(UUID.randomUUID()).replaceAll("-", "").toUpperCase());
//        try {
//            System.out.println("hello");
//        } catch (Exception E) {
//            throw new Exception();
//        }
//        throw new Exception();
    }
}
