package com.cssl.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName statistics
 */
@TableName(value ="statistics")
@Data
public class Statistics implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer s_id;

    /**
     * 
     */
    private Integer u_id;

    /**
     * 
     */
    private Integer o_id;

    /**
     * 
     */
    private Integer t_id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}