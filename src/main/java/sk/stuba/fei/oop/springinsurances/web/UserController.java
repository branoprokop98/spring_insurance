package sk.stuba.fei.oop.springinsurances.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sk.stuba.fei.oop.springinsurances.agreement.service.AgreementService;
import sk.stuba.fei.oop.springinsurances.user.Address;
import sk.stuba.fei.oop.springinsurances.user.service.UserService;
import sk.stuba.fei.oop.springinsurances.web.requests.AccidentResources;
import sk.stuba.fei.oop.springinsurances.web.requests.AgreementResource;
import sk.stuba.fei.oop.springinsurances.web.requests.TravelResources;
import sk.stuba.fei.oop.springinsurances.web.requests.UserResource;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private final UserService userService;
    private final AgreementService agreementService;
    private long id;

    @Autowired
    public UserController(UserService user, AgreementService agreementService) {
        this.userService = user;
        this.agreementService = agreementService;

    }

    @GetMapping("/register")
    public String registerUser(Model model){
        UserResource user = new UserResource();
        model.addAttribute("user", user);
        model.addAttribute("address", new Address());
        return "register";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Valid UserResource user, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        user.setID();
        this.userService.getUsersResources().put(user.getId(), user);
        return "redirect:";
    }

    @GetMapping("/id/{id}")
    public String printUserByID(Model model, @PathVariable long id){
        model.addAttribute("userService", userService);
        model.addAttribute("users", userService.getUsersResources().get(id));
        return "index";
    }

    @GetMapping("/")
    public String printAllUsers(Model model){
        model.addAttribute("users", userService.getUsersResources());
        return "printAll";
    }

    @GetMapping("/edit/{id}")
    public String editUser(Model model, @PathVariable long id){
        this.id = id;
        model.addAttribute("user", userService.getUsersResources().get(id));
        return "editUser";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute("user") @Valid UserResource user, BindingResult result){
        if (result.hasErrors()){
            return "/editUser";
        }
        Map<Long, AgreementResource> agreements = agreementService.getListOfAgreementResources();

        for (Map.Entry<Long, AgreementResource> agreement : agreements.entrySet()){
            if (agreement.getValue() instanceof  AccidentResources) {
                if (((AccidentResources) agreement.getValue()).getInsured().getId() == this.id) {
                    ((AccidentResources) agreement.getValue()).setInsured(user);
                }
            }
            else if (agreement.getValue() instanceof TravelResources) {
                if (((TravelResources) agreement.getValue()).getInsured().getId() == this.id) {
                    ((TravelResources) agreement.getValue()).setInsured(user);
                }
            }
        }
        List<AgreementResource> list = this.userService.getUsersResources().get(id).getListResources();
        this.userService.getUsersResources().put(this.id, user);
        user.setListResources(list);
        user.setId(id);
        return "redirect:";
    }
}
