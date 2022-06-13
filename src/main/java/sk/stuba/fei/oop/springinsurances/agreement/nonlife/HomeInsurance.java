package sk.stuba.fei.oop.springinsurances.agreement.nonlife;

import lombok.Data;
import sk.stuba.fei.oop.springinsurances.agreement.Agreement;
import sk.stuba.fei.oop.springinsurances.user.Address;
import sk.stuba.fei.oop.springinsurances.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class HomeInsurance extends Agreement implements NonLifeInsurance {
    private final List<String> typesOfProperty =new ArrayList<>(Arrays.asList("0. Flat", "1. Brick house", "2. Wooden house"));
    private float valueOfProperty;
    private float valueOfDevices;
    private final String property;
    private Address address;
    private HomeEnum propertyType;

    public HomeInsurance(User user, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment, float valueOfProperty, float valueOfDevices, int idxOfProperty, Address address) {
        super(user, amountOfInsuranceIndemnity, amountOfTheMonthlyPayment);
        setAddress(address);
        this.property = getTypeOfProperty(idxOfProperty);
        setValueOfProperty(valueOfProperty);
        setValueOfDevices(valueOfDevices);
        user.addAgreement(this);
    }

    public void setAddress(Address address) {
        if (address == null){
            throw new IllegalArgumentException();
        }
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public float getValueOfProperty() {
        return valueOfProperty;
    }

    public float getValueOfDevices() {
        return valueOfDevices;
    }

    public String getProperty() {
        return property;
    }

    @Override
    public String getTypeOfProperty(int idx) {
        if (idx > this.typesOfProperty.size()){
            throw new IndexOutOfBoundsException();
        }
        return this.typesOfProperty.get(idx);
    }

    @Override
    public void setValueOfProperty(float valueOfProperty) {
        if (valueOfProperty < 0){
            throw new IllegalArgumentException();
        }
        this.valueOfProperty = valueOfProperty;
    }

    public void setValueOfDevices(float valueOfDevices) {
        if (valueOfDevices < 0){
            throw new IllegalArgumentException();
        }
        this.valueOfDevices = valueOfDevices;
    }

    @Override
    public String toString() {
        return "Home Insurance";
    }
}
