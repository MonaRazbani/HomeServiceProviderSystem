package ir.maktab.data.dao;

import ir.maktab.data.models.entities.SubService;
import ir.maktab.data.models.entities.roles.User;
import ir.maktab.data.models.enums.RoleType;
import ir.maktab.dto.filterDto.UserCategoryDto;
import ir.maktab.exceptions.BadFilterSearching;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface UserSpecifications {
    static Specification<User> userFilter(UserCategoryDto userCategoryDto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            CriteriaQuery<User> resultCriteria = criteriaBuilder.createQuery(User.class);
            Join<User, SubService> subServices = root.join("subServices");
            List<Predicate> predicates = new ArrayList<>();

            if (userCategoryDto.getRoleType() == RoleType.CUSTOMER && userCategoryDto.getSubServiceName() != null) {
                throw new BadFilterSearching();
            }

            if (userCategoryDto.getFirstName() != null) {
                Predicate firstNameFilter = criteriaBuilder.equal(root.get("firstName"), userCategoryDto.getFirstName());
                predicates.add(firstNameFilter);

            }
            if (userCategoryDto.getLastName() != null) {
                Predicate lastNameFilter = criteriaBuilder.equal(root.get("lastName"), userCategoryDto.getLastName());
                predicates.add(lastNameFilter);
            }
            if (userCategoryDto.getEmail() != null) {
                Predicate emailFilter = criteriaBuilder.equal(root.get("email"), userCategoryDto.getEmail());
                predicates.add(emailFilter);
            }
            if (userCategoryDto.getRoleType() != null) {
                Predicate rolTypeFilter = criteriaBuilder.equal(root.get("rolType"), userCategoryDto.getRoleType());
                predicates.add(rolTypeFilter);
            }
            if (userCategoryDto.getSubServiceName() != null) {
                Predicate subServiceFilter = criteriaBuilder.in(subServices.get("name")).value(userCategoryDto.getSubServiceName());
                predicates.add(subServiceFilter);

            }
            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };
    }
}
