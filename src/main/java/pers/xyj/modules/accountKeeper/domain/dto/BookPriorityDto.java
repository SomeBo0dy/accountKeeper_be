package pers.xyj.modules.accountKeeper.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookPriorityDto {
    private Long id;
    private Integer priority;
}
