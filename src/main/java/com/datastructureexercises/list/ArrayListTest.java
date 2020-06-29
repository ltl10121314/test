package com.datastructureexercises.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ArrayListTest {
    @Test
    public void arrayListTest() {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("hahah");
        System.out.println(strings.get(0).getClass().getName());
    }

    @Test
    public void mapTest() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("apple", 12);
        map.put("pear", 13);
        map.put("banana", 15);
        Collection<Integer> values = map.values();
        System.out.println(values.toString());
        for (String key : map.keySet()) {
            Integer integer = map.get(key);
            System.out.println(integer);
        }
    }

    @Test
    public void map2Test() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("apple", 12);
        map.put("pear", 13);
        map.put("banana", 15);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "=" + value);
        }
    }
}
