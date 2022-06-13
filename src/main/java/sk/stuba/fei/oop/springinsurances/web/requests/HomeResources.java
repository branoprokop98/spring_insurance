package sk.stuba.fei.oop.springinsurances.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sk.stuba.fei.oop.springinsurances.agreement.nonlife.HomeEnum;
import sk.stuba.fei.oop.springinsurances.user.Address;

import javax.validation.Valid;
import javax.validation.constraints.Min;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HomeResources extends AgreementResource{
    @Min(0)
    private float valueOfProperty;
    @Min(0)
    private float valueOfDevices;
    @Valid
    private Address address;
    private HomeEnum propertyType;
//    private LocalDateTime dateOfFormation;
//    private LocalDateTime start;
//    private LocalDateTime end;
//    private User insurer;
//    @Min(0)
//    private float amountOfInsuranceIndemnity;
//    @Min(0)
//    private float amountOfTheMonthlyPayment;
//    private HomeEnum property;
}
