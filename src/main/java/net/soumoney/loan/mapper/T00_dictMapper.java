package net.soumoney.loan.mapper;

import net.soumoney.loan.dto.T00_dict;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/22.
 */
@Mapper
@Repository
public interface T00_dictMapper {
    List findT00_dictList(T00_dict dict);
}
