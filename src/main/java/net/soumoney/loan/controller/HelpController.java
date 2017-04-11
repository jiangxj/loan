package net.soumoney.loan.controller;

import com.google.gson.Gson;
import net.soumoney.loan.service.FaqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jiangxiaojie on 2017/3/28.
 */
@RestController
@RequestMapping("/help")
public class HelpController {
    @Autowired
    FaqService faqService;

    @ResponseBody
    @RequestMapping("/faq")
    public String faqList(){
        Map<String, Object> resultMap = new HashMap();
        try {
            List list = faqService.faqList();
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
