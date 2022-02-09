package ir.maktab.services;

import ir.maktab.data.models.entities.Comment;
import ir.maktab.dto.modelDtos.CommentDto;

import java.util.UUID;

public interface CommentService {
     Comment saveComment(CommentDto commentDto);

     void updateComment(CommentDto commentDto) ;

     void deleteComment(CommentDto commentDto) ;

     Comment findCommentById(long id) ;

     Comment findCommentByIdentificationCode(UUID identificationCode) ;

     long findCommentId(UUID identificationCode);
}
