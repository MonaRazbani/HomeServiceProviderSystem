package services;

import dao.*;
import exceptions.EditionDenied;
import exceptions.InvalidSuggestedPrice;
import lombok.Data;
import models.entities.Address;
import models.entities.Instruction;
import models.entities.Service;
import models.entities.roles.Customer;
import models.entities.roles.Expert;
import models.enums.InstructionStatus;
import validation.ControlInput;

import javax.persistence.ConstructorResult;
import javax.persistence.NoResultException;
import java.util.List;

@Data
public class InstructionService {
    private ServiceDao serviceDao;
    private AddressDao addressDao;
    private InstructionDao instructionDao;
    private ControlInput controlInput ;
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

            try {

                if (controlInput.isValidSuggestedPrice(service, suggestedPrice)) {
                    Instruction instruction = new Instruction();
                    instruction.setCustomer(customer);
                    instruction.setExplanation(explanation);
                    instruction.setSuggestedPrice(suggestedPrice);
                    instruction.setService(service);
                    instruction.setStatus(InstructionStatus.STARTED);
                    instruction.setAddress(address);
                    instructionDao.save(instruction);
                }
            }catch (InvalidSuggestedPrice e){
                System.out.println(e.getMessage());
            }

        } catch (NoResultException noResultException) {
            System.out.println("no service exists with this name ");
        }
    }

    public void editInstructionExplanation(Instruction instruction, String newExplanation) {
        try {
            if (instruction.getStatus().equals(InstructionStatus.STARTED) || instruction.getStatus().equals(InstructionStatus.WAITING_FOR_CHOOSING_EXPERT)) {
                instruction.setExplanation(newExplanation);
                instructionDao.update(instruction);
            }
        } catch (EditionDenied editionDenied) {
            System.out.println(editionDenied.getMessage());
        }
    }

    public void editInstructionAddress(Instruction instruction, String addressInfo) {
        try {
        if (canEdit(instruction.getStatus())){
                Address address = AddressService.createAddress(addressInfo);
                addressDao.save(address);
                instruction.setAddress(address);
                instructionDao.update(instruction);
            }
        } catch (EditionDenied editionDenied) {
            System.out.println(editionDenied.getMessage());
        }
    }

    public void editInstructionSuggestedPrice(Instruction instruction, double suggestedPrice) {
        try {
            if (canEdit(instruction.getStatus())) {
                instruction.setSuggestedPrice(suggestedPrice);
                instructionDao.update(instruction);
            }
        } catch (EditionDenied e) {
            System.out.println(e.getMessage());
        }
    }

    public void editInstructionServiceType(Instruction instruction, String serviceName) {
        try {
            try {
                if (canEdit(instruction.getStatus())) {
                    Service service = serviceDao.findByName(serviceName);
                    instruction.setService(service);
                    instructionDao.update(instruction);
                }
            } catch (EditionDenied e) {
                System.out.println(e.getMessage());
            }
        } catch (NoResultException noResultException) {
            System.out.println("no service exists with this name ");
        }
    }

    public void deleteInstructionFromCustomer(long instructionId) {
        try {
            Instruction instruction = instructionDao.findById(instructionId);
            if (canEdit(instruction.getStatus())) {
                instructionDao.delete(instruction);
            }
        } catch (EditionDenied e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Instruction> findInstructionByCustomerAndStatus(Customer customer, InstructionStatus status) {
        try {
            return instructionDao.findInstructionByCustomerAndStatus(customer, status);
        } catch (NoResultException e) {
            System.out.println("no result ");
        }
        return null;
    }

    public List<Instruction> findInstructionByExpertAndStatus(Expert expert, InstructionStatus status) {
        try {
            return instructionDao.findInstructionByExpertAndStatus(expert, status);
        } catch (NoResultException e) {
            System.out.println("no result ");
        }
        return null;
    }

    private boolean canEdit(InstructionStatus status) throws EditionDenied {
        if (status.equals(InstructionStatus.STARTED) || status.equals(InstructionStatus.WAITING_FOR_CHOOSING_EXPERT)) {
            return true;
        } else throw new EditionDenied();
    }
}
