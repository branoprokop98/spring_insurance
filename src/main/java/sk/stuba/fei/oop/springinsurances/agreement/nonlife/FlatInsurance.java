package sk.stuba.fei.oop.springinsurances.agreement.nonlife;

import lombok.Data;
import sk.stuba.fei.oop.springinsurances.agreement.Agreement;
import sk.stuba.fei.oop.springinsurances.user.Address;
import sk.stuba.fei.oop.springinsurances.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class FlatInsurance extends Agreement implements NonLifeInsurance {

    private final List<String> typeOfFlats = new ArrayList<>(Arrays.asList("0. Flat", "1. Brick house", "2. Wooden house"));
    private float valueOfProperty;
    private float valueOfDevices;
    private boolean isGarage;
    private Address address;
    private FlatEnum flats;
    private final String flatType;

    public FlatInsurance(User user, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment, float valueOfProperty, float valueOfDevices, boolean garage, int idx, Address address) {
        super(user, amountOfInsuranceIndemnity, amountOfTheMonthlyPayment);
        setValueOfProperty(valueOfProperty);
        setValueOfDevices(valueOfDevices);
        setGarage(garage);
        setAddress(address);
        this.flatType = getTypeOfProperty(idx);
        user.addAgreement(this);
    }

    public String getFlatType() {
        return flatType;
    }

    public void setAddress(Address address) {
        if (address == null){
            throw new IllegalArgumentException();
        }
        this.address = address;
    }

    public float getValueOfProperty() {
        return valueOfProperty;
    }

    public float getValueOfDevices() {
        return valueOfDevices;
    }

    public Address getAddress() {
        return address;
    }

    public void setValueOfProperty(float valueOfProperty) {
        if (valueOfProperty < 0) {
            throw new IllegalArgumentException();
        }
        this.valueOfProperty = valueOfProperty;
    }

    @Override
    public String getTypeOfProperty(int idx) {
        return typeOfFlats.get(idx);
    }

    public void setValueOfDevices(float valueOfDevices) {
        if (valueOfDevices < 0) {
            throw new IllegalArgumentException();
        }
        this.valueOfDevices = valueOfDevices;
    }

    public boolean isGarage() {
        return isGarage;
    }

    public void setGarage(boolean garage) {
        isGarage = garage;
    }

    @Override
    public String toString() {
        return "Flat Insurance";
    }
}
