package net.soumoney.loan.controller;

import com.google.gson.Gson;
import net.soumoney.common.Constants;
import net.soumoney.common.domain.TokenModel;
import net.soumoney.common.token.Authorization;
import net.soumoney.common.token.CurrentUser;
import net.soumoney.common.utils.SMSUtils;
import net.soumoney.common.utils.StringUtils;
import net.soumoney.loan.dto.T03_user;
import net.soumoney.loan.service.AuthService;
import net.soumoney.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangxiaojie on 2017/3/28.
 */
@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping("/login")
    public String login(String telephone, String passCode){
        Map<String, Object> resultMap = new HashMap();
        try {
            TokenModel token = authService.authenticate(telephone, passCode);
            if(token == null){
                resultMap.put("statusCode", 2);
                resultMap.put("statusMsg", "unlogin");
            }else{
                String authentication = token.getUid()+"_"+token.getToken();
                resultMap.put("auth", authentication);
                resultMap.put("statusCode", 0);
                resultMap.put("statusMsg", "success");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("statusCode", 1);
            resultMap.put("statusMsg", "error");
        }
        return new Gson().toJson(resultMap);
    }

    @ResponseBody
    @RequestMapping("/sendSMSPassCode")
    public String sendSMSPassCode(String telephone){
        Map<String, Object> resultMap = new HashMap();
        try {
            String realPassCode = StringUtils.createRandom(true, 6);
            SMSUtils.sendAuthCodeMessage(telephone, realPassCode);
            String key = StringUtils.md5(Constants.PREFIX+telephone);
            redisService.set(key, realPassCode);
            redisService.expire(key, 60*1000);
            resultMap.put("statusCode", 0);
            resultMap.put("statusMsg", "success");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("statusCode", 1);
            resultMap.put("statusMsg", "error");
        }
        return new Gson().toJson(resultMap);
    }

    @Authorization
    @ResponseBody
    @RequestMapping("/logout")
    public String logout(@CurrentUser T03_user user){
        Map<String, Object> resultMap = new HashMap();
        try {
            authService.logout(user.getUid());
            resultMap.put("statusCode", 0);
            resultMap.put("statusMsg", "success");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("statusCode", 1);
            resultMap.put("statusMsg", "error");
        }
        return new Gson().toJson(resultMap);
    }
}
