package com.cssl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cssl.mapper.OptionMapper;
import com.cssl.mapper.StatisticsMapper;
import com.cssl.pojo.Ticket;
import com.cssl.service.TicketService;
import com.cssl.mapper.TicketMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ACER
* @description 针对表【ticket】的数据库操作Service实现
* @createDate 2022-06-11 14:32:35
*/
@Transactional
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket>
    implements TicketService{
    @Resource
    private OptionMapper optionMapper;
    @Resource
    private StatisticsMapper statisticsMapper;
    @Override
    public int delVote(QueryWrapper<Ticket> qw) {
        int a= statisticsMapper.delSta(qw);
        int b= optionMapper.delOption(qw);
        int c= super.baseMapper.delVote(qw);
        if((a+b+c)>=1){
            return 0;
        }
        return 1;
    }

    @Override
    public IPage<Ticket> findTicks(Page<Ticket> page, QueryWrapper<Ticket> qw) {
        return super.baseMapper.findTicks(page,qw);
    }
}




