package sk.stuba.fei.oop.springinsurances.agreement.service;

import org.springframework.stereotype.Service;
import sk.stuba.fei.oop.springinsurances.agreement.Agreement;
import sk.stuba.fei.oop.springinsurances.agreement.life.Accident;
import sk.stuba.fei.oop.springinsurances.agreement.life.Travel;
import sk.stuba.fei.oop.springinsurances.agreement.nonlife.FlatInsurance;
import sk.stuba.fei.oop.springinsurances.agreement.nonlife.HomeInsurance;
import sk.stuba.fei.oop.springinsurances.user.Address;
import sk.stuba.fei.oop.springinsurances.user.User;
import sk.stuba.fei.oop.springinsurances.web.requests.AgreementResource;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AgreementService implements CreateInsurances {

    private final Map<Long, Agreement> listOfAgreement;
    private final Map<Long, AgreementResource> listOfAgreementResources;

    public AgreementService() {
        listOfAgreement = new HashMap<>();
        listOfAgreementResources = new HashMap<>();
    }

    public Map<Long, Agreement> getListOfAgreement() {
        return listOfAgreement;
    }

    public Map<Long, AgreementResource> getListOfAgreementResources() {
        return listOfAgreementResources;
    }

    @Override
    public Accident newAccidentInsurance(User insurer, User insured, boolean isEU, float permanentConsequences, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment){
        Accident accident = new Accident(insurer, insured, isEU, permanentConsequences, amountOfInsuranceIndemnity, amountOfTheMonthlyPayment);
        addNewItem(accident);
        return accident;
    }

    @Override
    public Travel newTravelInsurance(User insurer, User insured, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment, boolean dead, float reparation){
        Travel travel = new Travel(insurer, insured, amountOfInsuranceIndemnity, amountOfTheMonthlyPayment, dead, reparation);
        addNewItem(travel);
        return travel;
    }

    @Override
    public HomeInsurance newHomeInsurance(User user, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment, float valueOfProperty, float valueOfDevices, int idxOfProperty, Address address){
        HomeInsurance homeInsurance = new HomeInsurance(user, amountOfInsuranceIndemnity, amountOfTheMonthlyPayment, valueOfProperty, valueOfDevices, idxOfProperty, address);
        addNewItem(homeInsurance);
        return homeInsurance;
    }


    @Override
    public FlatInsurance newFlatInsurance(User user, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment, float valueOfProperty, float valueOfDevices, boolean garage, int idx, Address address){
        FlatInsurance flatInsurance = new FlatInsurance(user, amountOfInsuranceIndemnity, amountOfTheMonthlyPayment, valueOfProperty, valueOfDevices, garage, idx, address);
        addNewItem(flatInsurance);
        return flatInsurance;
    }

    public void editAgreement(long id, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment, LocalDateTime end){
        Agreement agreement = listOfAgreement.get(id);
        agreement.setAmountOfInsuranceIndemnity(amountOfInsuranceIndemnity);
        agreement.setAmountOfTheMonthlyPayment(amountOfTheMonthlyPayment);
        agreement.setEnd(end);
    }

    public void addNewItem(Agreement agreement){
        listOfAgreement.put(agreement.getUid(), agreement);
    }

    public void addNewItem(AgreementResource agreement){
        listOfAgreementResources.put(agreement.getAgreementID(), agreement);
    }


    public void editItem(Agreement agreement, long id){
        listOfAgreement.get(id).getInsurer().getList().remove(listOfAgreement.get(id));
        listOfAgreement.put(id, agreement);
    }



}
