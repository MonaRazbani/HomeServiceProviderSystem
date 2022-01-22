package ir.maktab.dto.filterDto;

import lombok.Data;

@Data
public class BasePaginationDto {

    private int pageNumber = 0;
    private int pageSize = 10;

}

