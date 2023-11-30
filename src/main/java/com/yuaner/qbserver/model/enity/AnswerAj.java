package com.yuaner.qbserver.model.enity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("ajanswer")
@AllArgsConstructor
@NoArgsConstructor
public class AnswerAj {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 问题
     */
    @TableField("question")
    private String question;
    /**
     * 回答
     */
    @TableField("answer")
    private String answer;
}
