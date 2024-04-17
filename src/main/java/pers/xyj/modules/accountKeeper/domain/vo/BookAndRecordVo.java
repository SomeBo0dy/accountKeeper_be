package pers.xyj.modules.accountKeeper.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.xyj.modules.accountKeeper.domain.entity.Book;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookAndRecordVo {
    private Integer bId;
    private String name;
    private String description;
    private PageVo recordPage;
}
