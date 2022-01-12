/*
package ir.maktab.services;

import ir.maktab.dao.AdminDao;
*/
/*import ir.maktab.dao.UserDao;*//*

import lombok.Data;
import ir.maktab.models.entities.Admin;
import ir.maktab.models.entities.roles.User;
import ir.maktab.models.enums.RoleType;
import ir.maktab.validation.ControlInput;

import java.ir.maktab.util.List;

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
*/
