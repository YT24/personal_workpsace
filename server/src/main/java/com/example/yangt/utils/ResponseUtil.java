package com.example.yangt.utils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.example.yangt.pojo.ResultMessage;
import com.google.gson.Gson;

public class ResponseUtil {

    public static void resultMsg(String result, HttpServletResponse response, int responseCode) {
        response.setCharacterEncoding("utf-8");
        response.setStatus(200);
        try {
            ServletOutputStream e = response.getOutputStream();
            e.write((result).getBytes("utf-8"));
            e.flush();
            response.flushBuffer();
            e.close();
        } catch (Exception e) {
            System.out.println("error is out");
        }
        return;
    }

    public static void dealResult(int errorCode, String errorMsg, Object data, HttpServletResponse response) {
        ResultMessage errorResult = new ResultMessage();
        errorResult.setError_code("" + errorCode);
        errorResult.setError_Msg(errorMsg);
        errorResult.setData(data);

        resultMsg(new Gson().toJson(errorResult), response, errorCode);
    }

}
