package services;

import dao.AdminDao;
import dao.UserDao;
import lombok.Data;
import models.entities.Admin;
import models.entities.roles.User;
import models.enums.RoleType;
import validation.ControlInput;

import java.util.List;

@Data
public class AdminService {
    private AdminDao adminDao;
    private UserDao userDao;
    private ControlInput controlInput;

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

    public List<User> dynamicSearch (String firstName , String lastName , String email , RoleType roleType){
      return userDao.filterDynamic(firstName, lastName, email, roleType);

    }
}
