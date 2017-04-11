package net.soumoney.common.token;

import net.soumoney.common.domain.TokenModel;

/**
 * Created by jiangxiaojie on 2017/4/11.
 */
public interface TokenManager {
    public TokenModel createToken(String uid);

    public boolean checkToken(TokenModel token);

    public TokenModel getToken(String authentication);

    public void deleteToken(String uid);

}
