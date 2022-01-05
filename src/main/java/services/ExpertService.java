package services;

import dao.ExpertDao;
import lombok.Data;
import models.entities.Service;
import models.entities.roles.Expert;
import models.enums.Gender;
import models.enums.UserStatus;
import validation.ControlInput;

import javax.persistence.NoResultException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

@Data
public class ExpertService {
    private ExpertDao expertDao;
    private ControlInput controlInput;

    private static ExpertService expertService;

    public static ExpertService instance() {

        if (expertService == null)
            expertService = new ExpertService();

        return expertService;
    }

    public void AddExpert(String expertInfo, File file) {
        try {
            String[] expertInfoSplit = expertInfo.split(",");
            String firstName = expertInfoSplit[0];
            String lastName = expertInfoSplit[1];
            String email = expertInfoSplit[2];
            String password = expertInfoSplit[3];
            Gender gender = Gender.valueOf(expertInfoSplit[4]);
            if (controlInput.isValidName(firstName)
                    && controlInput.isValidName(lastName)
                    && controlInput.isValidEmail(email)
                    && controlInput.isValidPassword(password)
                    && controlInput.isValidPhoto(file)) {
                byte[] imageBytes = initializePhoto(file);
                Expert expert = Expert.ExpertBuilder.anExpert()
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withEmail(email)
                        .withPassword(password)
                        .withGender(gender)
                        .withStatus(UserStatus.NEW)
                        .withRate(0)
                        .withPhoto(imageBytes)
                        .build();

                expertDao.save(expert);
            }

        } catch (Throwable throwable) {
            System.out.println(throwable.getMessage());
        }
    }

    public Expert findExpertByEmail(String email) {
        Expert expert = null;
        if (controlInput.isValidEmail(email)) {
            try {
                expert = expertDao.findByEmail(email);

            } catch (NoResultException noResultException) {
                System.out.println("Customer not found!");
            }
        }
        return expert;
    }

    public void changePasswordForExpert(Expert expert, String currentPassword, String newPassword) {
        if (expert.getPassword().equals(currentPassword)) {
            expert.setPassword(newPassword);
            expertDao.update(expert);
            System.out.println("done");
        } else System.out.println("changing password fail because you current password not correct ");

    }

    public byte[] initializePhoto(File file) {
        byte[] imageData = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(imageData);
            fileInputStream.close();

        } catch (IOException e) {
            e.getMessage();
        }
        return imageData;
    }

    public void addServiceToExpertServices(Service service , Expert expert){
        Set<Service> allExpertServices = expertDao.getAllExpertServices(expert,service);
        expert.setServices(allExpertServices);
        expertDao.update(expert);

    }


}

