package com.datastructureexercises.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestJsonConvert {
    public static void main(String[] args) {
        Map<String, Object> stringObjectHahsMap = new HashMap<String, Object>();
        stringObjectHahsMap.put("a",null);
        stringObjectHahsMap.put("b","bb");
        stringObjectHahsMap.put("c",JSONObject.fromObject(stringObjectHahsMap).toString());
        stringObjectHahsMap.put("d",JSONObject.fromObject(stringObjectHahsMap).toString());

        //String jsonObject=JSON.toJSONString(stringObjectHahsMap);
        String resount=JSON.toJSONString(stringObjectHahsMap, filter);
        JSONObject jo=JSONObject.fromObject(stringObjectHahsMap);
        System.out.println(jo.toString());
        System.out.println(filterNull(jo).toString());
        /*if(jo.toString().contains(":null")){
            jo=JSONObject.fromObject(jo.toString().replaceAll(":null", ":\"\""));
        }
        System.out.println(jo.toString());*/
        //JSONObjecttoJSONString(stringObjectHahsMap, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty)

    }
    @Test
    public void testJson(){
        String jsonStr="{\n" +
                "    \"a\": null,\n" +
                "    \"b\": \"bb\",\n" +
                "    \"c\": \"{\\\"a\\\":\\\"\\\",\\\"b\\\":\\\"bb\\\"}\",\n" +
                "    \"d\": \"{\\\"a\\\":\\\"\\\",\\\"b\\\":\\\"bb\\\",\\\"c\\\":\\\"{\\\\\\\"a\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"b\\\\\\\":\\\\\\\"bb\\\\\\\"}\\\"}\"\n" +
                "}";
        String jsonStr2="{\n" +
                "    \"a\": null,\n" +
                "    \"b\": \"bb\",\n" +
                "    \"c\": \"{\\\"a\\\":null,\\\"b\\\":\\\"bb\\\"}\",\n" +
                "    \"d\": \"{\\\"a\\\":null,\\\"b\\\":\\\"bb\\\",\\\"c\\\":\\\"{\\\\\\\"a\\\\\\\":null,\\\\\\\"b\\\\\\\":\\\\\\\"bb\\\\\\\"}\\\"}\"\n" +
                "}";
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        JSONObject d = jsonObject.getJSONObject("d");
        System.out.println(d.toString());


        JSONObject jsonObject1 = JSONObject.fromObject(jsonStr2);
        JSONObject jsonObject2 = filterNull(jsonObject1);
        System.out.println(jsonObject2);
    }
    private static ValueFilter filter = new ValueFilter() {
        @Override
        public Object process(Object obj, String s, Object v) {
            if (v == null)
                return "";
            return v;
        }
    };
    /**
     * 将json对象中包含的null和JSONNull属性修改成""
     *jwb
     * @param jsonObj
     */
    public static JSONObject filterNull(JSONObject jsonObj) {
        Iterator<String> it = jsonObj.keys();
        Object obj = null;
        String key = null;
        while (it.hasNext()) {
            key = it.next();
            obj = jsonObj.get(key);
            if (obj instanceof JSONObject) {
                filterNull((JSONObject) obj);
            }
            /*if (obj instanceof JSONArray) {
                JSONArray objArr = (JSONArray) obj;
                for (int i = 0; i < objArr.length(); i++) {
                    filterNull(objArr.getJSONObject(i));
                }
            }*/
            if (obj == null || obj instanceof JSONNull) {
                jsonObj.put(key, "");
            }
            if (obj.equals(null)) {
                jsonObj.put(key, "");
            }
        }
        return jsonObj;
    }

}
