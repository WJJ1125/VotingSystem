package com.cssl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cssl.pojo.Option;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cssl.pojo.Ticket;
import org.apache.ibatis.annotations.Param;

/**
* @author ACER
* @description 针对表【option】的数据库操作Mapper
* @createDate 2022-06-11 14:32:35
* @Entity com.cssl.pojo.Option
*/
public interface OptionMapper extends BaseMapper<Option> {
    int addd(Option option);
    int delOption(@Param("ew") QueryWrapper<Ticket> qw);
    int update(Option option);
}




