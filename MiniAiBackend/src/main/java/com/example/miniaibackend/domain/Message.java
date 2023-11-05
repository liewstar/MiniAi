package com.example.miniaibackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * @TableName message
 */
@TableName(value ="message")
@Data
@AllArgsConstructor
public class Message implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer conversationId;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private Date timestamp;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}