package ir.maktab.services;

import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface ExpertService {
    ExpertDto saveExpert(ExpertDto expertDto, CommonsMultipartFile profilePhoto);

    ExpertDto updateExpert(ExpertDto expertDto);

    Expert findExpertByEmail(String email);

    long findExpertId(String email);

    void changePasswordForExpert(ExpertDto expertDto, String currentPassword, String newPassword);

    void addSubServiceToExpertSubServices(ExpertDto expertDto, SubServiceDto subServiceDto);

    void deleteServiceFromExpertServices(ExpertDto expertDto, SubServiceDto subServiceDto);


}
