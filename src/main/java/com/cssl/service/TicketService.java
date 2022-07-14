package com.cssl.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cssl.pojo.Ticket;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author ACER
* @description 针对表【ticket】的数据库操作Service
* @createDate 2022-06-11 14:32:35
*/
public interface TicketService extends IService<Ticket> {
   IPage<Ticket> findTicks(Page<Ticket> page, QueryWrapper<Ticket> qw);
   int delVote(QueryWrapper<Ticket> qw);

}
