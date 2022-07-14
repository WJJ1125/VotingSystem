package com.cssl.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName option
 */
@TableName(value ="option")
@Data
public class Option implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer o_id;

    private String o_name;

    private Integer t_id;

    private Integer o_px;


    /*
    * 该选项得票数
    * */
    @TableField(exist = false)
    private Integer piaoCount;
    /*
     * 百分之占比
     * */
    @TableField(exist = false)
    private float Proportion;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}