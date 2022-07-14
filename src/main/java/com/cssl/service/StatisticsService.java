package com.cssl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cssl.pojo.Statistics;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cssl.pojo.Ticket;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ACER
* @description 针对表【statistics】的数据库操作Service
* @createDate 2022-06-11 14:32:35
*/
public interface StatisticsService extends IService<Statistics> {
    /*
     * 查询这个用户是否在该主题投过票
     * */
    int findCount(Integer uid,  Integer tid);
    int delStaByt_id(QueryWrapper<Ticket> queryWrapper) ;
}
