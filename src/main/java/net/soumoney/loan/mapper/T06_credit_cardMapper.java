package net.soumoney.loan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/22.
 */
@Mapper
@Repository
public interface T06_credit_cardMapper {
    List findT06_credit_cardList();
}
