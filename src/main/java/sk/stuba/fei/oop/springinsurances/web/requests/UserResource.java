package sk.stuba.fei.oop.springinsurances.web.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import sk.stuba.fei.oop.springinsurances.agreement.Agreement;
import sk.stuba.fei.oop.springinsurances.user.Address;
import sk.stuba.fei.oop.springinsurances.user.User;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResource extends User {
    @Size(min = 1)
    private String name;

    @Size(min = 1)
    private String surname;

    @Size(min = 1)
    private String identificationNumber;

    @Size(min = 1)
    @Email
    private String email;

    @Valid
    private Address address;

    private Address correspondenceAddress;
    private List<Agreement> list = new ArrayList<>();
    private List<AgreementResource> listResources = new ArrayList<>();


    public void addAgreement(AgreementResource agreement) {
        if (agreement == null){
            throw new IllegalArgumentException();
        }
        listResources.add(agreement);
    }


    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", identificationNumber='" + identificationNumber + '\'' +
                ", email='" + email;
    }
}
