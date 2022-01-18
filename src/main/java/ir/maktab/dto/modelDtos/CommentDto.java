package ir.maktab.dto.modelDtos;


import lombok.Data;

import java.util.UUID;

@Data
public class CommentDto {

    private UUID identificationCode;
    private double rate ;
    private String comment ;
    private OrderDto order;
}
