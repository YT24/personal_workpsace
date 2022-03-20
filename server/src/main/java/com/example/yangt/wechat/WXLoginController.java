package com.example.yangt.wechat;

import com.example.yangt.utils.JsonUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Map;


@RestController
public class WXLoginController {

	public static final String APPID = "wx3ff6ec02f32534d5";
	public static final String APPSECRET = "600abedef31f548f9025e2e166d53485";
	
	RestTemplate resttemplate = new RestTemplate();
	@RequestMapping(value = "/wechat", method = RequestMethod.GET)
	public void getWxCode(HttpServletRequest request, HttpServletResponse response) throws ParseException {

		if(StringUtils.isEmpty(request.getParameter("code"))) {
			// 这个url的域名必须要进行再公众号中进行注册验证，这个地址是成功后的回调地址
			String backUrl = "https://yzttest.jomoo.cn/wechat";
			String state = 1+(int)(Math.random()*500) +"";
			String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APPID + "&redirect_uri="+ URLEncoder.encode(backUrl) + "&response_type=code" + "&scope=snsapi_base"+ "&state="+state+"#wechat_redirect";
			System.err.println("--------------------------------------url:" + url + "----------------------------------");
			try {
				response.sendRedirect(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			String code = request.getParameter("code");
			System.out.println("-----------------------code:"+code+"-------------------------");
			String openIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret="+ APPSECRET + "&code="+code+"&grant_type=authorization_code";
			
			// 1 使用code 换取accesstoken+openid
			String result = resttemplate.getForObject(openIdUrl, String.class);
			Map<String, String> resultOpenIdMap = JsonUtils.getJson2Obj(result, Map.class);
			System.err.println("---------------------------result:"+result+"-----------------------------");
			
			//2 使用appid/appsecret 获取token
			String tokenurl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ APPID+"&secret="+APPSECRET;
			String tokenResult = resttemplate.getForObject(tokenurl, String.class);
			System.err.println("--------------------------------tokenResult:"+tokenResult+"-----------------------------------");
			Map<String, String> tokenMap = JsonUtils.getJson2Obj(tokenResult, Map.class);

			// 3 使用 1 中的openid 2中的accesstoken 换取unionid
			String unionIdUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+tokenMap.get("access_token")+"&openid="+resultOpenIdMap.get("openid")+"&lang=zh_CN";
			System.err.println(unionIdUrl);
			String resultUnionIdMap = resttemplate.getForObject(unionIdUrl, String.class);
			System.err.println("---------------------------resultUnionIdMap:"+resultUnionIdMap+"------------------------------");
		}
		
	}
	
	@RequestMapping(value = "/unionId", method = RequestMethod.GET)
	public void getUnionid(HttpServletRequest request, HttpServletResponse response) {
		String code = request.getParameter("code");
		String openIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + APPID + "&secret="+ APPSECRET + "&code="+code+"&grant_type=authorization_code";
		// 1 使用code 换取accesstoken+openid
		String result = resttemplate.getForObject(openIdUrl, String.class);
		Map<String, String> resultOpenIdMap = JsonUtils.getJson2Obj(result, Map.class);
		System.out.println("---------------------------"+result+"-----------------------------");
		String unionIdUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + resultOpenIdMap.get("access_token") + "&openid="+ resultOpenIdMap.get("openid");
		String resultUnionIdMap = resttemplate.getForObject(unionIdUrl, String.class);
		System.out.println("---------------------------resultUnionIdMap:"+resultUnionIdMap+"------------------------------");
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response) {

		return "SUCCESS";
	}
	
	
	public static void main(String[] args) {
		int x = 0;
		for (int i = 0; i <10000000 ; i++) {
			if(String.valueOf(i).startsWith("12")){
				i++;
			}
		}
		System.out.println(x);
	}


}
