package net.soumoney.common.token;

import net.soumoney.common.Constants;
import net.soumoney.common.domain.TokenModel;
import net.soumoney.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by jiangxiaojie on 2017/4/11.
 */
@Component
public class RedisTokenManager implements TokenManager{
    RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(redisTemplate.getStringSerializer());
        this.redisTemplate.setValueSerializer(redisTemplate.getStringSerializer());
    }
    @Override
    public TokenModel createToken(String uid) {
        String token = UUID.randomUUID().toString().replace("-", "");
        TokenModel tokenModel = new TokenModel();
        tokenModel.setToken(token);
        tokenModel.setUid(uid);
        this.redisTemplate.boundValueOps(uid).set(token, Constants.TOKEN_EXPIRES_HOURS, TimeUnit.HOURS);
        return tokenModel;
    }

    @Override
    public boolean checkToken(TokenModel tokenModel) {
        if(tokenModel == null){
            return false;
        }
        String token = this.redisTemplate.boundValueOps(tokenModel.getUid()).get();
        if(token == null || !StringUtils.equals(token, tokenModel.getToken())){
            return false;
        }

        this.redisTemplate.boundValueOps(tokenModel.getUid()).expire(Constants.TOKEN_EXPIRES_HOURS, TimeUnit.HOURS);
        return true;
    }

    @Override
    public TokenModel getToken(String authentication) {
        if(authentication == null || StringUtils.isBlank(authentication)){
            return null;
        }
        String[] params = authentication.split("_");
        if(params.length !=2){
            return null;
        }
        String uid = params[0];
        String token = params[1];
        TokenModel tm = new TokenModel();
        tm.setUid(uid);
        tm.setToken(token);
        return tm;
    }

    @Override
    public void deleteToken(String uid) {
        this.redisTemplate.delete(uid);
    }
}
