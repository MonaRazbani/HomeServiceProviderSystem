package dao;

import models.entities.roles.Customer;
import models.entities.roles.User;
import models.enums.UserType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;

import java.util.List;

public class UserDao extends HibernateUtil{
        private static UserDao userDao;

        public static UserDao instance() {

            if (userDao == null)
                userDao = new UserDao();

            return userDao;
        }

    public List<User> filterDynamic(String firstName, String lastName , String email, UserType userType) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(User.class, "user");
        if (firstName !=null ) {
            Criterion firstNameFilter = Restrictions.eq("user.firstName", firstName);
            criteria.add(firstNameFilter);
        }
        if (lastName!= null) {
            Criterion lastNameFilter = Restrictions.eq("user.lastName", lastName);
            criteria.add(lastNameFilter);
        }
        if (email!= null) {
            Criterion emailFilter = Restrictions.eq("user.email", email);
            criteria.add(emailFilter);
        }
        if (userType!=null){
            Criterion userTypeFilter = Restrictions.eq("user.userType", email);
            criteria.add(userTypeFilter);
        }
        List found = criteria.list();
        transaction.commit();
        session.close();

        return found;
    }

}
