package net.soumoney.loan.service;

import net.soumoney.common.utils.DateUtils;
import net.soumoney.common.utils.StringUtils;
import net.soumoney.loan.dto.T03_user;
import net.soumoney.loan.dto.T06_user_apply;
import net.soumoney.loan.dto.T06_user_collect;
import net.soumoney.loan.mapper.T03_userMapper;
import net.soumoney.loan.mapper.T06_user_applyMapper;
import net.soumoney.loan.mapper.T06_user_collectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/27.
 */
@Service
public class UserService {

    @Autowired
    T03_userMapper t03_userMapper;

    @Autowired
    T06_user_applyMapper t06_user_applyMapper;

    @Autowired
    T06_user_collectMapper t06_user_collectMapper;

    public List findUserCollectListByTelephone(String telephone){
        return t06_user_collectMapper.findT06_user_collectListByTelephone(telephone);
    }

    public List findUserApplyListByTelephone(String telephone){
        return t06_user_applyMapper.findT06_user_applyListByTelephone(telephone);
    }

    public void apply(String telephone, String pid){
        T03_user user = checkUser(telephone);
        if(user == null){
            return;
        }
        T06_user_apply apply = new T06_user_apply();
        apply.setUid(user.getUid());
        apply.setCreatedate(DateUtils.getCurrTime());
        apply.setAid(StringUtils.UUID());
        apply.setBusinesskey(pid);
        t06_user_applyMapper.insertT06_user_apply(apply);
    }

    public void collect(String telephone, String pid){
        T03_user user = checkUser(telephone);
        if(user == null){
            return;
        }
        T06_user_collect collect = new T06_user_collect();
        collect.setUid(user.getUid());
        collect.setCreatedate(DateUtils.getCurrTime());
        collect.setCid(StringUtils.UUID());
        collect.setBusinesskey(pid);
        t06_user_collectMapper.insertT06_user_collect(collect);
    }

    private T03_user checkUser(String telephone){
        if(org.apache.commons.lang3.StringUtils.isBlank(telephone)){
            return null;
        }
        T03_user user = t03_userMapper.findT03_userByTelephone(telephone);
        if(user == null || org.apache.commons.lang3.StringUtils.isBlank(user.getTelephone())){
            return null;
        }
        return user;
    }
}
