package com.example.client.controller;

import com.example.client.feign.LoginService;
import com.example.client.swagger2.SwaggerOpen;
import com.example.client.swagger2.entity.LoginRequestData;
import com.example.client.swagger2.entity.LoginReturnData;
import com.example.client.swagger2.entity.ResultData;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.index.reindex.UpdateByQueryRequestBuilder;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @SwaggerOpen
    @ApiOperation(value = "登录",httpMethod = "POST",notes = "登录")
    @PostMapping("login")
    public ResultData<Object> login(@RequestBody LoginRequestData loginRequestData, HttpServletRequest request, HttpServletResponse response){
        log.info("-------------login param is : {}",loginRequestData);
        String loginResult = loginService.login(loginRequestData);
        LoginReturnData loginReturnData = LoginReturnData.builder().access_token("1").refresh_token("2").id_token("3").build();
        return ResultData.builder().error_code("0")
                .error_msg("success:"+loginResult)
                .data(loginReturnData)
                .build();
    }

    @ApiOperation(value = "test")
    @GetMapping("test")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "指标id",paramType = "query")
    })
    public String test(@RequestParam(value = "indicatorId",required = true) String id){
        return "test welcome ~~~";
    }

//    @SwaggerOpen
//    @OperLog(operModul = "F200000",operType ="F20000004", operDesc = "获取OIDC解密id_token公钥信息")
//    @ApiOperation(value = "获取公钥", httpMethod = "POST", notes = "获取OIDC解密id_token公钥信息")
//    @PostMapping("/oidc/jwks")
//    public ResultDataJwks<JwkVO<Jwk>> jwks(@RequestBody UserAuthJwksVO userAuthJwksVO) {
//        log.info("jwks param:{}", userAuthJwksVO.toString());
////        JsonObject jb_param = JsonParser.parseString(param).getAsJsonObject();
//        if (StringUtils.isEmpty(userAuthJwksVO.getClient_id()) || StringUtils.isEmpty(userAuthJwksVO.getClient_secret())) {
//            return ResultDataJwks.error(ResponseStatus.PARAM_ERR.value(), ResponseStatus.PARAM_ERR.getReasonPhrase());
//        }
//        String client_secret_ = AESOperator.getInstance().decrypt(userAuthJwksVO.getClient_secret(), sKey, ivParameter);
//        if (StringUtils.isEmpty(client_secret_)) {
//            return ResultDataJwks.error(ResponseStatus.CLIENT_SECRET_ERR.value(), ResponseStatus.CLIENT_SECRET_ERR.getReasonPhrase());
//        }
//        if (!commonResourceService.checkClientId(userAuthJwksVO.getClient_id())) {
//            return ResultDataJwks.error(ResponseStatus.CLIENT_ID_ERR.value(), ResponseStatus.CLIENT_ID_ERR.getReasonPhrase());
//        }
//        String result = null;
//        try {
//            result = ssoClient.oidcJwks();
//        } catch (Exception e) {
//            return ResultDataJwks.error(ResponseStatus.INNERR_EXCEPTION.value(), ResponseStatus.INNERR_EXCEPTION.getReasonPhrase() + ":" + e.getMessage());
//        }
//        log.info("jwks result:{}", result);
//        JsonObject jb = JsonParser.parseString(result).getAsJsonObject();
//        JsonArray jsonArray = jb.get("keys").getAsJsonArray();
//        Iterator it = jsonArray.iterator();
//        List<Jwk> listJwk = new ArrayList<Jwk>();
//        while (it.hasNext()) {
//            JsonElement ele = (JsonElement) it.next();
//            JsonObject eachjb = ele.getAsJsonObject();
//            String e = eachjb.has("e") ? eachjb.get("e").getAsString() : null;
//            String n = eachjb.has("n") ? eaString kty = eachjb.has("kty") ? eachjb.get("kty").getAsString() : null;
//            String kid = eachjb.has("kid") ? eachjb.get("kid").getAsString() : null;
//            Jwk jwk = new Jwk(null, e, n, kty, kid);
//            listJwk.add(jwk);
//        }
//        JwkVO<Jwk> jwkVo = JwkVO.<Jwk>builder()
//                .keys(listJwk)
//                .build();
//        return ResultDataJwks.<JwkVO<Jwk>>builder()
//                .error_code("0")
//                .error_msg("SUCCESS")
//                .data(jwkVo)
//                .build();
//    }

    public static void main(String[] args) {
        TransportClient client = null;
        Settings settings = Settings.builder().put("cluster.name", "my-application")
                .put("client.transport.sniff",false).build();// 集群名
        //创建client
        try {
            client  = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (UnknownHostException e) {

        }
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("desc", "李四是个测试工程师--------");
        UpdateByQueryRequestBuilder updateByQueryRequestBuilder = UpdateByQueryAction.INSTANCE.newRequestBuilder(client);
        updateByQueryRequestBuilder.source("test_demo_index")
                .filter(QueryBuilders.termQuery("name.keyword", "李四1"))
                .size(1000)
                .script((new Script(ScriptType.INLINE, "painless", "ctx._source.desc=params.desc", paramsMap))).execute();
        BulkByScrollResponse response = updateByQueryRequestBuilder.get();
        System.out.println("response.getStatus:"+response.getStatus());



        GetRequestBuilder getRequestBuilder = client.prepareGet("test_demo_index", "user", "12345678");
        GetResponse fields = getRequestBuilder.get();
       /* DeleteResponse dResponse = client.prepareDelete("test_demo_index","user", "12345678").execute().actionGet();
        if ("OK".equals(dResponse.status())) {
            System.out.println("删除成功");
        } else {
            System.out.println("删除失败");
        }*/

        client.close();
    }
}

