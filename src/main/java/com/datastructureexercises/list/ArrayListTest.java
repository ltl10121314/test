package com.datastructureexercises.list;

import org.junit.Test;

import java.util.ArrayList;

public class ArrayListTest {
    @Test
    public void arrayListTest(){
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("hahah");
        System.out.println(strings.get(0).getClass().getName());

    }
}
