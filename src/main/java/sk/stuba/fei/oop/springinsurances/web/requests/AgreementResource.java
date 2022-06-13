package sk.stuba.fei.oop.springinsurances.web.requests;

import lombok.Data;
import sk.stuba.fei.oop.springinsurances.user.User;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;


@Data
public class AgreementResource {
    @PastOrPresent
    private LocalDateTime dateOfFormation;
    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;

    private User insurer;

    @Min(0)
    private float amountOfInsuranceIndemnity;

    @Min(0)
    private float amountOfTheMonthlyPayment;

    private static final AtomicLong generator = new AtomicLong(10);
    private  long agreementID;

    public void setAgreementID(){
        agreementID = generator.getAndIncrement();
    }
}
