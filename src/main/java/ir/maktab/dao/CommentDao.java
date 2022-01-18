package ir.maktab.dao;

import ir.maktab.models.entities.Address;
import ir.maktab.models.entities.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CommentDao extends PagingAndSortingRepository<Comment,Long> {
    Optional<Comment> findByIdentificationCode(UUID identificationCode);
}
