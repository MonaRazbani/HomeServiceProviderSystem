package ir.maktab.services;

import ir.maktab.dao.ExpertDao;
import ir.maktab.dao.SubServiceDao;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.*;
import ir.maktab.models.entities.SubService;
import ir.maktab.models.entities.roles.Expert;
import ir.maktab.validation.ControlEdition;
import ir.maktab.validation.ControlInput;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@Service
@Data
public class ExpertService {
    private final ExpertDao expertDao;
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition;
    private final SubServiceService subServiceService;

    @Autowired
    public ExpertService(ExpertDao expertDao, ControlInput controlInput, ModelMapper modelMapper, ControlEdition controlEdition, SubServiceService subServiceService) {
        this.expertDao = expertDao;
        this.controlInput = controlInput;
        this.modelMapper = modelMapper;
        this.controlEdition = controlEdition;
        this.subServiceService = subServiceService;
    }

    public void saveExpert(ExpertDto expertDto) {

        if (controlInput.isValidExpertDtoInfo(expertDto)) {
            if (expertDao.findByEmail(expertDto.getEmail()).isEmpty()) {

                Expert expert = modelMapper.map(expertDto, Expert.class);
                expert.setIdentificationCode(UUID.randomUUID());
                expertDao.save(expert);

            } else
                throw new DuplicateEmail();
        } else
            throw new RuntimeException("sing up fail");
    }

    public Expert findExpertByEmail(String email) {
        if (controlInput.isValidEmail(email)) {
            Optional<Expert> expert = expertDao.findByEmail(email);
            if (expert.isPresent()) {
                return expert.get();
            } else throw new ExpertNotFound();
        } else
            throw new RuntimeException("searching fail ");
    }

    public void changePasswordForExpert(ExpertDto expertDto, String currentPassword, String newPassword) {
        Expert expert = findExpertByEmail(expertDto.getEmail());
        if (controlInput.isValidPassword(newPassword)) {
            if (expert.getPassword().equals(currentPassword)) {
                expert.setPassword(newPassword);
                expertDao.save(expert);
                System.out.println("done");
            } else
                throw new WrongPassword();
        } else
            throw new InvalidPassword();
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

    public void addSubServiceToExpertSubServices(ExpertDto expertDto, SubServiceDto subServiceDto) {
        Expert expert = findExpertByEmail(expertDto.getEmail());
        if (expert != null) {
            SubService subService = subServiceService.findByName(subServiceDto.getName());
            expert.getSubServices().add(subService);
            expertDao.save(expert);
        }
        throw new RuntimeException("add subService Fail");
    }

    public void deleteServiceFromExpertServices(ExpertDto expertDto, SubServiceDto subServiceDto) {
        Expert expert = findExpertByEmail(expertDto.getEmail());
        if (expert != null) {
            SubService subServiceFound = subServiceService.findByName(subServiceDto.getName());

            expert.getSubServices().remove(subServiceFound);
            expertDao.save(expert);
        }
        throw new RuntimeException("delete subService Fail");
    }


}

