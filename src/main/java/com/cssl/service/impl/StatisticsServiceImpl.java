package com.cssl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cssl.mapper.OptionMapper;
import com.cssl.mapper.TicketMapper;
import com.cssl.pojo.Statistics;
import com.cssl.pojo.Ticket;
import com.cssl.service.StatisticsService;
import com.cssl.mapper.StatisticsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ACER
* @description 针对表【statistics】的数据库操作Service实现
* @createDate 2022-06-11 14:32:35
*/
@Service
public class StatisticsServiceImpl extends ServiceImpl<StatisticsMapper, Statistics>
    implements StatisticsService{
    @Resource
    private StatisticsMapper statisticsMapper;


    @Override
    public int findCount(Integer uid, Integer tid) {
        return super.baseMapper.findCount(uid,tid);
    }

    @Override
    public int delStaByt_id(QueryWrapper<Ticket> queryWrapper) {
        return statisticsMapper.delSta(queryWrapper);
    }
}




