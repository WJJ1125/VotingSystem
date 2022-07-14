package com.cssl.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cssl.pojo.Ticket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author ACER
* @description 针对表【ticket】的数据库操作Mapper
* @createDate 2022-06-11 14:32:35
* @Entity com.cssl.pojo.Ticket
*/
public interface TicketMapper extends BaseMapper<Ticket> {
    IPage<Ticket> findTicks(Page<Ticket> page,@Param("ew") QueryWrapper<Ticket> qw);
    /*
     * 删除主题和旗下选项
     * */
    int delVote(@Param("ew") QueryWrapper<Ticket> qw);
}




