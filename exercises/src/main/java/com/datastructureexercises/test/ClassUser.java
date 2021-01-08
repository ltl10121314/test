package com.datastructureexercises.test;

import java.util.ArrayList;
import java.util.List;

public class ClassUser {
    public static void main(String[] args) {
        Address address1 = new Address("山西", "太原");
        Address address2 = new Address("陕西", "西安");
        Address address3 = new Address("广东", "广州");
        Address address4 = new Address("湖北", "武汉");
        List<User> list = new ArrayList<>();
        User user1 = new User("1", "张三", address1);
        User user2 = new User("2", "李四", address2);
        list.add(user1);
        list.add(user2);

        List<User> list2 = new ArrayList<>();
        User user3 = new User("3", "王五", address3);
        User user4 = new User("4", "张麻子", address4);
        list.add(user3);
        list.add(user4);
        System.out.println(list.toString());
        for (User user : list) {
            user.setId("hahaha");
        }
        System.out.println(list.toString());

//        System.out.println(list2.toString());
//        for (int i = 0; i < list.size(); i++) {
//            if (1 == i) {
//                User user = list.get(1);
//                System.out.println(user);
//                Address address = user.getAddress();
//                address.setId("北京");
//                address.setName("北京");
//            }
//
//        }
//        list.addAll(list2);
//        System.out.println(list);
    }
}
