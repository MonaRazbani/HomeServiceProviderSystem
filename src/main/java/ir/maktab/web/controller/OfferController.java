package ir.maktab.web.controller;

import ir.maktab.dto.modelDtos.OfferDto;
import ir.maktab.dto.modelDtos.OrderDto;
import ir.maktab.dto.modelDtos.roles.ExpertDto;
import ir.maktab.exceptions.AccessDenied;
import ir.maktab.services.ExpertService;
import ir.maktab.services.OfferService;
import ir.maktab.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
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
                                    @ModelAttribute("offerDto") OfferDto offerDto) {

        if (expertDto == null)
            throw new AccessDenied();

        offerDto.setExpert(expertDto);
        offerDto.setOrder(orderDto);

        offerService.saveOffer(offerDto);

        return new ModelAndView("/offer/expertOffers");
    }

    @GetMapping("expertOffer")
    public ModelAndView showListOfOffersPage(HttpSession httpSession) {

        ExpertDto expertDto = (ExpertDto) httpSession.getAttribute("expertDto");
        if (expertDto == null)
            throw new AccessDenied();
        List<OfferDto> expertOffers = offerService.findExpertOffer(expertDto);

        return new ModelAndView("/offer/expertOffers", "expertOffers", expertOffers);
    }

    @GetMapping("/expertOffers")
    public ModelAndView showExpertOffersPage(@SessionAttribute("expertDto") ExpertDto expertDto) {

        if (expertDto == null)
            throw new AccessDenied();

        List<OfferDto> expertOffers = offerService.findExpertOffer(expertDto);

        return new ModelAndView("/offer/expertOffers", "expertOffers", expertOffers);
    }
    @GetMapping("/expertOffer/{identificationCode}")
    public ModelAndView selectOfferFromExpertOffers(@SessionAttribute("expertDto") ExpertDto expertDto,
                                                    @PathVariable String identificationCode){
        if(expertDto==null)
            throw new AccessDenied();

        OfferDto offerDto = offerService.findOfferDtoByIdentificationCode(UUID.fromString(identificationCode));

        return new ModelAndView("offer/showOfferMenuForExpert","offerDto",offerDto);
    }
 /*   @GetMapping("/changeOrderStatus")
    public ModelAndView showChangeStatusPage*/
}

