package net.soumoney.loan.mapper;

import net.soumoney.loan.dto.T00_dict;
import net.soumoney.loan.dto.T03_user;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/22.
 */
@Mapper
@Repository
public interface T03_userMapper {
    T03_user findT03_userByTelephone(String telephone);
    void insertT03_user(T03_user t03_user);
    T03_user findT03_userByUid(String uid);
}
