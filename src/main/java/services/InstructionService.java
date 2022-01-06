package services;

import dao.*;
import exceptions.EditionDenied;
import jdk.jshell.Snippet;
import lombok.Data;
import models.entities.Address;
import models.entities.Instruction;
import models.entities.Service;
import models.entities.roles.Customer;
import models.entities.roles.Expert;
import models.enums.InstructionStatus;
import validation.ControlInput;

import javax.persistence.NoResultException;
import java.util.List;

@Data
public class InstructionService {
    private ServiceDao serviceDao;
    private ExpertDao expertDao;
    private CustomerDao customerDao;
    private OfferDao offerDao;
    private ControlInput controlInput;
    private AddressDao addressDao;
    private InstructionDao instructionDao;
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
        } catch (NoResultException noResultException) {
            System.out.println("no service exists with this name ");
        }
    }

    public void editInstructionExplanation(Instruction instruction, String newExplanation) {
        instruction.setExplanation(newExplanation);
        instructionDao.update(instruction);
    }

    public void editInstructionAddress(Instruction instruction, String addressInfo) {
        Address address = AddressService.createAddress(addressInfo);
        addressDao.save(address);
        instruction.setAddress(address);
    }

    public void editInstructionSuggestedPrice(Instruction instruction, double suggestedPrice) {
        instruction.setSuggestedPrice(suggestedPrice);

    }

    public void editInstructionServiceType(Instruction instruction, String serviceName) {
        try {
            try {
                if (instruction.getStatus().equals(InstructionStatus.STARTED) || instruction.getStatus().equals(InstructionStatus.WAITING_FOR_CHOOSING_EXPERT)) {
                    Service service = serviceDao.findByName(serviceName);
                    instruction.setService(service);
                    instructionDao.update(instruction);

                } else throw new EditionDenied();

            } catch (EditionDenied editionDenied) {
                System.out.println(editionDenied.getMessage());
            }

        } catch (NoResultException noResultException) {
            System.out.println("no service exists with this name ");
        }
    }

    public void deleteInstructionFromCustomer(Instruction instruction, Customer customer) {
        customer = customerDao.getCustomerWithInstruction(customer);
        customer.getInstructions().remove(instruction);
        customerDao.update(customer);

    }

    public void deleteInstructionFromCustomer(long instructionId) {
        Instruction instruction = instructionDao.findById(instructionId);
        instructionDao.delete(instruction);
    }

    public List<Instruction> findInstructionByCustomerAndStatus(Customer customer, InstructionStatus status) {
        try {
           return  instructionDao.findInstructionByCustomerAndStatus(customer, status);
        } catch (NoResultException e) {
            System.out.println("no result ");
        }
        return null;
    }

    public List<Instruction> findInstructionByExpertAndStatus(Expert expert, InstructionStatus status) {
        try {
           return  instructionDao.findInstructionByExpertAndStatus(expert, status);
        } catch (NoResultException e) {
            System.out.println("no result ");
        }
        return null;
    }
}
