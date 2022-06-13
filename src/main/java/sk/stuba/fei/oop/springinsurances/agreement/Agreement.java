package sk.stuba.fei.oop.springinsurances.agreement;

import sk.stuba.fei.oop.springinsurances.user.User;
import sk.stuba.fei.oop.springinsurances.web.requests.AgreementResource;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Agreement extends AgreementResource {
    private  LocalDateTime dateOfFormation;
    private LocalDateTime start;
    private LocalDateTime end;
    private User insurer;
    private float amountOfInsuranceIndemnity;
    private float amountOfTheMonthlyPayment;
    private static final AtomicLong generator = new AtomicLong(10);
    private  long agreementID;

    public Agreement(){

    }

    public long getAgreementID() {
        return agreementID;
    }

    public Agreement(User insurer, float amountOfInsuranceIndemnity, float amountOfTheMonthlyPayment) {
        setInsurer(insurer);
        this.dateOfFormation = LocalDateTime.now();
        this.start = LocalDateTime.now();
        this.end = LocalDateTime.of(2022, Month.DECEMBER, 31, 0, 0);
        this.agreementID = generator.getAndIncrement();
        setAmountOfInsuranceIndemnity(amountOfInsuranceIndemnity);
        setAmountOfTheMonthlyPayment(amountOfTheMonthlyPayment);
    }

    public void setID(){
        this.agreementID = generator.getAndIncrement();
    }


    public void setAmountOfInsuranceIndemnity(float amountOfInsuranceIndemnity) {
        if (amountOfInsuranceIndemnity < 0) {
            throw new IllegalArgumentException("The amount of insurance indemnity is negative");
        }
        this.amountOfInsuranceIndemnity = amountOfInsuranceIndemnity;
    }

    public void setAmountOfTheMonthlyPayment(float amountOfTheMonthlyPayment) {
        if (amountOfTheMonthlyPayment < 0) {
            throw new IllegalArgumentException("The amount of the monthly payment is negative");
        }
        this.amountOfTheMonthlyPayment = amountOfTheMonthlyPayment;
    }


    public void setInsurer(User insurer){
//        if(insurer == null){
//            throw new IllegalArgumentException();
//        }
        this.insurer = insurer;
    }
    public User getInsurer(){
        return this.insurer;
    }

    public void setEnd(LocalDateTime end){
        this.end = end;
    }


    public long getUid() {
        return agreementID;
    }

    public LocalDateTime getDateOfFormation() {
        return dateOfFormation;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public float getAmountOfInsuranceIndemnity() {
        return amountOfInsuranceIndemnity;
    }

    public float getAmountOfTheMonthlyPayment() {
        return amountOfTheMonthlyPayment;
    }
}
