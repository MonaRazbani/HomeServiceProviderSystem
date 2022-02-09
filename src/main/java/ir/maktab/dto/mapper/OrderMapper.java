package ir.maktab.dto.mapper;

import ir.maktab.data.models.entities.Address;
import ir.maktab.data.models.entities.Comment;
import ir.maktab.data.models.entities.Order;
import ir.maktab.data.models.entities.roles.Customer;
import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.dto.modelDtos.AddressDto;
import ir.maktab.dto.modelDtos.CommentDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderMapper {
    private final ModelMapper modelMapper;
    public final CommentMapper commentMapper;



    public Order toOrder(OrderDto orderDto) {
        Order order = Order.builder()
                .customer(modelMapper.map(orderDto.getCustomer(), Customer.class))
                .subService(SubServiceMapper.toSubService(orderDto.getSubService()))
                .explanation(orderDto.getExplanation())
                .suggestedPrice(orderDto.getSuggestedPrice())
                .build();
        if (orderDto.getIdentificationCode() != null)
            order.setIdentificationCode(orderDto.getIdentificationCode());

        if (orderDto.getExpert() != null)
            order.setExpert(modelMapper.map(orderDto.getExpert(), Expert.class));

        if (orderDto.getComment() != null)
            order.setComment(commentMapper.toComment(orderDto.getComment()));

        if (orderDto.getAddress() != null)
            order.setAddress(modelMapper.map(orderDto.getAddress(), Address.class));

        if (orderDto.getStatus() != null)
            order.setStatus(orderDto.getStatus());

        if (orderDto.getPerformedOrder() != null)
            order.setPerformedOrder(orderDto.getPerformedOrder());

        return order;
    }

    public OrderDto toOrderDto(Order order) {
        OrderDto orderDto = OrderDto.builder()
                .identificationCode(order.getIdentificationCode())
                .customer(modelMapper.map(order.getCustomer(), CustomerDto.class))
                .explanation(order.getExplanation())
                .suggestedPrice(order.getSuggestedPrice())
                .address(modelMapper.map(order.getAddress(), AddressDto.class))
                .subService(SubServiceMapper.toSubServiceDto(order.getSubService()))
                .build();

        if (order.getExpert() != null)
            orderDto.setExpert(modelMapper.map(order.getExpert(), ExpertDto.class));

        if (order.getComment() != null)
            orderDto.setComment(commentMapper.toCommentDto(order.getComment()));

        if (order.getStatus() != null)
            orderDto.setStatus(order.getStatus());

        if (order.getPerformedOrder() != null)
            orderDto.setPerformedOrder(order.getPerformedOrder());

        return orderDto;

    }

}
