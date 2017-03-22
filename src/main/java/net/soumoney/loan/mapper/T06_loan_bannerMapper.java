package net.soumoney.loan.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/22.
 */
@Mapper
@Repository
public interface T06_loan_bannerMapper {
    List findT06_loan_bannerList();
}
