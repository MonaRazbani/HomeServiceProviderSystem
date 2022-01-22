package ir.maktab.services;

import ir.maktab.data.models.entities.SubService;
import ir.maktab.dto.modelDtos.SubServiceDto;

public interface SubServiceService {

    void saveSubService(SubServiceDto subServiceDto) ;

    void updateSubService(SubServiceDto subServiceDto);

    SubService findByName(String name);

}
