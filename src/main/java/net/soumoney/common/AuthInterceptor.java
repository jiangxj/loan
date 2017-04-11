package net.soumoney.common;

import com.google.gson.Gson;
import net.soumoney.common.domain.TokenModel;
import net.soumoney.common.token.Authorization;
import net.soumoney.common.token.TokenManager;
import net.soumoney.redis.JedisConnection;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by jiangxiaojie on 2017/3/28.
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter{
    @Autowired
    private TokenManager tokenManager;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod hm = (HandlerMethod)handler;
        Method method = hm.getMethod();
        String authorization = request.getHeader(Constants.AUTHORIZATION);
        TokenModel tm = tokenManager.getToken(authorization);
        if(tokenManager.checkToken(tm)){
            request.setAttribute(Constants.CURR_USER_ID, tm.getUid());
            return true;
        }
        if(method.getAnnotation(Authorization.class) != null){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }
}
