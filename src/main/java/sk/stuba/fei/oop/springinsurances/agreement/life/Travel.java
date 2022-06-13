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
public class Travel extends Agreement implements LifeInsurance {
    @Valid
    private User insured;
    private boolean dead;
    private String type = "Travel";
    private float reparation;
    private TravelEnum validity;
    private final List<String> validityList = new ArrayList<>(Arrays.asList("0. Slovakia", "1. World", "2. World + Slovakia"));

    public Travel(){super();}

    public Travel(User insurer, User insured, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment, boolean dead, float reparation) {
        super(insurer, amountOfInsuranceIndemnity, amountOfTheMonthlyPayment);
        setInsured(insured);
        this.dead = dead;
        setReparation(reparation);
        insurer.addAgreement(this);
    }

    public String getTypeOfInsurance(){
        return "Travel";
    }


    @Override
    public String toString() {
        return "Travel";
    }


    public boolean isDead() {
        return dead;
    }

    public float getReparation() {
        return reparation;
    }


    public User getInsured() {
        return insured;
    }

    public void setInsured(User insured) {
        if (insured == null){
            throw new IllegalArgumentException();
        }
        this.insured = insured;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setReparation(float reparation) {
        if (reparation < 0){
            throw new IllegalArgumentException();
        }
        this.reparation = reparation;
    }


}
