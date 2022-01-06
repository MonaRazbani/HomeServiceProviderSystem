package services;

import dao.CommentDao;
import dao.InstructionDao;
import exceptions.InvalidRate;
import lombok.Data;
import models.entities.Comment;
import models.entities.Instruction;
import validation.ControlInput;

@Data
public class CommentService {
    private InstructionDao instructionDao;
    private CommentDao commentDao;
    private ControlInput controlInput;
    private static CommentService commentService;

    public static CommentService instance() {

        if (commentService == null)
            commentService = new CommentService();
        return commentService;
    }

    public void addNewComment(Instruction instruction, int rate, String comment) {
        try {
            if (controlInput.isValidRate(rate)) {
                Comment comment1 = new Comment();
                comment1.setRate(rate);
                comment1.setInstruction(instruction);
                comment1.setComment(comment);
                commentDao.save(comment1);
            }
        } catch (InvalidRate e) {
            System.out.println(e.getMessage());
        }
    }

    public void addNewComment(Instruction instruction, int rate) {
        try {
            if (controlInput.isValidRate(rate)) {
                Comment comment1 = new Comment();
                comment1.setRate(rate);
                comment1.setInstruction(instruction);
                commentDao.save(comment1);
            }
        } catch (InvalidRate e) {
            System.out.println(e.getMessage());
        }
    }

}
