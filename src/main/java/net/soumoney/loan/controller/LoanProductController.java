package net.soumoney.loan.controller;

import com.google.gson.Gson;
import net.soumoney.loan.service.LoanProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangxiaojie on 2017/3/22.
 */
@RestController
@RequestMapping("/product")
public class LoanProductController {
    @Autowired
    LoanProductService loanProductService;
    @ResponseBody
    @RequestMapping("/hot")
    public String hotList(){
        Map<String, Object> resultMap = new HashMap();
        try {
            List list = loanProductService.hotList();
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

    @ResponseBody
    @RequestMapping("/recommend")
    public String recommendList(){
        Map<String, Object> resultMap = new HashMap();
        try {
            List list = loanProductService.recommendList();
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

    @ResponseBody
    @RequestMapping("/banners")
    public String bannerList(HttpServletResponse response){
        Map<String, Object> resultMap = new HashMap();
        try {
            List list = loanProductService.bannerList();
            resultMap.put("datalist", list);
            resultMap.put("statusCode", 0);
            resultMap.put("statusMsg", "success");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("statusCode", 1);
            resultMap.put("statusMsg", "error");
        }
        response.addHeader("Access-Control-Allow-Origin", "*");
        return new Gson().toJson(resultMap);
    }

    @ResponseBody
    @RequestMapping("/list")
    public String allList(String type, String money_limit){
        Map<String, Object> resultMap = new HashMap();
        try {
            List list = loanProductService.allList(type, money_limit);
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
}
