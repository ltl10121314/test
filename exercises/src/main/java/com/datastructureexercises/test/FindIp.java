package com.datastructureexercises.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FindIp {

    final String STRING = "[" +
            "{\"begin_int_ip\":\"10.10.10.1\",\"end_int_ip\":\"10.10.11.128\",\"city\":\"北京\"},\n" +
            "{\"begin_int_ip\":\"10.10.12.1\",\"end_int_ip\":\"10.10.12.120\",\"city\":\"上海\"},\n" +
            "{\"begin_int_ip\":\"100.1.1.2\",\"end_int_ip\":\"103.1.1.3\",\"city\":\"天津\"},\n" +
            "{\"begin_int_ip\":\"104.10.10.1\",\"end_int_ip\":\"105.10.11.128\",\"city\":\"杭州\"},\n" +
            "{\"begin_int_ip\":\"106.10.10.1\",\"end_int_ip\":\"107.10.11.128\",\"city\":\"西安\"}" +
            "]";
    private static final int RECORD_NUM = 5;//5条IP地址段数据
    private long[] ipSegments;
    private static List<String> ipRegions;

    public FindIp() {
        ipSegments = new long[RECORD_NUM * 2];// 每个区间 有起始位置和终点位置 [startPos, endPos]
        ipRegions = new ArrayList<>(RECORD_NUM);
    }


    private int parse() {
        JSONArray jsonArray = JSONArray.parseArray(STRING);
        int recordNum = 0;
        int index = 0;
        for (Object ob : jsonArray) {
            JSONObject jsonObject = JSONObject.parseObject(ob.toString());
            ipSegments[index++] = toSmallLongFromIpAddress(jsonObject.getString("begin_int_ip"));
            ipSegments[index++] = toSmallLongFromIpAddress(jsonObject.getString("end_int_ip"));
            ipRegions.add(jsonObject.getString("city"));
            recordNum++;
        }
        return recordNum;
    }

    public static void main(String[] args) {
        FindIp fip = new FindIp();
        fip.parse();
        String ip = "106.10.11.12";
        long startTime = System.currentTimeMillis();
        String address = fip.find(ip);
        System.out.println("ip: " + ip + ", address:" + address);
        long endTime = System.currentTimeMillis();
        System.out.println("find five ip address use time:" + (endTime - startTime) + "ms");
    }

    public String find(String ip) {
        long startTime = System.currentTimeMillis();

        long ipConvert = toSmallLongFromIpAddress(ip);//

        System.out.println("ip:" + ip + ", convertInt:" + ipConvert);

        int index = binarySearch(ipSegments, ipConvert);
        if (index == -1)
            return "null";
        String addressResult = ipRegions.get(index / 2);
        long endTime = System.currentTimeMillis();
        System.out.println("find: " + ip + " use time: " + (endTime - startTime));
        return addressResult;
    }

    private int binarySearch(long[] arr, long searchNumber) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("初始化失败...");
        }
        int low = 0;
        int high = arr.length - 1;
        int mid;
        System.out.println("arr len:" + arr.length);
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] > searchNumber)
                high = mid - 1;
            else if (arr[mid] < searchNumber)
                low = mid + 1;
            else
                return mid;
        }
        System.out.println("low=" + low + ", high=" + high);
        if (low > arr.length - 1 || high < 0)
            return -1;
        return high;
    }

    private static long toSmallLongFromIpAddress(String strIp) {
        long[] ip = new long[4];
        String[] ipSegments = strIp.split("\\.");
        for (int i = 0; i < 4; i++) {
            ip[i] = Long.parseLong(ipSegments[i]);
        }
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }
}
