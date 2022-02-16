package ir.maktab.services;

import ir.maktab.dto.filterDto.OrderCategoryDto;
import ir.maktab.dto.filterDto.UserCategoryDto;
import ir.maktab.dto.modelDtos.AdminDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.UserDto;

import java.util.List;

public interface AdminService {

     void saveAdmin(AdminDto adminDto) ;

     AdminDto loginAdmin(AdminDto adminDto) ;

     List<UserDto> userDynamicSearch(UserCategoryDto userCategoryDto) ;

     List<OrderDto> orderDynamicSearch(OrderCategoryDto orderCategoryDto);

     long reportNumOfCustomerOrder(String email);

     long reportNumOfExpertDoneOrder(String email);
}
