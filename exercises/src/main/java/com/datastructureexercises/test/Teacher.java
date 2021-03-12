package com.datastructureexercises.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Liu Tianlong
 * @create: 2021-01-08 18:42
 **/
public class Teacher {

    public String getTxdate(String dates, String jys, String ofi) throws ParseException {
        ArrayList hlist = new ArrayList();
        hlist.add("shjs");
        hlist.add("gjs");
        int hlisindex = hlist.indexOf(jys);
        String[] timesa = {"20200109:1,1", "20200110:1,0", "20200111:0,1",
                "20200112:1,1", "20200113:1,1"};
        String dateout = "99999999";
        int ii = 0;
        int find = 0;
        for (String str : timesa) {
            String[] parts = str.split(":");
            String ifjy = parts[1].split(",")[hlisindex];
            if (parts[0].equals(dates)) {
                find = 1;
            }

            if (find == 1 && ifjy.equals("1")) {
                if (Integer.toString(ii).equals(ofi)) {
                    dateout = parts[0];
                    break;
                }
                ii += 1;
            }
        }
        return dateout;
    }

    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<Object> objects1 = new ArrayList<>();
        objects.add("a");
        objects.add("b");
        objects.add("c");
        objects1.add("c");
        objects1.add("d");
        objects1.add("e");
        objects.removeAll(objects1);
        System.out.println(objects);
        System.out.println("===========================");
        String[] strings = new String[]{"a","c","b"};
        String[] strings2 = new String[]{"c","d","e"};
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        List<String> list2 = new ArrayList<>(Arrays.asList(strings2));
        list2.removeAll(list);
        System.out.println(list2);

    }

    public static String hehehe() {
        try {
            System.out.println("sasas");
            return "xixi";
        } catch (Exception e) {
        } finally {
            System.out.println("hahahah");
        }
        return "hehe";
    }

}
