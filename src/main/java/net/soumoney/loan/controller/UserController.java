package net.soumoney.loan.controller;

import com.google.gson.Gson;
import net.soumoney.common.token.Authorization;
import net.soumoney.common.token.CurrentUser;
import net.soumoney.loan.dto.T03_user;
import net.soumoney.loan.service.FaqService;
import net.soumoney.loan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangxiaojie on 2017/3/27.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @Authorization
    @ResponseBody
    @RequestMapping("/applylist")
    public String userApplyList(@CurrentUser T03_user user){
        Map<String, Object> resultMap = new HashMap();
        try {
            List list = userService.findUserApplyListByTelephone(user.getTelephone());
            resultMap.put("datalist", list);
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
    @RequestMapping("/apply")
    public String userApply(@CurrentUser T03_user user, String pid){
        Map<String, Object> resultMap = new HashMap();
        try {
            userService.apply(user.getTelephone(), pid);
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
    @RequestMapping("/collectlist")
    public String userCollectList(@CurrentUser T03_user user){
        Map<String, Object> resultMap = new HashMap();
        try {
            List list = userService.findUserCollectListByTelephone(user.getTelephone());
            resultMap.put("datalist", list);
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
    @RequestMapping("/collect")
    public String userCollect(@CurrentUser T03_user user, String pid){
        Map<String, Object> resultMap = new HashMap();
        try {
            userService.collect(user.getTelephone(), pid);
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
