package com.datastructureexercises.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author liutianlong
 */
public class TestGet {

    public static void main(String[] args) {
        int count = 10;
        for (int i = 0; i < count; i++) {
            System.out.println(get("https://www.baidu.com/"));
        }
    }

    public static String get(String url) {
        try {
            // 把字符串转换为URL请求地址
            URL realUrl = new URL(url);
            // 打开连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            // 循环读取流
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}


