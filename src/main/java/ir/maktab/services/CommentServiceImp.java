package ir.maktab.services;

import ir.maktab.data.dao.CommentDao;
import ir.maktab.data.models.entities.Comment;
import ir.maktab.dto.modelDtos.CommentDto;
import ir.maktab.exceptions.CommentNotFound;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.InvalidRate;
import ir.maktab.validation.ControlEdition;
import ir.maktab.validation.ControlInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentDao commentDao;
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition;

    @Override
    public void saveComment(CommentDto commentDto) {
        if (controlInput.isValidRate(commentDto.getRate())) {
            Comment comment = modelMapper.map(commentDto, Comment.class);
            commentDao.save(comment);
        } else
            throw new InvalidRate();
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        if (controlInput.isValidRate(commentDto.getRate())) {

            Comment comment = modelMapper.map(commentDto, Comment.class);
            long commentId = findCommentId(commentDto.getIdentificationCode());
            comment.setId(commentId);

            commentDao.save(comment);
        } else
            throw new InvalidRate();
    }

    @Override
    public void deleteComment(CommentDto commentDto) {
        if (controlEdition.isValidToEdit(commentDto.getOrder().getStatus())) {

            Comment comment = modelMapper.map(commentDto, Comment.class);
            long commentId = findCommentId(commentDto.getIdentificationCode());
            comment.setId(commentId);

            commentDao.delete(comment);

        } else
            throw new EditionDenied();
    }

    @Override
    public Comment findCommentById(long id) {
        Optional<Comment> comment = commentDao.findById(id);
        if (comment.isPresent())
            return comment.get();
        else
            throw new CommentNotFound();
    }

    @Override
    public Comment findCommentByIdentificationCode(UUID identificationCode) {
        Optional<Comment> comment = commentDao.findByIdentificationCode(identificationCode);
        if (comment.isPresent())
            return comment.get();
        else
            throw new CommentNotFound();
    }

    @Override
    public long findCommentId(UUID identificationCode) {
        Comment comment = findCommentByIdentificationCode(identificationCode);
        return comment.getId();
    }

}
