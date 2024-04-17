package pers.xyj.modules.accountKeeper.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("ak_book_user")
public class BookUser {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Long uId;

    private Integer bId;

    private Integer priority;

    public BookUser(Long uId, Integer bId){
        this.uId = uId;
        this.bId = bId;
    }

}

