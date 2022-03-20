package com.example.aserver.gp;

import com.alibaba.fastjson.JSONObject;
import com.futu.openapi.*;
import com.futu.openapi.pb.QotCommon;
import com.futu.openapi.pb.QotRequestTradeDate;

public class QotDemo implements FTSPI_Qot, FTSPI_Conn {
    FTAPI_Conn_Qot qot = new FTAPI_Conn_Qot();

    public QotDemo() {
        qot.setClientInfo("javaclient", 1);  //设置客户端信息
        qot.setConnSpi(this);  //设置连接回调
        qot.setQotSpi(this);   //设置交易回调
    }

    public void start() {
        qot.initConnect("127.0.0.1", (short)11111, false);
    }

    @Override
    public void onInitConnect(FTAPI_Conn client, long errCode, String desc)
    {
        System.out.printf("Qot onInitConnect: ret=%b desc=%s connID=%d\n", errCode, desc, client.getConnectID());
        if (errCode != 0){
            return;
        }

        QotCommon.Security sec = QotCommon.Security.newBuilder()
            .setMarket(QotCommon.QotMarket.QotMarket_HK_Security_VALUE)
            .setCode("00700")
            .build();
        QotRequestTradeDate.C2S c2s = QotRequestTradeDate.C2S.newBuilder()
            .setMarket(QotCommon.TradeDateMarket.TradeDateMarket_HK.getNumber())
            .setBeginTime("2020-08-01")
            .setEndTime("2020-09-01")
            .setSecurity(sec)
            .build();
        QotRequestTradeDate.Request req = QotRequestTradeDate.Request.newBuilder().setC2S(c2s).build();
        int seqNo = qot.requestTradeDate(req);
        System.out.printf("Send QotRequestTradeDate: %d\n", seqNo);
    }

    @Override
    public void onDisconnect(FTAPI_Conn client, long errCode) {
        System.out.printf("Qot onDisConnect: %d\n", errCode);
    }

    @Override
    public void onReply_RequestTradeDate(FTAPI_Conn client, int nSerialNo, QotRequestTradeDate.Response rsp) {
        if (rsp.getRetType() != 0) {
            System.out.printf("QotRequestTradeDate failed: %s\n", rsp.getRetMsg());
        }
        else {
            try {
                System.out.printf("Receive QotRequestTradeDate: %s\n", JSONObject.toJSONString(rsp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FTAPI.init();
        QotDemo qot = new QotDemo();
        qot.start();

        while (true) {
            try {
                Thread.sleep(1000 * 600);
            } catch (InterruptedException exc) {

            }
        }
    }
}