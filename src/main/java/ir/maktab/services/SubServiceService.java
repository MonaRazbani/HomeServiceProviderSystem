package ir.maktab.services;

import ir.maktab.dao.SubServiceDao;
import ir.maktab.dto.modelDtos.SubServiceDto;
import ir.maktab.exceptions.DuplicateSubService;
import ir.maktab.exceptions.NoCategoryServiceForService;
import ir.maktab.exceptions.SubServiceNotFound;
import ir.maktab.models.entities.SubService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubServiceService {
    private final SubServiceDao subServiceDao;
    private final ModelMapper modelMapper;


    @Autowired
    public SubServiceService(SubServiceDao subServiceDao, ModelMapper modelMapper) {
        this.subServiceDao = subServiceDao;
        this.modelMapper = modelMapper;

    }

    public void saveSubService(SubServiceDto subServiceDto) {
        SubService subService = modelMapper.map(subServiceDto, SubService.class);
        if (subServiceDao.findByName(subService.getName()).isEmpty()) {
            if (subService.getServiceCategory() == null)
                throw new NoCategoryServiceForService();
            subServiceDao.save(subService);
        } else
            throw new DuplicateSubService();
    }


    public SubService findByName(String name) {
        Optional<SubService> subService = subServiceDao.findByName(name);
        if (subService.isPresent()) {
            return subService.get();
        } else
            throw new SubServiceNotFound();
    }
}
