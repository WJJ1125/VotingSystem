package com.cssl.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName ticket
 */
@TableName(value ="ticket")
@Data
public class Ticket implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer t_id;

    /**
     * 
     */
    private String t_title;

    /**
     * 
     */
    private Integer t_type;
    /*
    * 选项数
    * */
    @TableField(exist = false)
    private Integer xXCount;
    /*
    * 参与投票的用户数
    * */
    @TableField(exist = false)
    private Integer userCount;
    /*
    * 选项list
    * */
    @TableField(exist = false)
    private List<Option> optionList;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}