package pers.xyj.modules.accountKeeper.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBookDto {
    private String name;
    //描绘
    private String description;
}
