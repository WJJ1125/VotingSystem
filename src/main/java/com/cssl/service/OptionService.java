package com.cssl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cssl.pojo.Option;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cssl.pojo.Ticket;
import org.springframework.transaction.annotation.Transactional;

/**
* @author ACER
* @description 针对表【option】的数据库操作Service
* @createDate 2022-06-11 14:32:35
*/
@Transactional
public interface OptionService extends IService<Option> {
    int addVote(Option option);
    int addd(Option option);
    int delByt_id(QueryWrapper<Ticket> queryWrapper);
    int up(Option option);
}
