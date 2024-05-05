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
 * (ShareCode)表实体类
 *
 * @author makejava
 * @since 2024-05-05 00:27:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ak_share_code")
public class ShareCode {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer bId;

    private String shareCode;

    private Integer sharedCount;

}
