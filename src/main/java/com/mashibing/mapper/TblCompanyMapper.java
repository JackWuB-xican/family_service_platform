package com.mashibing.mapper;

import com.mashibing.bean.TblCompany;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 企业档案 Mapper 接口
 * </p>
 *
 * @author wu
 * @since 2021-05-13
 */
@Component
public interface TblCompanyMapper extends BaseMapper<TblCompany> {

    public List<TblCompany> selectCompany();
}
