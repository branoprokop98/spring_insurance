package sk.stuba.fei.oop.springinsurances.agreement.life;

import lombok.Data;
import lombok.EqualsAndHashCode;
import sk.stuba.fei.oop.springinsurances.agreement.Agreement;
import sk.stuba.fei.oop.springinsurances.user.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Accident extends Agreement implements LifeInsurance {
    @Valid
    private User insured;
    private boolean isEU;
    private String type = "Accident";
    private float permanentConsequences;
    private AccidentEnum purpose;
    private final List<String> listOfPurposes = new ArrayList<>(Arrays.asList("0. Work", "1. Recreation", "2. Sport"));

    public Accident() {
        super();
    }

    public Accident(User insurer, User insured, boolean isEU, float permanentConsequences, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment) {
        super(insurer, amountOfInsuranceIndemnity, amountOfTheMonthlyPayment);
        setInsured(insured);
        setEU(isEU);
        setPermanentConsequences(permanentConsequences);
        insurer.addAgreement(this);
    }

    public String getTypeOfInsurance(){
        return "Accident";
    }


    public void setEU(boolean EU) {
        isEU = EU;
    }

    @Override
    public String toString() {
        return "Accident";
    }

    public User getInsured() {
        return insured;
    }

    public boolean isEU() {
        return isEU;
    }

    public float getPermanentConsequences() {
        return permanentConsequences;
    }

    public void setInsured(User insured) {
        if (insured == null){
            throw new IllegalArgumentException();
        }
        this.insured = insured;
    }

    public void setPermanentConsequences(float permanentConsequences) {
        if (permanentConsequences < 0){
            throw new IllegalArgumentException();
        }
        this.permanentConsequences = permanentConsequences;
    }

}
