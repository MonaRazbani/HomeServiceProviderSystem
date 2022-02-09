package ir.maktab.services;

import ir.maktab.data.dao.CommentDao;
import ir.maktab.data.models.entities.Comment;
import ir.maktab.data.models.entities.Order;
import ir.maktab.dto.mapper.CommentMapper;
import ir.maktab.dto.modelDtos.CommentDto;
import ir.maktab.exceptions.CommentNotFound;
import ir.maktab.exceptions.EditionDenied;
import ir.maktab.exceptions.InvalidRate;
import ir.maktab.validation.ControlEdition;
import ir.maktab.validation.ControlInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentDao commentDao;
    private final CommentMapper commentMapper;
    private final ControlEdition controlEdition;

    @Override
    public Comment  saveComment(CommentDto commentDto) {
        Comment comment = commentMapper.toComment(commentDto);
        comment.setIdentificationCode(UUID.randomUUID());
       return commentDao.save(comment);
    }

    @Override
    public void updateComment(CommentDto commentDto) {


            Comment comment = commentMapper.toComment(commentDto);
            long commentId = findCommentId(commentDto.getIdentificationCode());

            comment.setId(commentId);

            commentDao.save(comment);
    }

    @Override
    public void deleteComment(CommentDto commentDto) {

            Comment comment = commentMapper.toComment(commentDto);
            long commentId = findCommentId(commentDto.getIdentificationCode());
            comment.setId(commentId);

            commentDao.delete(comment);

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
