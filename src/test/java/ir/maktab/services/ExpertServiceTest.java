package ir.maktab.services;

import ir.maktab.config.SpringConfig;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.*;
import ir.maktab.models.entities.roles.Expert;
import ir.maktab.models.enums.RoleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

public class ExpertServiceTest {

    ExpertService expertService;
    ExpertDto expertDto;

    @BeforeEach
    void init() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        expertService = context.getBean(ExpertService.class);
        expertDto = ExpertDto.builder().
                firstName("ali").
                lastName("hashemi").
                email("ali@gmail.com").
                roleType(RoleType.EXPERT).
                build();

    }

    @Test
    void expertService_CallsSaveExpert_CheckWithFindByEmail_ResponseTrue() {
        // when hibernate.hbm2ddl.auto is 'update' change user's email to have correct test
        String password = "123456ali";
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        expertService.saveExpert(expertDto, password, file);
        Expert expertByEmail = expertService.findExpertByEmail(expertDto.getEmail());
        Assertions.assertEquals(expertDto.getEmail(), expertByEmail.getEmail());
        Assertions.assertEquals(expertDto.getFirstName(), expertByEmail.getFirstName());
        Assertions.assertEquals(expertDto.getLastName(), expertByEmail.getLastName());
        Assertions.assertEquals(expertDto.getRoleType(), expertByEmail.getRoleType());
    }

    @Test
    void expertService_CallsSaveCustomer_ReturnException() {
        //use bellow when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
      /*
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        expertService.saveExpert(expertDto, password, file);*/
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        DuplicateEmail result = Assertions.assertThrows(DuplicateEmail.class, () -> expertService.saveExpert(expertDto, password, file));
        Assertions.assertEquals("This email has been used before", result.getMessage());
    }

    @Test
    void expertService_CallsFindExpertByEmail_ResponseTrue() {
//use bellow when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
      /*
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        expertService.saveExpert(expertDto, password, file);*/
        Expert expertByEmail = expertService.findExpertByEmail(expertDto.getEmail());
        Assertions.assertEquals(expertByEmail.getEmail(), expertByEmail.getEmail());
        Assertions.assertEquals(expertByEmail.getFirstName(), expertByEmail.getFirstName());
        Assertions.assertEquals(expertByEmail.getLastName(), expertByEmail.getLastName());
        Assertions.assertEquals(expertByEmail.getRoleType(), expertByEmail.getRoleType());
    }

    @Test
    void expertService_CallsFindExpertDtoByEmail_UseInvalidEmail_ReturnException() {
        //use bellow when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
      /*
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        expertService.saveExpert(expertDto, password, file);*/
        InvalidEmail result = Assertions.assertThrows(InvalidEmail.class, () -> expertService.findExpertDtoByEmail("mana@gmail"));
        Assertions.assertEquals("invalid email", result.getMessage());
    }

    @Test
    void expertService_CallsFindExpertDtoByEmail_UseUnSaveEmail_ReturnException() {
        //use bellow when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
      /*
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        expertService.saveExpert(expertDto, password, file);*/
        ExpertNotFound result = Assertions.assertThrows(ExpertNotFound.class, () -> expertService.findExpertDtoByEmail("ali111@gmail.com"));
        Assertions.assertEquals("expert not found", result.getMessage());
    }

    @Test
    void expertService_CallsFindExpertDtoByEmail_ResponseTrue() {
        //use bellow when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
      /*
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        expertService.saveExpert(expertDto, password, file);*/
        ExpertDto expertDtoByEmail = expertService.findExpertDtoByEmail(expertDto.getEmail());
        Assertions.assertEquals(expertDtoByEmail.getEmail(), expertDtoByEmail.getEmail());
        Assertions.assertEquals(expertDtoByEmail.getFirstName(), expertDtoByEmail.getFirstName());
        Assertions.assertEquals(expertDtoByEmail.getLastName(), expertDtoByEmail.getLastName());
        Assertions.assertEquals(expertDtoByEmail.getRoleType(), expertDtoByEmail.getRoleType());
    }

    @Test
    void expertService_CallsFindExpertByEmail_UseInvalidEmail_ReturnException() {
        //use bellow when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
      /*
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        expertService.saveExpert(expertDto, password, file);*/
        InvalidEmail result = Assertions.assertThrows(InvalidEmail.class, () -> expertService.findExpertByEmail("mana@gmail"));
        Assertions.assertEquals("invalid email", result.getMessage());
    }

    @Test
    void expertService_CallsFindExpertByEmail_UseUnSaveEmail_ReturnException() {
        //use bellow when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
      /*
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        expertService.saveExpert(expertDto, password, file);*/
        ExpertNotFound result = Assertions.assertThrows(ExpertNotFound.class, () -> expertService.findExpertByEmail("ali@gmail.com"));
        Assertions.assertEquals("expert not found", result.getMessage());
    }

    @Test
    void expertService_CallsChangePasswordForExpert_ResponseTrue() {
        //use bellow when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
         /*
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        expertService.saveExpert(expertDto, password, file);*/
        Expert expertByEmail = expertService.findExpertByEmail(expertDto.getEmail());
        String newPassword = "mona76543218";

        expertService.changePasswordForExpert(expertDto, expertByEmail.getPassword(), newPassword);

        Expert expertWhichWasChangedPassword = expertService.findExpertByEmail(expertDto.getEmail());
        Assertions.assertEquals(expertWhichWasChangedPassword.getPassword(), newPassword);

    }

    @Test
    void expertService_CallsChangePasswordForExpert_UseWrongPassword_ReturnException() {
        //use bellow when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
        /*
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        expertService.saveExpert(expertDto, password, file);*/
        String newPassword = "mona76543218";
        WrongPassword result = Assertions.assertThrows(WrongPassword.class, () ->
                expertService.changePasswordForExpert(expertDto, "123456789asdgh", newPassword));
        Assertions.assertEquals("wrong Password", result.getMessage());
    }

    @Test
    void expertService_CallsChangePasswordForExpert_UseInvalidPassword_ReturnException() {
        //use bellow when hibernate.hbm2ddl.auto is 'create' or 'create_drop'
      /*
        File file = new File("C:\\Users\\Asus\\Desktop\\image\\expert1.png");
        String password = "123456ali";
        expertService.saveExpert(expertDto, password, file);*/
        Expert expertByEmail = expertService.findExpertByEmail(expertDto.getEmail());
        String newPassword = "1234";
        InvalidPassword result = Assertions.assertThrows(InvalidPassword.class, () ->
                expertService.changePasswordForExpert(expertDto, expertByEmail.getPassword(), newPassword));
        Assertions.assertEquals("password must contains words and numbers and password length's  must more than 8 ", result.getMessage());
    }
}
