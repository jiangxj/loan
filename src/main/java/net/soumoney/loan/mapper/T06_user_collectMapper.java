package net.soumoney.loan.mapper;

import net.soumoney.loan.dto.T06_user_collect;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/22.
 */
@Mapper
@Repository
public interface T06_user_collectMapper {
    List findT06_user_collectListByTelephone(String telephone);

    void insertT06_user_collect(T06_user_collect collect);
}
