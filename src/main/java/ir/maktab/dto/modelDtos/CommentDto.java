package ir.maktab.dto.modelDtos;


import lombok.Data;

@Data
public class CommentDto {

    private double rate ;
    private String comment ;
    private OrderDto order;
}
