package net.soumoney.loan.service;

import net.soumoney.loan.mapper.T06_credit_cardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/27.
 */
@Service
public class CreditCardService {
    @Autowired
    T06_credit_cardMapper t06_credit_cardMapper;

    public List creditCardList(){
        return t06_credit_cardMapper.findT06_credit_cardList();
    }
}
