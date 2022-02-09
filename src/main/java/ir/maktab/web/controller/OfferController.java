package ir.maktab.web.controller;

import ir.maktab.data.models.enums.OfferStatus;
import ir.maktab.dto.modelDtos.CommentDto;
import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.CustomerDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.AccessDenied;
import ir.maktab.services.ExpertService;
import ir.maktab.services.OfferService;
import ir.maktab.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offer")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;
    private final OrderService orderService;
    private final ExpertService expertService;

    @PostMapping("submitOffer")
    public ModelAndView submitOffer(@SessionAttribute("expertDto") ExpertDto expertDto,
                                    @SessionAttribute("orderDto") OrderDto orderDto,
                                    @ModelAttribute("offerDto") OfferDto offerDto) throws ParseException {

        if (expertDto == null)
            throw new AccessDenied();

        offerDto.setExpert(expertDto);
        offerDto.setOrder(orderDto);

        offerService.saveOffer(offerDto);

        return new ModelAndView("/offer/expertOffers");
    }

/*
    @GetMapping("/expertOffers")
    public ModelAndView showExpertOffersPage(@SessionAttribute("expertDto") ExpertDto expertDto) {

        if (expertDto == null)
            throw new AccessDenied();

        List<OfferDto> expertOffers = offerService.findExpertOffer(expertDto);

        return new ModelAndView("/offer/expertOffers", "expertOffers", expertOffers);
    }

    @GetMapping("/expertOffers/{identificationCode}")
    public ModelAndView selectOfferFromExpertOffers(@SessionAttribute("expertDto") ExpertDto expertDto,
                                                    @PathVariable String identificationCode) {
        if (expertDto == null)
            throw new AccessDenied();

        OfferDto offerDto = offerService.findOfferDtoByIdentificationCode(UUID.fromString(identificationCode));

        return new ModelAndView("offer/showOfferMenuForExpert", "offerDto", offerDto);
    }*/

    @GetMapping("/expertOffers")
    public ModelAndView showExpertOfferPage(@SessionAttribute("expertDto") ExpertDto expertDto) {

        if (expertDto == null)
            throw new AccessDenied();

        List<OfferDto> expertOffers = offerService.findExpertOffer(expertDto);

        return new ModelAndView("offer/showExpertOffers", "expertOffers", expertOffers);
    }

    @GetMapping("/expertOffers/{identificationCode}")
    public String selectOfferFromShowExpertOfferPage(@PathVariable String identificationCode,
                                                     HttpSession httpSession) {

        if (httpSession.getAttribute("expertDto") == null)
            throw new AccessDenied();

        OfferDto offerDto = offerService.findOfferDtoByIdentificationCode(UUID.fromString(identificationCode));
        httpSession.setAttribute("offerDto", offerDto);

        return "offer/showOfferMenuForExpert";
    }

    @GetMapping("/editOfferStartDate")
    public String showEditOfferStartDatePage(@SessionAttribute("expertDto") ExpertDto expertDto,
                                             @SessionAttribute("offerDto") OfferDto offerDto) {
        if (expertDto == null && offerDto == null)
            throw new AccessDenied();

        return "/offer/editOfferStartDate";
    }

    @PostMapping("/editOfferStartDate")
    public String editOfferStartDate(@SessionAttribute("expertDto") ExpertDto expertDto,
                                     @SessionAttribute("offerDto") OfferDto offerDto,
                                     @RequestParam(value = "startDate", required = false) @NotNull @DateTimeFormat(pattern = "hh:mm") String startDate) {

        if (expertDto == null && offerDto == null)
            throw new AccessDenied();

        try {
            offerDto = offerService.editStartDateOffer(offerDto, startDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "offer/showOfferMenuForExpert";
    }


    @GetMapping("/editOfferSuggestedPrice")
    public String showEditOfferSuggestedPricePage(@SessionAttribute("expertDto") ExpertDto expertDto,
                                                  @SessionAttribute("offerDto") OfferDto offerDto) {
        if (expertDto == null && offerDto == null)
            throw new AccessDenied();
        return "/offer/editOfferSuggestedPrice";
    }

    @PostMapping("/editOfferSuggestedPrice")
    public String editOfferSuggestedPrice(@SessionAttribute("expertDto") ExpertDto expertDto,
                                          @SessionAttribute("offerDto") OfferDto offerDto,
                                          @RequestParam(value = "suggestedPrice", required = false) @NotNull @NumberFormat String suggestedPrice) {

        if (expertDto == null && offerDto == null)
            throw new AccessDenied();

        try {
            offerDto = offerService.editOfferSuggestedPrice(offerDto, suggestedPrice);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "offer/showOfferMenuForExpert";
    }

    @GetMapping("/editOfferSuggestedDurationOfService")
    public String showEditOfferSuggestedDurationOfServicePage(@SessionAttribute("expertDto") ExpertDto expertDto,
                                                              @SessionAttribute("offerDto") OfferDto offerDto) {
        if (expertDto == null && offerDto == null)
            throw new AccessDenied();

        return "/offer/editOfferSuggestedDurationOfService";
    }

    @PostMapping("/editOfferSuggestedDurationOfService")
    public String editOfferSuggestedDurationOfService(@SessionAttribute("expertDto") ExpertDto expertDto,
                                                      @SessionAttribute("offerDto") OfferDto offerDto,
                                                      @RequestParam(value = "suggestedDurationOfService", required = false)
                                                      @NotNull @NumberFormat String suggestedDurationOfService) {

        if (expertDto == null && offerDto == null)
            throw new AccessDenied();

        try {
            offerDto = offerService.editSuggestedDurationOfService(offerDto, suggestedDurationOfService);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "offer/showOfferMenuForExpert";
    }

    @GetMapping("/changeOrderStatus")
    public String ShowChangeOfferStatus(@SessionAttribute("expertDto") ExpertDto expertDto,
                                        @SessionAttribute("offerDto") OfferDto offerDto) {

        if (expertDto == null && offerDto == null && offerDto.getStatus() != OfferStatus.ACCEPT)
            throw new AccessDenied();

        return "/offer/changeOrderStatus";
    }

    @GetMapping("/changeOrderStatus/{status}")
    public String changeOfferStatus(@SessionAttribute("expertDto") ExpertDto expertDto,
                                    @SessionAttribute("offerDto") OfferDto offerDto,
                                    @PathVariable String status,
                                    HttpSession httpSession) {
        if (expertDto == null && offerDto == null && offerDto.getStatus() != OfferStatus.ACCEPT)
            throw new AccessDenied();

        try {
           offerService.changeOrderStatus(offerDto, status);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        httpSession.removeAttribute("offerDto");
        return "/offer/expertOffers";
    }


 /*   @GetMapping("/changeOrderStatus")
    public ModelAndView showChangeStatusPage*/
}

