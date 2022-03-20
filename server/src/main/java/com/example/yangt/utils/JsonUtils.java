package com.example.yangt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.DataInput;
import java.io.InputStream;

public class JsonUtils {
    protected static final Gson gson = new Gson();

    protected static final ObjectMapper MAPPER = new ObjectMapper();

    public static String  getObjectToJson(Object object){

        return gson.toJson(object);
    }

    /**
     * JSON <-----> Object
     * @param obj
     * @param sClass
     * @return
     */
    public static <S> S getJsonObj(Object obj, Class<S> sClass) {
        try {
            if (obj instanceof String) {
                return MAPPER.readValue((String)obj, sClass);
            }
            if (obj instanceof byte[]) {
                return MAPPER.readValue((byte[])((byte[])obj), sClass);
            }
            if (obj instanceof InputStream) {
                return MAPPER.readValue((InputStream)obj, sClass);
            }
            if (obj instanceof DataInput) {
                return MAPPER.readValue((DataInput)obj, sClass);
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return null;
    }

    /**
     * obj 转 字节
     * @param obj
     * @return
     */
    public static byte[] getObj2Json(Object obj) {
        byte[] message = null;

        try {
            message = MAPPER.writeValueAsBytes(obj);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return message;
    }

    /**
     * object 转json
     * @param obj
     * @return
     */
    public static String getJsonString(Object obj) {
        String message = null;

        try {
            message = MAPPER.writeValueAsString(obj);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

        return message;
    }

    public static  <S> S getJson2Obj(Object obj,final Class<S> serviceClass) {

        try {
            if(obj instanceof String) {
                return MAPPER.readValue((String)obj, serviceClass);
            }else
            if(obj instanceof byte[]) {
                return MAPPER.readValue((byte[])obj, serviceClass);
            }else
            if(obj instanceof InputStream) {
                return MAPPER.readValue((InputStream)obj, serviceClass);
            }else
            if(obj instanceof DataInput) {
                return MAPPER.readValue((DataInput)obj, serviceClass);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
