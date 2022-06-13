package sk.stuba.fei.oop.springinsurances.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.oop.springinsurances.agreement.nonlife.FlatEnum;
import sk.stuba.fei.oop.springinsurances.user.Address;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlatResources extends AgreementResource{
    @Min(0)
    private float valueOfProperty;
    @Min(0)
    private float valueOfDevices;
    private boolean isGarage;
    @Valid
    private Address address;
    private FlatEnum flats;
//    private LocalDateTime dateOfFormation;
//    private LocalDateTime start;
//    private LocalDateTime end;
//    private User insurer;
//    @Min(0)
//    private float amountOfInsuranceIndemnity;
//    @Min(0)
//    private float amountOfTheMonthlyPayment;
}
