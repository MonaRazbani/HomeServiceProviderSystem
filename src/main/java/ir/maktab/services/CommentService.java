package ir.maktab.services;

import ir.maktab.dao.CommentDao;
import ir.maktab.dto.mappingMethod.MapperObject;
import ir.maktab.dto.modelDtos.CommentDto;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.InvalidRate;
import ir.maktab.models.entities.Comment;
import ir.maktab.models.entities.Order;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ir.maktab.validation.ControlEdition;
import ir.maktab.validation.ControlInput;

@Service
public class CommentService {
    private final CommentDao commentDao;
    private final ControlInput controlInput;
    private final MapperObject mapperObject ;
    private final ControlEdition controlEdition ;

    @Autowired
    public CommentService(CommentDao commentDao, ControlInput controlInput,MapperObject mapperObject, ControlEdition controlEdition) {
        this.commentDao = commentDao;
        this.controlInput = controlInput;
        this.mapperObject = mapperObject;
        this.controlEdition = controlEdition;
    }


    public void saveOrUpdateComment(CommentDto commentDto) {
            if (controlInput.isValidRate(commentDto.getRate())){
                Comment comment = mapperObject.commentDtoMapToComment(commentDto);
                commentDao.save(comment);
            }else
                 throw new InvalidRate();
    }

    public void DeleteComment (CommentDto commentDto){
        if (controlEdition.isValidToEdit(commentDto.getOrder().getStatus())){
            Comment comment = mapperObject.commentDtoMapToComment(commentDto);
            commentDao.delete(comment);

        }else
            throw new EditionDenied() ;
    }


}
