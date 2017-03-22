package net.soumoney.loan.mapper;

import net.soumoney.loan.dto.T06_loan_product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/21.
 */
@Mapper
@Repository
public interface T06_loan_productMapper {
    public List<T06_loan_product> findT06_loan_productList(T06_loan_product loan_product);
}
