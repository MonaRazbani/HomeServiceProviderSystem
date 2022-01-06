package services;

import dao.*;
import lombok.Data;
import validation.ControlInput;
@Data
public class InstructionService{
    private ServiceDao serviceDao ;
    private ExpertDao expertDao ;
    private CustomerDao customerDao ;
    private OfferDao offerDao ;
    private ControlInput controlInput ;
    private static InstructionService instructionService ;

    public static InstructionService instance() {

        if (instructionService == null)
            instructionService = new InstructionService();

        return instructionService;
    }

}
