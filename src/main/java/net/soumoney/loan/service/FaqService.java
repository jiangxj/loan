package net.soumoney.loan.service;

import net.soumoney.loan.mapper.T06_faqMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/27.
 */
@Service
public class FaqService {
    @Autowired
    T06_faqMapper t06_faqMapper;
    public List faqList(){
        return t06_faqMapper.findT06_faqList();
    }
}
