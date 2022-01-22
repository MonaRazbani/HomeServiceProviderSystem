package ir.maktab.data.dao;

import ir.maktab.data.models.entities.SubService;
import ir.maktab.data.models.entities.roles.User;
import ir.maktab.data.models.enums.RoleType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {


    static Specification<User> filterDynamic(String firstName, String lastName, String email, RoleType roleType, SubService subService) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (firstName != null) {
                Predicate firstNameFilter = criteriaBuilder.equal(root.get("firstName"), firstName);
                predicates.add(firstNameFilter);

            }
            if (lastName != null) {
                Predicate lastNameFilter = criteriaBuilder.equal(root.get("lastName"), lastName);
                predicates.add(lastNameFilter);
            }
            if (email != null) {
                Predicate emailFilter = criteriaBuilder.equal(root.get("email"), email);
                predicates.add(emailFilter);
            }
            if (roleType != null) {
                Predicate rolTypeFilter = criteriaBuilder.equal(root.get("rolType"), roleType);
                predicates.add(rolTypeFilter);

            }
            if (subService != null) {
                Predicate subServiceFilter = criteriaBuilder.equal(root.get("subService"), subService);
                predicates.add(subServiceFilter);

            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}