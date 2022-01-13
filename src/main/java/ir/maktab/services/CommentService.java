package ir.maktab.services;

import ir.maktab.dao.CommentDao;
import ir.maktab.dto.modelDtos.CommentDto;
import ir.maktab.exceptions.CommentNotFound;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.InvalidRate;
import ir.maktab.models.entities.Comment;
import ir.maktab.validation.ControlEdition;
import ir.maktab.validation.ControlInput;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {
    private final CommentDao commentDao;
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition;

    @Autowired
    public CommentService(CommentDao commentDao, ControlInput controlInput, ModelMapper modelMapper, ControlEdition controlEdition) {
        this.commentDao = commentDao;
        this.controlInput = controlInput;
        this.modelMapper = modelMapper;
        this.controlEdition = controlEdition;
    }


    public void saveOrUpdateComment(CommentDto commentDto) {
        if (controlInput.isValidRate(commentDto.getRate())) {
            Comment comment = modelMapper.map(commentDto, Comment.class);
            commentDao.save(comment);
        } else
            throw new InvalidRate();
    }

    public void deleteComment(CommentDto commentDto) {
        if (controlEdition.isValidToEdit(commentDto.getOrder().getStatus())) {
            Comment comment = modelMapper.map(commentDto, Comment.class);
            commentDao.delete(comment);

        } else
            throw new EditionDenied();
    }

    public CommentDto findCommentDtoById(long id) {
        Optional<Comment> comment = commentDao.findById(id);
        if (comment.isPresent())
            return modelMapper.map(comment.get(), CommentDto.class);
        else
            throw new CommentNotFound();
    }

    public Comment findCommentById(long id) {
        Optional<Comment> comment = commentDao.findById(id);
        if (comment.isPresent())
            return comment.get();
        else
            throw new CommentNotFound();
    }


}
