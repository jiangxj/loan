package net.soumoney.loan.service;

import net.soumoney.loan.dto.T00_dict;
import net.soumoney.loan.mapper.T00_dictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangxiaojie on 2017/3/25.
 */
@Service
public class DictService {
    @Autowired
    T00_dictMapper t00_dictMapper;
    public List allConditions(String disctype) {
        T00_dict dict = new T00_dict();
        dict.setDisctype(disctype);
        return t00_dictMapper.findT00_dictList(dict);
    }
}
