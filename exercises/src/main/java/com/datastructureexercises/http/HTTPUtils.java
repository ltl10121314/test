//package com.datastructureexercises.http;
//
//import org.junit.Test;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLSession;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * Http请求
// *
// * @author mszhou
// */
//public class HTTPUtils {
//    private static final int TIMEOUT = 45000;
//    public static final String ENCODING = "UTF-8";
//
//    /**
//     * 创建HTTP连接
//     *
//     * @param url              地址
//     * @param method           方法
//     * @param headerParameters 头信息
//     * @param body             请求内容
//     * @return
//     * @throws Exception
//     */
//    private static HttpURLConnection createConnection(String url, String method, Map<String, String> headerParameters, String body)
//            throws Exception {
//        URL Url = new URL(url);
//        trustAllHttpsCertificates();
//        HttpURLConnection httpConnection = (HttpURLConnection) Url.openConnection();
//        // 设置请求时间
//        httpConnection.setConnectTimeout(TIMEOUT);
//        // 设置 header
//        if (headerParameters != null) {
//            for (String key : headerParameters.keySet()) {
//                httpConnection.setRequestProperty(key, headerParameters.get(key));
//            }
//        }
//        httpConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + ENCODING);
//
//        // 设置请求方法
//        httpConnection.setRequestMethod(method);
//        httpConnection.setDoOutput(true);
//        httpConnection.setDoInput(true);
//        // 写query数据流
//        if (!(body == null || body.trim().equals(""))) {
//            OutputStream writer = httpConnection.getOutputStream();
//            try {
//                writer.write(body.getBytes(ENCODING));
//            } finally {
//                if (writer != null) {
//                    writer.flush();
//                    writer.close();
//                }
//            }
//        }
//        // 请求结果
//        int responseCode = httpConnection.getResponseCode();
//        if (responseCode != 200) {
//            throw new Exception(responseCode + ":" + inputStream2String(httpConnection.getErrorStream(), ENCODING));
//        }
//        return httpConnection;
//    }
//
//    /**
//     * POST请求
//     *
//     * @param address          请求地址
//     * @param headerParameters 参数
//     * @param body
//     * @return
//     * @throws Exception
//     */
//    public static String post(String address, Map<String, String> headerParameters, String body) throws Exception {
//        return proxyHttpRequest(address, "POST", null, getRequestBody(headerParameters));
//    }
//
//    /**
//     * GET请求
//     *
//     * @param address
//     * @param headerParameters
//     * @param body
//     * @return
//     * @throws Exception
//     */
//    public static String get(String address, Map<String, String> headerParameters, String body) throws Exception {
//        return proxyHttpRequest(address + "?" + getRequestBody(headerParameters), "GET", null, null);
//    }
//
//    /**
//     * 读取网络文件
//     *
//     * @param address
//     * @param headerParameters
//     * @param file
//     * @return
//     * @throws Exception
//     */
//    public static String getFile(String address, Map<String, String> headerParameters, File file) throws Exception {
//        String result = "fail";
//        HttpURLConnection httpConnection = null;
//        try {
//            httpConnection = createConnection(address, "POST", null, getRequestBody(headerParameters));
//            result = readInputStream(httpConnection.getInputStream(), file);
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (httpConnection != null) {
//                httpConnection.disconnect();
//            }
//        }
//        return result;
//    }
//
//    public static byte[] getFileByte(String address, Map<String, String> headerParameters) throws Exception {
//        byte[] result = null;
//        HttpURLConnection httpConnection = null;
//        try {
//            httpConnection = createConnection(address, "POST", null, getRequestBody(headerParameters));
//            result = readInputStreamToByte(httpConnection.getInputStream());
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (httpConnection != null) {
//                httpConnection.disconnect();
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 读取文件流
//     *
//     * @param in
//     * @return
//     * @throws Exception
//     */
//    public static String readInputStream(InputStream in, File file) throws Exception {
//        FileOutputStream out = null;
//        ByteArrayOutputStream output = null;
//        try {
//            output = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while ((len = in.read(buffer)) != -1) {
//                output.write(buffer, 0, len);
//            }
//            out = new FileOutputStream(file);
//            out.write(output.toByteArray());
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (output != null) {
//                output.close();
//            }
//            if (out != null) {
//                out.close();
//            }
//        }
//        return "success";
//    }
//
//    public static byte[] readInputStreamToByte(InputStream in) throws Exception {
//        FileOutputStream out = null;
//        ByteArrayOutputStream output = null;
//        byte[] byteFile = null;
//        try {
//            output = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while ((len = in.read(buffer)) != -1) {
//                output.write(buffer, 0, len);
//            }
//            byteFile = output.toByteArray();
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (output != null) {
//                output.close();
//            }
//            if (out != null) {
//                out.close();
//            }
//        }
//        return byteFile;
//    }
//
//    /**
//     * HTTP请求
//     *
//     * @param address          地址
//     * @param method           方法
//     * @param headerParameters 头信息
//     * @param body             请求内容
//     * @return
//     * @throws Exception
//     */
//    public static String proxyHttpRequest(String address, String method, Map<String, String> headerParameters, String body) throws Exception {
//        String result = null;
//        HttpURLConnection httpConnection = null;
//        try {
//            httpConnection = createConnection(address, method, headerParameters, body);
//            String encoding = "UTF-8";
//            if (httpConnection.getContentType() != null && httpConnection.getContentType().indexOf("charset=") >= 0) {
//                encoding = httpConnection.getContentType().substring(httpConnection.getContentType().indexOf("charset=") + 8);
//            }
//            result = inputStream2String(httpConnection.getInputStream(), encoding);
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (httpConnection != null) {
//                httpConnection.disconnect();
//            }
//        }
//        return result;
//    }
//
//    /**
//     * 将参数化为 body
//     *
//     * @param params
//     * @return
//     */
//    public static String getRequestBody(Map<String, String> params) {
//        return getRequestBody(params, true);
//    }
//
//    /**
//     * 将参数化为 body
//     *
//     * @param params
//     * @return
//     */
//    public static String getRequestBody(Map<String, String> params, boolean urlEncode) {
//        StringBuilder body = new StringBuilder();
//        Iterator<String> iteratorHeader = params.keySet().iterator();
//        while (iteratorHeader.hasNext()) {
//            String key = iteratorHeader.next();
//            String value = params.get(key);
//            if (urlEncode) {
//                try {
//                    body.append(key + "=" + URLEncoder.encode(value, ENCODING) + "&");
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            } else {
//                body.append(key + "=" + value + "&");
//            }
//        }
//
//        if (body.length() == 0) {
//            return "";
//        }
//        return body.substring(0, body.length() - 1);
//    }
//
//    /**
//     * 读取inputStream 到 string
//     *
//     * @param input
//     * @param encoding
//     * @return
//     * @throws IOException
//     */
//    private static String inputStream2String(InputStream input, String encoding) throws IOException {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(input, encoding));
//        StringBuilder result = new StringBuilder();
//        String temp = null;
//        while ((temp = reader.readLine()) != null) {
//            result.append(temp);
//        }
//        return result.toString();
//    }
//
//    /**
//     * 设置 https 请求
//     *
//     * @throws Exception
//     */
//    private static void trustAllHttpsCertificates() throws Exception {
//        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String str, SSLSession session) {
//                return true;
//            }
//        });
//        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
//        javax.net.ssl.TrustManager tm = new miTM();
//        trustAllCerts[0] = tm;
//        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
//        sc.init(null, trustAllCerts, null);
//        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//    }
//
//
//    //设置 https 请求证书
//    static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
//        @Override
//        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//            return null;
//        }
//
//        public boolean isServerTrusted(
//                java.security.cert.X509Certificate[] certs) {
//            return true;
//        }
//
//        public boolean isClientTrusted(
//                java.security.cert.X509Certificate[] certs) {
//            return true;
//        }
//
//        @Override
//        public void checkServerTrusted(
//                java.security.cert.X509Certificate[] certs, String authType)
//                throws java.security.cert.CertificateException {
//            return;
//        }
//
//        @Override
//        public void checkClientTrusted(
//                java.security.cert.X509Certificate[] certs, String authType)
//                throws java.security.cert.CertificateException {
//            return;
//        }
//
//
//    }
//
//    /**
//     * post请求
//     *
//     * @param json 前端请求
//     * @return 返回值
//     * @throws Exception 返回异常
//     */
//    public static String sendPost(String address, String json) throws Exception {
//        //创建URL对象,设置请求url
//        URL url = new URL(address);
//        trustAllHttpsCertificates();
//        //调用URL对象的openConnection( )来获取HttpURLConnection对象实例
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        //请求方法为POST
//        conn.setRequestMethod("POST");
//        //设置连接超时为5秒
//        conn.setConnectTimeout(5000);
//        //允许输入输出
//        conn.setDoInput(true);
//        conn.setDoOutput(true);
//        //不能缓存
//        conn.setUseCaches(false);
//        //至少要设置的两个请求头
//        //设置头部信息
//        conn.setRequestProperty("accept", "*/*");
//        conn.setRequestProperty("connection", "Keep-Alive");
//        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//        //一定要设置 Content-Type 要不然服务端接收不到参数
//        conn.setRequestProperty("Content-Type", "application/Json; charset=UTF-8");
//        //输出流包含要发送的数据,要注意数据格式编码
//        OutputStream op = conn.getOutputStream();
//        op.write(json.getBytes());
//        //服务器返回东西了，先对响应码判断
//        String result = "";
//        if (conn.getResponseCode() == 200) {
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
//            String line = "";
//            while (null != (line = br.readLine())) {
//                result += line;
//            }
//            br.close();
//            conn.disconnect();
//            return result;
//        }
//        return result;
//    }
//
//    @Test
//    public void sendGetTest() throws Exception {
//        String address = "https://172.16.26.46:9444/rest/dig-buw-monitor/workflows/configuration/getStatusConfiguration";
//        //请求参数
//        Map<String, String> params = new HashMap<String, String>();
//        //这是该接口需要的参数
//        params.put("tel", "13777777777");
//        // 调用 get 请求
//        int count = 1000;
//        for (int i = 0; i < count; i++) {
//            String res = get(address, params, null);
//            //打印返回参数
//            System.out.println("第" + (i + 1) + "调用" + res);
//        }
//    }
//
//    @Test
//    public void sendPostTest() throws Exception {
//        //请求地址
////        String address = "http://172.16.26.54:8700/rest/dig-buw-manager/taskServiceForRest/getTaskDefineForExecutor";
//        String address = "https://172.16.26.46:9444/rest/dig-buw-manager/taskServiceForRest/getTaskDefineForExecutor";
//        //将对象转换为json，然后发送；
//        String json = "{\n" +
//                "  \"objectInfo\": {\n" +
//                "    \"nodeKey\": \"d63975e2c2f543aaac8e1bebb7eaac48\",\n" +
//                "    \"taskType\": \"m3221\",\n" +
//                "    \"tenantId\": \"cs\"\n" +
//                "  },\n" +
//                "  \"token\":1\n" +
//                "}";
//        String result = "";
//        int count = 1000;
//        for (int i = 0; i < count; i++) {
//            result = sendPost(address, json);
//            System.out.println("第" + (i + 1) + "次调用：" + result);
//        }
//    }
//
//}
