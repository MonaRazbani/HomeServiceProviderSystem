package services;

import dao.CommentDao;
import dto.modelDtos.CommentDto;
import exceptions.EditionDenied;
import exceptions.InvalidRate;
import models.entities.Comment;
import models.entities.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import validation.ControlEdition;
import validation.ControlInput;

@Service
public class CommentService {
    private final CommentDao commentDao;
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition ;

    @Autowired
    public CommentService(CommentDao commentDao, ControlInput controlInput, ModelMapper modelMapper, ControlEdition controlEdition) {
        this.commentDao = commentDao;
        this.controlInput = controlInput;
        this.modelMapper = modelMapper;
        this.controlEdition = controlEdition;
    }


    public void saveOrUpdateComment(CommentDto commentDto) {
            if (controlInput.isValidRate(commentDto.getRate())){
                Comment comment = modelMapper.map(commentDto,Comment.class);
                Order order = modelMapper.map(commentDto.getOrderDto(),Order.class);
                comment.setOrder(order);
                commentDao.save(comment);
            }else
                 throw new InvalidRate();
    }

    public void DeleteComment (CommentDto commentDto){
        if (controlEdition.isValidToEdit(commentDto.getOrderDto().getStatus())){
            Comment comment = modelMapper.map(commentDto,Comment.class);
            Order order = modelMapper.map(commentDto.getOrderDto(),Order.class);
            comment.setOrder(order);
            commentDao.delete(comment);

        }else
            throw new EditionDenied() ;
    }


}
