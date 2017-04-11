package net.soumoney.loan.service;

import com.github.pagehelper.PageHelper;
import net.soumoney.loan.dto.T06_loan_product;
import net.soumoney.loan.mapper.T00_dictMapper;
import net.soumoney.loan.mapper.T06_loan_bannerMapper;
import net.soumoney.loan.mapper.T06_loan_productMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/22.
 */
@Service
public class LoanProductService {
    @Autowired
    T06_loan_productMapper t06_loan_productMapper;

    @Autowired
    T06_loan_bannerMapper t06_loan_bannerMapper;

    public List hotList(){
        T06_loan_product loan_product = new T06_loan_product();
        loan_product.setIs_hot("1");
        loan_product.setIs_show("1");
        PageHelper.startPage(1, 4);
        return t06_loan_productMapper.findT06_loan_productList(loan_product);
    }

    public List recommendList(){
        T06_loan_product loan_product = new T06_loan_product();
        loan_product.setIs_recommnet("1");
        loan_product.setIs_show("1");
        return t06_loan_productMapper.findT06_loan_productList(loan_product);
    }

    public List bannerList(){
        return t06_loan_bannerMapper.findT06_loan_bannerList();
    }

    public List allList(String type, String money_limit){
        T06_loan_product loan_product = new T06_loan_product();
        loan_product.setMoney_limit(money_limit);
        loan_product.setIs_show("1");
        loan_product.setType(type);
        return t06_loan_productMapper.findT06_loan_productList(loan_product);
    }

    public T06_loan_product productDetail(String pid){
        return t06_loan_productMapper.findT06_loan_productByPID(pid);
    }

}
