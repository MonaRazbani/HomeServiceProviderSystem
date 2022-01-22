package ir.maktab.services;

import ir.maktab.data.dao.SubServiceDao;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.exceptions.DuplicateSubService;
import ir.maktab.exceptions.NoCategoryServiceForService;
import ir.maktab.exceptions.SubServiceNotFound;
import ir.maktab.data.models.entities.SubService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class SubServiceServiceImp implements SubServiceService{
    private final SubServiceDao subServiceDao;
    private final ModelMapper modelMapper;

    @Override
    public void saveSubService(SubServiceDto subServiceDto) {
        SubService newSubService = modelMapper.map(subServiceDto, SubService.class);
        Optional<SubService> oldSubService = subServiceDao.findByName(newSubService.getName());
        if (oldSubService.isEmpty()) {
            if (newSubService.getServiceCategory() == null)
                throw new NoCategoryServiceForService();
            newSubService.setId(oldSubService.get().getId());
            subServiceDao.save(newSubService);
        } else
            throw new DuplicateSubService();
    }

    @Override
    public void updateSubService(SubServiceDto subServiceDto) {
        SubService subService = modelMapper.map(subServiceDto, SubService.class);
        if (subServiceDao.findByName(subService.getName()).isPresent()) {
            if (subService.getServiceCategory() == null)
                throw new NoCategoryServiceForService();
            subServiceDao.save(subService);
        } else
            throw new SubServiceNotFound();
    }

    @Override
    public SubService findByName(String name) {
        Optional<SubService> subService = subServiceDao.findByName(name);
        if (subService.isPresent()) {
            return subService.get();
        } else
            throw new SubServiceNotFound();
    }

}
