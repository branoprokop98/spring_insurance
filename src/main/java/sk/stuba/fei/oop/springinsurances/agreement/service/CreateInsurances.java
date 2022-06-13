package sk.stuba.fei.oop.springinsurances.agreement.service;

import sk.stuba.fei.oop.springinsurances.agreement.life.Accident;
import sk.stuba.fei.oop.springinsurances.agreement.life.Travel;
import sk.stuba.fei.oop.springinsurances.agreement.nonlife.FlatInsurance;
import sk.stuba.fei.oop.springinsurances.agreement.nonlife.HomeInsurance;
import sk.stuba.fei.oop.springinsurances.user.Address;
import sk.stuba.fei.oop.springinsurances.user.User;

public interface CreateInsurances {
    Accident newAccidentInsurance(User insurer, User insured, boolean isEU, float permanentConsequences, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment);
    Travel newTravelInsurance(User insurer, User insured, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment, boolean dead, float reparation);
    HomeInsurance newHomeInsurance(User user, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment, float valueOfProperty, float valueOfDevices, int idxOfProperty, Address address);
    FlatInsurance newFlatInsurance(User user, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment, float valueOfProperty, float valueOfDevices, boolean garage, int idx, Address address);
}
