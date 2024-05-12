package pers.xyj.modules.accountKeeper.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Type)表实体类
 *
 * @author makejava
 * @since 2024-04-16 13:02:31
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ak_type")
public class Type {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer isIncome;

    private String name;

    private String imgUrl;

}