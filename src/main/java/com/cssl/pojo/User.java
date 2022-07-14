package com.cssl.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer u_id;

    /**
     * 
     */
    private String u_name;

    /**
     * 
     */
    private String u_password;

    /**
     * 
     */
    private Integer u_type;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}