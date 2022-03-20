package com.example.yangt.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {


    public static String getUUID() {

        String uuid = UUID.randomUUID().toString(); //转化为String对象
        uuid = uuid.replace("-", "");

        return uuid;
    }

    public static String getToken() {

        String uuid = UUID.randomUUID().toString(); //转化为String对象
        uuid = uuid.replace("-", "");

        return "AT-"+uuid;
    }

    /**
     * 返回响应结果 json
     * @param errCode
     * @param errMsg
     * @param data
     * @return
     */
    public String result(String errCode, String errMsg, Map<String,Object> data){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("error_code",errCode);
        resultMap.put("error_msg",errMsg);
        resultMap.put("data",data);

        return JsonUtils.getObjectToJson(resultMap);
    }
}
