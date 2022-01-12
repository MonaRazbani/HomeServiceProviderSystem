/*
package ir.maktab.dao;

import ir.maktab.models.entities.roles.User;
import ir.maktab.models.enums.RoleType;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import ir.maktab.util.HibernateUtil;

import java.ir.maktab.util.List;

public class UserDao extends HibernateUtil{


    public List<User> filterDynamic(String firstName, String lastName , String email, RoleType roleType) {
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
        if (roleType !=null){
            Criterion userTypeFilter = Restrictions.eq("user.userType", email);
            criteria.add(userTypeFilter);
        }
        List found = criteria.list();
        transaction.commit();
        session.close();

        return found;
    }

}
*/
