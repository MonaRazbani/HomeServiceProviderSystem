package ir.maktab.dao;

import ir.maktab.models.entities.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends PagingAndSortingRepository<Comment,Long> {

}
