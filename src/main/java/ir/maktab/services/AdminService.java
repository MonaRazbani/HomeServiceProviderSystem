package ir.maktab.services;

import ir.maktab.dto.filterDto.UserCategoryDto;
import ir.maktab.dto.modelDtos.AdminDto;
import ir.maktab.dto.modelDtos.roles.UserDto;

import java.util.List;

public interface AdminService {
     void saveAdmin(AdminDto adminDto, String password) ;

     AdminDto loginAdmin(AdminDto adminDto) ;

     List<UserDto> UserDynamicSearch(UserCategoryDto userCategoryDto) ;
}
