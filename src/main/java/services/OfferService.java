package services;

import dao.InstructionDao;
import dao.OfferDao;
import exceptions.EditionDenied;
import lombok.Data;
import models.entities.Instruction;
import models.entities.Offer;
import models.entities.roles.Expert;
import models.enums.InstructionStatus;

import javax.persistence.NoResultException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
public class OfferService {
    private InstructionDao instructionDao;
    private OfferDao offerDao;
    private static OfferService offerService;

    public static OfferService instance() {

        if (offerService == null)
            offerService = new OfferService();
        return offerService;
    }

    private Date creationDate;
    private double suggestedPrice;
    private int suggestedDurationOfService;
    private Date startDate;

    public void addNewOffer(Expert expert, double suggestedPrice, int suggestedDurationOfService, String startDateString, Instruction instruction) {
        Offer offer = new Offer();
        offer.setExpert(expert);
        offer.setSuggestedDurationOfService(suggestedDurationOfService);
        offer.setSuggestedPrice(suggestedPrice);
        offer.setInstruction(instruction);
        try {
            Date startDate = new SimpleDateFormat("HH:mm").parse(startDateString);
            offer.setStartDate(startDate);
            offerDao.save(offer);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("insert fail");
        }

    }

    public void editStartDateStringOffer(Offer offer, String startDateString) {
        try {
            try {
                if (canEdit(offer.getInstruction().getStatus())) {
                    Date startDate = new SimpleDateFormat("HH:mm").parse(startDateString);
                    offer.setStartDate(startDate);
                    offerDao.update(offer);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (EditionDenied editionDenied) {
            System.out.println(editionDenied.getMessage());
        }
    }

    public void editOfferSuggestedPrice(Offer offer, double suggestedPrice) {
        try {
            if (canEdit(offer.getInstruction().getStatus())) {
                offer.setSuggestedPrice(suggestedPrice);
            }
        } catch (EditionDenied editionDenied) {
            System.out.println(editionDenied.getMessage());
        }
    }

    public void editSuggestedDurationOfService(Offer offer, int suggestedDurationOfService) {
        try {
            if (canEdit(offer.getInstruction().getStatus())) {
                offer.setSuggestedDurationOfService(suggestedDurationOfService);
                offerDao.update(offer);
            }
        } catch (EditionDenied e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Offer> findOffersOfInstruction(Instruction instruction, InstructionStatus status) {
        try {
            return offerDao.findOffersOfInstruction(instruction, status);
        } catch (NoResultException e) {
            System.out.println("no result ");
        }
        return null;
    }

    public void deleteOfferFromInstruction(long offerId) {
        try {
            Offer offer = offerDao.findById(offerId);
            if (canEdit(offer.getInstruction().getStatus())) {
                offerDao.delete(offer);
            }
        } catch (EditionDenied e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean canEdit(InstructionStatus status) throws EditionDenied {
        if (status.equals(InstructionStatus.STARTED) || status.equals(InstructionStatus.WAITING_FOR_CHOOSING_EXPERT)) {
            return true;
        } else throw new EditionDenied();
    }
}



