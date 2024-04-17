package pers.xyj.modules.accountKeeper.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.xyj.modules.accountKeeper.domain.entity.Record;
import pers.xyj.modules.accountKeeper.domain.vo.RecordVo;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {

    @Select("SELECT sum(amount) " +
            "FROM ak_record " +
            "WHERE b_id = #{id}")
    Double getSumByBookId(@Param("id") Integer id);

    @Select(" SELECT r.id, r.amount, t.id as tId, t.name as typeName, t.img_url, r.description " +
            " FROM ak_record r " +
            " LEFT JOIN ak_type t ON t.id = r.t_id " +
            " ${ew.customSqlSegment} ")
    IPage<RecordVo> getTopBookRecords(IPage<RecordVo> page, @Param(Constants.WRAPPER) LambdaQueryWrapper<Record> queryWrapper);
}

