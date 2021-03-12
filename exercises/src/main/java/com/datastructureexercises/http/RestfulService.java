//package com.datastructureexercises.http;
//
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import net.sf.json.JSONArray;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLSession;
//
//public class RestfulService {
//    public Map<String, Object> restfulService(Map<String, Object> map) throws Exception {
//        Map<String, Object> result = new HashMap<>();
//        String url = map.get("serverIp").toString().trim();
//        System.out.println("serverIp-------------------------" + url);
//        String postParam = (map.get("serverName") == null) ? "" : map.get("serverName").toString();
//        System.out.println("postJsonParam-------------------------" + postParam);
//        String requestMethod = map.get("requestMethod").toString().trim();
//        System.out.println("规则列表格式.........." + map.get("parameterList").toString());
//        String parameterList = map.get("parameterList").toString().trim();
//        String jsonRequestMethod = parameterList.equals("") ? "" : parameterList.trim().substring(1, parameterList.length() - 1);
//        System.out.println(jsonRequestMethod);
//        String parameterData = null;
//        String param = "?";
//        JSONArray fromObject = JSONArray.fromObject("[" + jsonRequestMethod +
//                "]");
//        System.out.println(fromObject.toString());
//        List<Map<String, Object>> listRequestMethod =
//                (List<Map<String, Object>>) JSONArray.toCollection(fromObject, Map.class);
//        System.out.println(listRequestMethod.toString());
//        for (Map<String, Object> m : listRequestMethod) {
//            String parameterName = replaceVariable(map,
//                    m.get("parameterName").toString().trim());
//            String parameterValue = replaceVariable(map,
//                    m.get("parameterValue").toString().trim());
//            param = String.valueOf(param) + parameterName + "=" + parameterValue + "&";
//        }
//        param = param.substring(0, param.length() - 1);
//        System.out.println("param=======" + param);
//        URL localURL = null;
//        if (requestMethod.equals("get")) {
//            System.out.println("get:" + url + param);
//            localURL = new URL(String.valueOf(url) + param);
//        } else {
//            if ("".equals(postParam) &&
//                    postParam != null && !"{}".equals(postParam)) {
//                if (postParam.indexOf("{{") != -1 && postParam.indexOf("}}") != -1 &&
//                        postParam.indexOf("{{") == 0 &&
//                        "}}".equals(postParam.substring(postParam.length() - 2, postParam.length()))) {
//                    System.out.println("post请求，参数json为============================"+ postParam.substring(1, postParam.length() - 1));
//                            parameterData = postParam.substring(1, postParam.length() - 1);
//                }
//                System.out.println("post请求，参数json为==========================="+ postParam);
//                        parameterData = postParam;
//            }
//            localURL = new URL(String.valueOf(url) + param);
//            System.out.println("post请求，参数json为================="+ localURL);
//        }
//        trustAllHttpsCertificates();
//        URLConnection connection = localURL.openConnection();
//        HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
//        httpURLConnection.setDoOutput(true);
//        httpURLConnection.setConnectTimeout(60000);
//        httpURLConnection.setReadTimeout(300000);
//        if ("get".equals(requestMethod)) {
//            httpURLConnection.setRequestMethod("GET");
//            httpURLConnection.setRequestProperty("Content-Type",
//                    "application/x-www-form-urlencoded;charset=UTF-8");
//        } else {
//            httpURLConnection.setRequestMethod("POST");
//            httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
//        }
//        OutputStream outputStream = null;
//        InputStream inputStream = null;
//        InputStreamReader inputStreamReader = null;
//        BufferedReader bufferedReader = null;
//        StringBuffer resultBuffer = new StringBuffer();
//        String tempLine = "";
//        if (parameterData != null) {
//            outputStream = new DataOutputStream(
//                    httpURLConnection.getOutputStream());
//            outputStream.write(parameterData.toString().getBytes());
//            outputStream.flush();
//        }
//        if (httpURLConnection.getResponseCode() == 200) {
//            inputStream = httpURLConnection.getInputStream();
//            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
//            bufferedReader = new BufferedReader(inputStreamReader);
//            while ((tempLine = bufferedReader.readLine()) != null) {
//                resultBuffer.append(tempLine);
//            }
//        } else {
//            throw new Exception(
//                    "Access restservice interface is exception response code："+
//                    httpURLConnection.getResponseCode() + "Error:" + httpURLConnection.getResponseMessage());
//        }
//        System.out.println(resultBuffer.toString());
//        result.put("Data", resultBuffer.toString());
//        result.put("flag", Boolean.valueOf(true));
//        return result;
//    }
//
//    private String replaceVariable(Map<String, Object> contextMap, String str) {
//        if (contextMap == null || str == null) {
//            return str;
//        }
//        Pattern pattern = Pattern.compile("\\$\\{([a-zA-Z\\_]\\w*)\\}");
//        Matcher matcher = pattern.matcher(str);
//        StringBuffer sb = new StringBuffer();
//        while (matcher.find()) {
//            String key = matcher.group(1);
//            Object value = contextMap.get(key);
//            if (value != null) {
//                value = value.toString();
//            }
//            matcher.appendReplacement(sb, (value == null) ? "null" :
//                    (String) value);
//        }
//        matcher.appendTail(sb);
//        return sb.toString();
//    }
//
//    public void trustAllHttpsCertificates() throws Exception {
//        HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String str, SSLSession session) {
//                return true;
//            }
//        });
//        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
//        javax.net.ssl.TrustManager tm = new HTTPUtils.miTM();
//        trustAllCerts[0] = tm;
//        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
//        sc.init(null, trustAllCerts, null);
//        javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//    }
//}
//
