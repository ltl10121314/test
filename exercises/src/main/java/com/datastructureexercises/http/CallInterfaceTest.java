package com.datastructureexercises.http;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * get和post方法调用
 *
 * @author liutianlong
 */
public class CallInterfaceTest {
    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String doGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    @Test
    public void doGet() throws Exception {
        String address = "http://172.16.26.54:8700/rest/dig-buw-monitor/workflows/configuration/getStatusConfiguration";
        String param = "";
        int count = 1000;
        for (int i = 0; i < count; i++) {
            String result = doGet(address, param);
            System.out.println("第" + (i + 1) + "次调用:" + result);
        }
    }

    /**
     * post请求
     *
     * @param json 前端请求
     * @return 返回值
     * @throws Exception 返回异常
     */
    public static String doPost(String address, String json) throws Exception {
        //创建URL对象,设置请求url
        URL url = new URL(address);
        //调用URL对象的openConnection( )来获取HttpURLConnection对象实例
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //请求方法为POST
        conn.setRequestMethod("POST");
        //设置连接超时为5秒
        conn.setConnectTimeout(5000);
        //允许输入输出
        conn.setDoInput(true);
        conn.setDoOutput(true);
        //不能缓存
        conn.setUseCaches(false);
        //至少要设置的两个请求头
        //设置头部信息
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
        //一定要设置 Content-Type 要不然服务端接收不到参数
        conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
        //输出流包含要发送的数据,要注意数据格式编码
        OutputStream op = conn.getOutputStream();
        op.write(json.getBytes());
        //服务器返回东西了，先对响应码判断
        String result = "";
        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = "";
            while (null != (line = br.readLine())) {
                result += line;
            }
            br.close();
            conn.disconnect();
            return result;
        }
        return result;
    }

    @Test
    public void doPostTest() throws Exception {
        //请求地址
        String address = "http://172.16.26.54:8700/rest/dig-buw-manager/taskServiceForRest/getTaskDefineForExecutor";
        //将对象转换为json，然后发送；
        String json = "{\n" +
                "  \"objectInfo\": {\n" +
                "    \"nodeKey\": \"4bbb27d384844d8cb83de1307dcc33d6\",\n" +
                "    \"taskType\": \"m3221\",\n" +
                "    \"tenantId\": \"tenant_system\"\n" +
                "  },\n" +
                "  \"token\":1\n" +
                "}";
        String result = "";
        int count = 1000;
        for (int i = 0; i < count; i++) {
            result = doPost(address, json);
            System.out.println("第" + (i + 1) + "次调用：" + result);
        }
    }


}
