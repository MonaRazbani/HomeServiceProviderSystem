package ir.maktab.services;

import ir.maktab.data.dao.AdminDao;
import ir.maktab.data.dao.UserDao;
import ir.maktab.data.dao.UserSpecifications;
import ir.maktab.dto.filterDto.UserCategoryDto;
import ir.maktab.dto.modelDtos.AdminDto;
import ir.maktab.data.models.entities.Admin;
import ir.maktab.data.models.entities.SubService;
import ir.maktab.data.models.entities.roles.User;
import ir.maktab.data.models.enums.RoleType;
import ir.maktab.dto.modelDtos.roles.UserDto;
import ir.maktab.validation.ControlInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminDao adminDao;
    private final UserDao userDao;
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;


    public void addAdmin(AdminDto adminDto, String password) {
        if (controlInput.isValidPassword(password)) {
            Admin admin = new Admin();
            admin.setUsername(adminDto.getUsername());
            admin.setPassword(password);
            adminDao.save(admin);
        }
    }

    public List<UserDto> dynamicSearch(UserCategoryDto userCategoryDto) {
        Sort sort = Sort.by("lastName").and(Sort.by("firstName"));
        Pageable pageable = PageRequest.of(userCategoryDto.getPageNumber(), userCategoryDto.getPageSize(), sort);


        Specification<User> specification = UserSpecifications.userFilter(userCategoryDto);
        return  userDao
                .findAll(specification, pageable)
                .stream()
                .map(user -> modelMapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }
}
