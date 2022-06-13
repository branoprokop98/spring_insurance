package sk.stuba.fei.oop.springinsurances.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sk.stuba.fei.oop.springinsurances.agreement.life.TravelEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelResources extends AgreementResource {
    //@Valid
    @NotNull
    private UserResource insured;
    private String type = "Travel";
    private boolean dead;
    @NotNull
    @Min(0)
    private float reparation;
    @NotNull
    private TravelEnum validity;
//    private LocalDateTime dateOfFormation;
//    @NotNull
//    private LocalDateTime start;
//    @NotNull
//    private LocalDateTime end;
//    @NotNull
//    private User insurer;
//    @Min(0)
//    private float amountOfInsuranceIndemnity;
//    @Min(0)
//    private float amountOfTheMonthlyPayment;
}
