package ir.maktab.services;

import ir.maktab.data.dao.ExpertDao;
import ir.maktab.data.models.entities.SubService;
import ir.maktab.data.models.entities.roles.Expert;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.DuplicateEmail;
import ir.maktab.exceptions.ExpertNotFound;
import ir.maktab.exceptions.WrongPassword;
import ir.maktab.validation.ControlEdition;
import ir.maktab.validation.ControlInput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ExpertServiceImp implements ExpertService {
    private final ExpertDao expertDao;
    private final ControlInput controlInput;
    private final ModelMapper modelMapper;
    private final ControlEdition controlEdition;
    private final SubServiceServiceImp subServiceServiceImp;


    @Override
    public ExpertDto saveExpert(ExpertDto expertDto, CommonsMultipartFile profilePhoto) {

        if (expertDao.findByEmail(expertDto.getEmail()).isEmpty()) {
            Expert expert = modelMapper.map(expertDto, Expert.class);
            expert.setProfilePhoto(profilePhoto.getBytes());
            Expert save = expertDao.save(expert);
            return modelMapper.map(save, ExpertDto.class);

        } else
            throw new DuplicateEmail();
    }

    @Override
    public ExpertDto loginExpert(ExpertDto expertDto) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        Optional<Expert> found = expertDao.findByEmailAndPassword(expert.getEmail(), expert.getPassword());
        if (found.isPresent()) {
            return modelMapper.map(found.get(), ExpertDto.class);
        } else
            throw new ExpertNotFound();
    }


    @Override
    public ExpertDto updateExpert(ExpertDto expertDto) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        long customerId = findExpertId(expertDto.getEmail());
        expert.setId(customerId);
        Expert save = expertDao.save(expert);
        return modelMapper.map(save, ExpertDto.class);
    }

    @Override
    public Expert findExpertByEmail(String email) {
        if (controlInput.isValidEmail(email)) {
            Optional<Expert> expert = expertDao.findByEmail(email);
            if (expert.isPresent()) {
                return expert.get();
            } else throw new ExpertNotFound();
        } else
            throw new RuntimeException("searching fail ");
    }

    @Override
    public void changePasswordForExpert(ExpertDto expertDto, String currentPassword, String newPassword) {
        Expert expert = findExpertByEmail(expertDto.getEmail());
        if (expert.getPassword().equals(currentPassword)) {
            long expertId = findExpertId(expert.getEmail());
            expert.setId(expertId);
            expert.setPassword(newPassword);
            expertDao.save(expert);
            System.out.println("done");
        } else
            throw new WrongPassword();
    }

    @Override
    public long findExpertId(String email) {
        Expert expert = findExpertByEmail(email);
        return expert.getId();
    }

    @Override
    public void addSubServiceToExpertSubServices(ExpertDto expertDto, String subServiceName) {
        Expert expert = findExpertByEmail(expertDto.getEmail());
        if (expert != null) {
            SubService subService = subServiceServiceImp.findByName(subServiceName);
            expert.getSubServices().add(subService);
            expertDao.save(expert);
        }
         else throw new RuntimeException("add subService Fail");
    }

    @Override
    public void deleteServiceFromExpertServices(ExpertDto expertDto, SubServiceDto subServiceDto) {
        Expert expert = findExpertByEmail(expertDto.getEmail());
        if (expert != null) {
            SubService subServiceFound = subServiceServiceImp.findByName(subServiceDto.getName());
            expert.getSubServices().remove(subServiceFound);
            expertDao.save(expert);
        }
        throw new RuntimeException("delete subService Fail");
    }


}

