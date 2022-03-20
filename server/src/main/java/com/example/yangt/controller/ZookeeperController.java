package com.example.yangt.controller;//package com.example.demo.controller;
//
//import com.example.demo.enums.CommonEnums;
//import com.example.demo.enums.CommonResult;
//import com.example.demo.zookeeper.ZkDistributeLock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.Map;
//
//@RestController
//public class ZookeeperController {
//
//    @Autowired
//    private ZkDistributeLock zkDistributeLock;
//
//    @GetMapping("lock")
//    public CommonResult zklock(@RequestBody Map<String,String> map, HttpServletRequest request, HttpServletResponse response){
//
//        zkDistributeLock.zkLock(map.get("path"));
//
//        zkDistributeLock.zkLock(map.get("path"));
//
//        return new CommonResult(CommonEnums.ErrorCode.SUCCESS.getVal(), CommonEnums.ErrorCode.SUCCESS.getResourceKey(), null);
//
//    }
//}
