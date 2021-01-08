package com.datastructureexercises.test;

import org.apache.commons.net.util.Base64;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

public class Exercise {
    @Test
    public void heTest() throws UnsupportedEncodingException {
//        System.out.println(Integer.parseInt("".toString()));
//        System.out.println(String.valueOf(UUID.randomUUID()).replaceAll("-", "").toUpperCase());
//        try {
//            System.out.println("hello");
//        } catch (Exception E) {
//            throw new Exception();
//        }
//        throw new Exception();
//        String a = "Base64#NXJwcm94eQ==".substring(7);
//        System.out.println(a);
//        String s1 = new String(Base64.decodeBase64(a.getBytes()), StandardCharsets.UTF_8);
//        System.out.println(s1);

        String b1 = "Base64#MTQ5Mzg1MA==".substring(7);
        String s1 = new String(Base64.decodeBase64(b1.getBytes()), StandardCharsets.UTF_8);
        System.out.println(b1);
        System.out.println(s1);
        String s2 = new String(Base64.encodeBase64(s1.getBytes()), StandardCharsets.UTF_8);
        System.out.println(s2);
    }
    @Test
    public void hehetest(){
        String string = "E201040004:流程编码重复[流程映射节点测试01]!";
        int test = string.indexOf("流程编码重复");
        System.out.println(test);
        String idInfo = string.substring(string.indexOf("[")+1,string.indexOf("]"));
        System.out.println(idInfo);
    }
    @Test
    public void hhhtest(){
        String str = "asdfa(asdfa(sdafa(";
        String ss = str.replaceFirst("\\(", "我的");
        System.out.println(ss);
    }
}
