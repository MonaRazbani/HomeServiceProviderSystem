package ir.maktab.data.dao;

import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.entities.ServiceCategory;
import ir.maktab.data.models.entities.SubService;
import ir.maktab.dto.filterDto.OrderCategoryDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface OrderSpecifications {
    static Specification<Order> orderFilter(OrderCategoryDto orderCategoryDto) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            CriteriaQuery<Order> resultCriteria = criteriaBuilder.createQuery(Order.class);

            Join<Order, SubService> subService = root.join("subService");
            Join<SubService, ServiceCategory> serviceCategory = root.join("serviceCategory");

            List<Predicate> predicates = new ArrayList<>();


            if (orderCategoryDto.getStartDate() != null && orderCategoryDto.getEndDate() != null) {
                Predicate dateFilter = criteriaBuilder.between(
                        root.get("creation"), orderCategoryDto.getStartDate(), orderCategoryDto.getEndDate());
                predicates.add(dateFilter);
            }
            if (orderCategoryDto.getOrderStatus() != null) {
                Predicate orderStatusFilter = criteriaBuilder.equal(root.get("status"), orderCategoryDto.getOrderStatus());
                predicates.add(orderStatusFilter);
            }
            if (orderCategoryDto.getServiceCategoryName() != null) {
                Predicate serviceCategoryFilter = criteriaBuilder.equal(serviceCategory.get("name"), orderCategoryDto.getServiceCategoryName());
                predicates.add(serviceCategoryFilter);
            }
            if (orderCategoryDto.getSubServiceName() != null) {
                Predicate rolTypeFilter = criteriaBuilder.equal(subService.get("name"), orderCategoryDto.getSubServiceName());
                predicates.add(rolTypeFilter);
            }
            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };
    }
}
