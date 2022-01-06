package services;

import dao.*;
import lombok.Data;
import models.entities.Address;
import models.entities.Instruction;
import models.entities.Service;
import models.entities.roles.Customer;
import models.enums.InstructionStatus
import validation.ControlInput;

import javax.persistence.NoResultException;

@Data
public class InstructionService {
    private ServiceDao serviceDao;
    private ExpertDao expertDao;
    private CustomerDao customerDao;
    private OfferDao offerDao;
    private ControlInput controlInput;
    private AddressDao addressDao;
    private InstructionDao instructionDao ;
    private static InstructionService instructionService;

    public static InstructionService instance() {

        if (instructionService == null)
            instructionService = new InstructionService();

        return instructionService;
    }

    public void addNewInstruction(Customer customer, double suggestedPrice, String explanation, String addressInfo, String serviceName) {
        Address address = AddressService.createAddress(addressInfo);
        addressDao.save(address);
        try {
            Service service = serviceDao.findByName(serviceName);
            Instruction instruction = new Instruction();
            instruction.setCustomer(customer);
            instruction.setExplanation(explanation);
            instruction.setSuggestedPrice(suggestedPrice);
            instruction.setService(service);
            instruction.setStatus(InstructionStatus.STARTED);
            instruction.setAddress(address);
            instructionDao.save(instruction);
        }catch (NoResultException noResultException){
            System.out.println("no service exists with this name ");
        }
    }

    public void editInstructionExplanation (Instruction instruction , String newExplanation ){
        instruction.setExplanation(newExplanation);
        instructionDao.update(instruction);
    }

    public void editInstructionAddress (Instruction instruction , String addressInfo ){
        Address address = AddressService.createAddress(addressInfo);
        addressDao.update(address);
    }

    public void editInstructionSuggestedPrice (Instruction instruction , double suggestedPrice ){
        instruction.setSuggestedPrice(suggestedPrice);
        instructionDao.update(instruction);
    }
    public void editInstructionServiceType(Instruction instruction , String serviceName ){
        try {
            Service service = serviceDao.findByName(serviceName);
            instruction.setService(service);
            instructionDao.update(instruction);
        }catch (NoResultException noResultException){
            System.out.println("no service exists with this name ");
        }
    }


}
