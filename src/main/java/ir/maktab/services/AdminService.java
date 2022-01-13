package ir.maktab.services;

import ir.maktab.dao.AdminDao;
import ir.maktab.dao.UserDao;
import ir.maktab.dto.modelDtos.AdminDto;
import ir.maktab.models.entities.Admin;
import ir.maktab.models.entities.SubService;
import ir.maktab.models.entities.roles.User;
import ir.maktab.models.enums.RoleType;
import ir.maktab.validation.ControlInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminDao adminDao;
    private final UserDao userDao;
    private final ControlInput controlInput;

    @Autowired
    public AdminService(AdminDao adminDao, UserDao userDao, ControlInput controlInput) {
        this.adminDao = adminDao;
        this.userDao = userDao;
        this.controlInput = controlInput;
    }


    public void addAdmin(AdminDto adminDto, String password) {
        if (controlInput.isValidPassword(password)) {
            Admin admin = new Admin();
            admin.setUsername(adminDto.getUsername());
            admin.setPassword(password);
            adminDao.save(admin);
        }
    }

    public List<User> dynamicSearch(String firstName, String lastName, String email, RoleType roleType, SubService subService) {
        return userDao.findAll(UserDao.filterDynamic(firstName, lastName, email, roleType, subService));
    }
}
