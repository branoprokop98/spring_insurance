package sk.stuba.fei.oop.springinsurances.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sk.stuba.fei.oop.springinsurances.agreement.life.AccidentEnum;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccidentResources extends AgreementResource {
    private UserResource insured;
    private String type = "Accident";
    private boolean isEU;
    @NotNull
    @Min(0)
    private float permanentConsequences;
    @NotNull
    private AccidentEnum purpose;

}
