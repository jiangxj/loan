package net.soumoney.loan.service;

import net.soumoney.common.Constants;
import net.soumoney.common.domain.TokenModel;
import net.soumoney.common.token.TokenManager;
import net.soumoney.common.utils.DateUtils;
import net.soumoney.loan.dto.T03_user;
import net.soumoney.loan.mapper.T03_userMapper;
import net.soumoney.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by jiangxiaojie on 2017/3/28.
 */
@Service
public class AuthService {

    @Autowired
    RedisService redisService;
    @Autowired
    T03_userMapper t03_userMapper;
    @Autowired
    TokenManager tokenManager;

    public TokenModel authenticate(String telephone, String passCode) throws Exception{
        T03_user user = t03_userMapper.findT03_userByTelephone(telephone);
        String key = net.soumoney.common.utils.StringUtils.md5(Constants.PREFIX+telephone);
        String realPassCode = redisService.get(key);
        String uid = null;
        if(StringUtils.isNotBlank(passCode)
                && StringUtils.isNotBlank(realPassCode)
                    && StringUtils.equals(passCode, realPassCode)){
                if(user == null || StringUtils.isBlank(user.getTelephone())){
                    uid = net.soumoney.common.utils.StringUtils.UUID();
                    T03_user sUser = new T03_user();
                    sUser.setTelephone(telephone);
                    sUser.setUid(uid);
                    sUser.setCreatedate(DateUtils.getCurrTime());
                    sUser.setFlag("1");
                    t03_userMapper.insertT03_user(sUser);
                }else{
                    uid = user.getUid();
                }
                return tokenManager.createToken(uid);
        }
        return null;
    }

    public void logout(String uid){
        tokenManager.deleteToken(uid);
    }
}
