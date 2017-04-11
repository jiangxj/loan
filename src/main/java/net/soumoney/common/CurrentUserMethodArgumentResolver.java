package net.soumoney.common;

import net.soumoney.common.token.CurrentUser;
import net.soumoney.loan.dto.T03_user;
import net.soumoney.loan.mapper.T03_userMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * Created by jiangxiaojie on 2017/4/11.
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver{
    @Autowired
    T03_userMapper t03_userMapper;
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.getParameterType().isAssignableFrom(T03_user.class)
                && methodParameter.hasParameterAnnotation(CurrentUser.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String uid = (String)nativeWebRequest.getAttribute(Constants.CURR_USER_ID,
                RequestAttributes.SCOPE_REQUEST);
        if(StringUtils.isNotBlank(uid)){
            return t03_userMapper.findT03_userByUid(uid);
        }
        throw new MissingServletRequestPartException(Constants.CURR_USER_ID);
    }
}
