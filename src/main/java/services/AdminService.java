package services;

import dao.AdminDao;
import dao.CustomerDao;
import dao.ExpertDao;
import dao.UserDao;
import lombok.Data;
import models.entities.roles.Admin;
import models.entities.roles.Customer;
import models.entities.roles.Expert;
import models.entities.roles.User;
import models.enums.UserType;
import validation.ControlInput;

import java.util.ArrayList;
import java.util.List;

@Data
public class AdminService {
    private AdminDao adminDao;
    private UserDao userDao;
    private ControlInput controlInput;
    private static AdminService adminService;

    public static AdminService instance() {

        if (adminService == null)
            adminService = new AdminService();
        return adminService;
    }

    public void addAdmin (String adminInfo ) {
        String[] splitAdminInfo = adminInfo.split(",");
        String username = splitAdminInfo[0];
        String password = splitAdminInfo[1];
        try {
            if (controlInput.isValidPassword(password)) {
                Admin admin = new Admin();
                admin.setUsername(username);
                admin.setPassword(password);
                adminDao.save(admin);
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> dynamicSearch (String firstName , String lastName , String email , UserType userType){
      return userDao.filterDynamic(firstName, lastName, email, userType);

    }
}
