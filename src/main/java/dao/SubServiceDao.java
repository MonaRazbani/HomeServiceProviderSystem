package dao;

import models.entities.SubService;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import util.HibernateUtil;

import javax.persistence.NoResultException;
import java.util.List;

public interface SubServiceDao extends PagingAndSortingRepository<SubService,Long> {
    List<SubService> findByName (String name);



}
