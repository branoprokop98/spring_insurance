package sk.stuba.fei.oop.springinsurances.service;

import sk.stuba.fei.oop.springinsurances.agreement.Agreement;
import sk.stuba.fei.oop.springinsurances.agreement.service.AgreementService;
import sk.stuba.fei.oop.springinsurances.user.User;
import sk.stuba.fei.oop.springinsurances.user.service.UserService;

import java.util.List;
import java.util.Map;

public class AdministrationSystem implements Printable {

    private final UserService userService;
    private final AgreementService agreementService;

    public AdministrationSystem() {
        this.userService = new UserService();
        this.agreementService = new AgreementService();
    }

    public UserService getUserService() {
        return userService;
    }

    public AgreementService getAgreementService() {
        return agreementService;
    }

    @Override
    public void printSignedAgreements(long uuid){

        List<Agreement> a = userService.getUsers().get(uuid).getList();
        System.out.println(userService.getUsers().get(uuid).getUid() + " " + userService.getUsers().get(uuid).getName() + " " + userService.getUsers().get(uuid).getSurname());

        for (int i = 0; i < a.size(); i++){
            System.out.println(i + " " + a.get(i));
        }
    }

    @Override
    public void printAllSignedAgreement(){
        for (long id : agreementService.getListOfAgreement().keySet()){
            System.out.println(id + " " + agreementService.getListOfAgreement().get(id));
        }
    }

    @Override
    public void printUserByUID(long uuid){
        System.out.println(userService.findById(uuid));
    }

    @Override
    public void printAllUsers(){
        for (Map.Entry<Long, User> user : userService.getUsers().entrySet()){
            System.out.println(user.getKey() + " " + user.getValue().getName() +  " " + user.getValue().getSurname());
        }
    }

}
