package ir.maktab.services;

import ir.maktab.dao.ExpertDao;
import ir.maktab.dao.SubServiceDao;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.SubServiceNotFound;
import ir.maktab.exceptions.WrongPassword;
import lombok.Data;
import ir.maktab.models.entities.SubService;
import ir.maktab.models.entities.roles.Expert;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ir.maktab.validation.ControlEdition;
import ir.maktab.validation.ControlInput;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@Service
@Data
public class ExpertService {
    private final ExpertDao expertDao;
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition;
    private final SubServiceDao subServiceDao;

    @Autowired
    public ExpertService(ExpertDao expertDao, ControlInput controlInput, ModelMapper modelMapper, ControlEdition controlEdition, SubServiceDao subServiceDao) {
        this.expertDao = expertDao;
        this.controlInput = controlInput;
        this.modelMapper = modelMapper;
        this.controlEdition = controlEdition;
        this.subServiceDao = subServiceDao;
    }

    public void saveExpert(ExpertDto expertDto, String password, File file) {
        if (controlInput.isValidExpertDtoInfo(expertDto, password, file)) {
            byte[] imageBytes = initializePhoto(file);
            Expert expert = modelMapper.map(expertDto, Expert.class);
            expert.setPhoto(imageBytes);
            expert.setPassword(password);
            expertDao.save(expert);
        } else
            throw new RuntimeException("sing up fail");
    }

    public Expert findExpertByEmail(String email) {

        if (controlInput.isValidEmail(email)) {
            return findExpertByEmail(email);
        }
        throw new RuntimeException("finding fail ");
    }

    public ExpertDto findExpertDtoByEmail(String email) {
        if (controlInput.isValidEmail(email)) {
            Expert expert = findExpertByEmail(email);
            return modelMapper.map(expert, ExpertDto.class);
        } else
            throw new RuntimeException("searching fail ");
    }

    public void changePasswordForExpert(ExpertDto expertDto, String currentPassword, String newPassword) {
        Expert expert = findExpertByEmail(expertDto.getEmail());
        if (expert.getPassword().equals(currentPassword)) {
            expert.setPassword(newPassword);
            expertDao.save(expert);
            System.out.println("done");
        } else
            throw new WrongPassword();
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

    public void addServiceToExpertServices(ExpertDto expertDto, SubServiceDto subServiceDto) {
        Expert expert = findExpertByEmail(expertDto.getEmail());
        if (expert != null) {
            SubService subServiceFound = findSubServiceBySubServiceDto(subServiceDto);
            expert.getSubServices().add(subServiceFound);
            expertDao.save(expert);
        }
        throw new RuntimeException("add subService Fail");
    }

    public void deleteServiceFromExpertServices(ExpertDto expertDto, SubServiceDto subServiceDto) {
        Expert expert = findExpertByEmail(expertDto.getEmail());
        if (expert != null) {
            SubService subServiceFound = findSubServiceBySubServiceDto(subServiceDto);
            expert.getSubServices().remove(subServiceFound);
            expertDao.save(expert);
        }
        throw new RuntimeException("delete subService Fail");
    }

    public SubService findSubServiceBySubServiceDto(SubServiceDto subServiceDto) {
        List<SubService> subServicesFound = subServiceDao.findByName(subServiceDto.getName());
        if (!subServicesFound.isEmpty()) {
            return subServicesFound.get(0);
        } else
            throw new SubServiceNotFound();
    }


}

