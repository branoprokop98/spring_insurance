package sk.stuba.fei.oop.springinsurances.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.oop.springinsurances.agreement.service.AgreementService;
import sk.stuba.fei.oop.springinsurances.user.service.UserService;
import sk.stuba.fei.oop.springinsurances.web.requests.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/agreement")
public class AgreementController {
    private final AgreementService agreementService;
    private final UserService userService;
    private long userId;
    private long agreementId;

    @Autowired
    public AgreementController(AgreementService agreementService, UserService userService) {
        this.agreementService = agreementService;
        this.userService = userService;
    }

    @GetMapping("/addAccident/{id}")
    public String addAccident(Model model, @PathVariable long id) {
        this.userId = id;
        UserResource userResource = new UserResource();
        AccidentResources accident = new AccidentResources();
        model.addAttribute("insuredUsers", userService);
        model.addAttribute("insured", userResource);
        model.addAttribute("accidentResources", accident);
        return "/agreement/lifeInsurance/accident";
    }

    @GetMapping("/addTravel/{id}")
    public String addTravel(Model model, @PathVariable long id) {
        this.userId = id;
        UserResource userResource = new UserResource();
        TravelResources travel = new TravelResources();
        model.addAttribute("insuredUsers", userService);
        model.addAttribute("insured", userResource);
        model.addAttribute("travelResource", travel);
        return "/agreement/lifeInsurance/travel";
    }

    @GetMapping("/addFlat/{id}")
    public String addFlatInsurance(Model model, @PathVariable long id) {
        this.userId = id;
        FlatResources flat = new FlatResources();
        model.addAttribute("flatResource", flat);
        return "/agreement/nonLifeInsurance/flat";
    }

    @GetMapping("/addHome/{id}")
    public String addHomeInsurance(Model model, @PathVariable long id) {
        this.userId = id;
        HomeResources homeInsurance = new HomeResources();
        model.addAttribute("home", homeInsurance);
        return "/agreement/nonLifeInsurance/home";
    }

    @GetMapping("/detail/{idUser}/{id}")
    public String detailOfAccident(Model model, @PathVariable long id, @PathVariable long idUser) {
        AgreementResource agreement = null;
        List<AgreementResource> list = userService.getUsersResources().get(idUser).getListResources();
        for (AgreementResource agreementListing : list) {
            if (agreementListing.getAgreementID() == id) {
                agreement = agreementListing;
            }
        }
        if (agreement instanceof AccidentResources) {
            AccidentResources accident = (AccidentResources) agreement;
            model.addAttribute("accident", accident);
            return "/agreement/lifeInsurance/accidentDetail";
        } else if (agreement instanceof TravelResources) {
            TravelResources travel = (TravelResources)agreement;
            model.addAttribute("travel", travel);
            return "/agreement/lifeInsurance/travelDetail";
        } else if (agreement instanceof HomeResources) {
            HomeResources home = (HomeResources)agreement;
            model.addAttribute("home", home);
            return "/agreement/nonLifeInsurance/homeDetail";
        } else if (agreement instanceof FlatResources) {
            FlatResources flat = (FlatResources)agreement;
            model.addAttribute("flat", flat);
            return "/agreement/nonLifeInsurance/flatDetail";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/edit/{idUser}/{id}")
    public String editAgreement(Model model, @PathVariable long id, @PathVariable long idUser) {
        AgreementResource agreement = null;
        this.userId = idUser;
        List<AgreementResource> list = userService.getUsersResources().get(idUser).getListResources();
        for (AgreementResource agreementListing : list) {
            if (agreementListing.getAgreementID() == id) {
                agreement = agreementListing;
            }
        }
        if (agreement instanceof AccidentResources) {
            AccidentResources accident = (AccidentResources) agreement;
            this.agreementId = accident.getAgreementID();
            model.addAttribute("edit", accident);
            model.addAttribute("insuredUsers", userService);
            return "/agreement/lifeInsurance/accidentEdit";
        } else if (agreement instanceof TravelResources) {
            TravelResources travel = (TravelResources)agreement;
            this.agreementId = travel.getAgreementID();
            model.addAttribute("insuredUsers", userService);
            model.addAttribute("travelResource", travel);
            return "/agreement/lifeInsurance/travelEdit";
        } else if (agreement instanceof HomeResources) {
            HomeResources home = (HomeResources)agreement;
            this.agreementId = home.getAgreementID();
            model.addAttribute("home", home);
            return "/agreement/nonLifeInsurance/homeEdit";
        } else if (agreement instanceof FlatResources) {
            FlatResources flat = (FlatResources)agreement;
            this.agreementId = flat.getAgreementID();
            model.addAttribute("flat", flat);
            return "/agreement/nonLifeInsurance/flatEdit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/editAccident")
    public String edit(@ModelAttribute("edit") @Valid AccidentResources agreementResource, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("insuredUsers", userService);
            return "/agreement/lifeInsurance/accidentEdit";
        }
        updateList(agreementResource);
        return "redirect:/";
    }

    @PostMapping("/editTravel")
    public String edit(@ModelAttribute("travelResource") @Valid TravelResources agreementResource, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("insuredUsers", userService);
            return "/agreement/lifeInsurance/travelEdit";
        }
        updateList(agreementResource);
        return "redirect:/";
    }

    @PostMapping("/editFlat")
    public String edit(@ModelAttribute("flat") @Valid FlatResources agreementResource, BindingResult result){
        if (result.hasErrors()){
            return "/agreement/nonLifeInsurance/flatEdit";
        }
        updateList(agreementResource);
        return "redirect:/";
    }

    @PostMapping("/editHome")
    public String edit(@ModelAttribute("home") @Valid HomeResources agreementResource, BindingResult result){
        if (result.hasErrors()){
            return "/agreement/nonLifeInsurance/homeEdit";
        }
        updateList(agreementResource);
        return "redirect:/";
    }

    public void updateList(AgreementResource agreementResource){
        List<AgreementResource> list = userService.getUsersResources().get(this.userId).getListResources();
        list.removeIf(resource -> resource.getAgreementID() == this.agreementId);
        userService.getUsersResources().get(this.userId).addAgreement(agreementResource);
        agreementResource.setInsurer(userService.getUsersResources().get(this.userId));
        agreementResource.setAgreementID(this.agreementId);
        agreementService.getListOfAgreementResources().put(agreementResource.getAgreementID(), agreementResource);
    }

    @PostMapping("/addAccident")
    public String add(@ModelAttribute @Valid AccidentResources accident, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("insuredUsers", userService);
            return "/agreement/lifeInsurance/accident";
        }
        //accident.getInsured().setID();
        userService.getUsersResources().get(this.userId).addAgreement(accident);
        accident.setAgreementID();
        agreementService.addNewItem(accident);
        accident.setInsurer(userService.getUsersResources().get(this.userId));
        return "redirect:/";
    }

    @PostMapping("/addTravel")
    public String add(@ModelAttribute("travelResource") @Valid TravelResources travel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("insuredUsers", userService);
            return "/agreement/lifeInsurance/travel";
        }
        //travel.getInsured().setID();
        userService.getUsersResources().get(this.userId).addAgreement(travel);
        travel.setAgreementID();
        agreementService.addNewItem(travel);
        travel.setInsurer(userService.getUsersResources().get(this.userId));
        return "redirect:/";
    }

    @PostMapping("/addHome")
    public String add(@ModelAttribute("home") @Valid HomeResources home, BindingResult result) {
        if (result.hasErrors()) {
            return "/agreement/nonLifeInsurance/home";
        }
        userService.getUsersResources().get(this.userId).addAgreement(home);
        home.setAgreementID();
        agreementService.addNewItem(home);
        home.setInsurer(userService.getUsersResources().get(this.userId));
        return "redirect:/";
    }

    @PostMapping("/addFlat")
    public String add(@ModelAttribute("flatResource") @Valid FlatResources flat, BindingResult result) {
        if (result.hasErrors()) {
            return "/agreement/nonLifeInsurance/flat";
        }
        userService.getUsersResources().get(this.userId).addAgreement(flat);
        flat.setAgreementID();
        agreementService.addNewItem(flat);
        flat.setInsurer(userService.getUsersResources().get(this.userId));
        return "redirect:/";
    }


    @PostMapping("/add")
    public String add(@ModelAttribute @Valid AgreementResource agreement, BindingResult result) {
        if (result.hasErrors()) {
            return "/agreement/lifeInsurance/travel";
        }
        agreement.setAgreementID();
        agreement.setInsurer(this.userService.getUsersResources().get(this.userId));
        agreementService.getListOfAgreementResources().put(agreement.getAgreementID(), agreement);
        this.userService.getUsersResources().get(this.userId).addAgreement(agreement);
        //agreement.getInsured().setID();
        return "redirect:/";
    }

}
