package net.soumoney.loan.mapper;

import net.soumoney.loan.dto.T06_user_apply;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/22.
 */
@Mapper
@Repository
public interface T06_user_applyMapper {
    List findT06_user_applyListByTelephone(String telephone);

    void insertT06_user_apply(T06_user_apply apply);
}
