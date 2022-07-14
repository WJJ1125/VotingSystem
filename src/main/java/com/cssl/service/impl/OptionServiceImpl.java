package com.cssl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cssl.mapper.TicketMapper;
import com.cssl.pojo.Option;
import com.cssl.pojo.Ticket;
import com.cssl.service.OptionService;
import com.cssl.mapper.OptionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author ACER
* @description 针对表【option】的数据库操作Service实现
* @createDate 2022-06-11 14:32:35
*/
@Transactional
@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option>
    implements OptionService{


    @Resource
    private OptionMapper optionMapper;
    @Override
    public int delByt_id(QueryWrapper<Ticket> queryWrapper) {
        return optionMapper.delOption(queryWrapper);
    }
    @Override
    public int up(Option option) {
        return optionMapper.update(option);
    }
    @Resource
    private TicketMapper ticketMapper;
    @Override
    public int addVote(Option option) {
        return 0;
    }

    @Override
    public int addd(Option option) {
        return super.baseMapper.addd(option);
    }
}




