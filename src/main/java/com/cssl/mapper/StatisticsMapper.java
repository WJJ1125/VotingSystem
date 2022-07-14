package com.cssl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cssl.pojo.Statistics;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cssl.pojo.Ticket;
import org.apache.ibatis.annotations.Param;

/**
* @author ACER
* @description 针对表【statistics】的数据库操作Mapper
* @createDate 2022-06-11 14:32:35
* @Entity com.cssl.pojo.Statistics
*/
public interface StatisticsMapper extends BaseMapper<Statistics> {
    /*
    * 查询这个用户是否在该主题投过票
    * */
    int findCount(@Param("uid") Integer uid, @Param("tid") Integer tid);
    int delSta(@Param("ew") QueryWrapper<Ticket> qw);
}




