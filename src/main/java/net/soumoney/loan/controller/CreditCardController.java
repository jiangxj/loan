package net.soumoney.loan.controller;

import com.google.gson.Gson;
import net.soumoney.loan.service.CreditCardService;
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
@RequestMapping("/card")
public class CreditCardController {
    @Autowired
    CreditCardService creditCardService;

    @ResponseBody
    @RequestMapping("/hotlist")
    public String creditCardList(){
        Map<String, Object> resultMap = new HashMap();
        try {
            List list = creditCardService.creditCardList();
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
