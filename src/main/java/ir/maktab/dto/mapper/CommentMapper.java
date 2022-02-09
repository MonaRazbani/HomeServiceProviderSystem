package ir.maktab.dto.mapper;

import ir.maktab.data.models.entities.Comment;
import ir.maktab.dto.modelDtos.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentMapper {


    public Comment toComment(CommentDto commentDto) {
        Comment comment = Comment.builder()
                .rate(commentDto.getRate())
                .build();
        if (commentDto.getIdentificationCode()!=null)
            comment.setIdentificationCode(commentDto.getIdentificationCode());
        if (commentDto.getComment() != null)
            comment.setComment(commentDto.getComment());

        return comment;
    }

    public CommentDto toCommentDto(Comment comment) {
        CommentDto commentDto = CommentDto.builder()
                .identificationCode(comment.getIdentificationCode())
                .rate(comment.getRate())
                .build();

        if (comment.getComment() != null)
            commentDto.setComment(comment.getComment());

        return commentDto;

    }
}
