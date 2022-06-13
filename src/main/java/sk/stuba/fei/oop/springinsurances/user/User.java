package sk.stuba.fei.oop.springinsurances.user;

import lombok.Data;
import sk.stuba.fei.oop.springinsurances.agreement.Agreement;
import sk.stuba.fei.oop.springinsurances.web.requests.AgreementResource;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Data
public class User {
    @Size(min = 1)
    private String name;

    @Size(min = 1)
    private String surname;

    @Size(min = 1)
    private String identificationNumber;

    @Email
    private String email;

    @Valid
    private Address address;

    private Address correspondenceAddress;

    private long id;
    private static final AtomicLong generator = new AtomicLong(100);
    @NotNull
    private List<Agreement> list;
    @NotNull
    private List<AgreementResource> listResources;


    public User() {
        this.address = new Address();
        this.list = new ArrayList<>();
        this.listResources = new ArrayList<>();
    }

    public void setId(long id){
        this.id = id;
    }

    public void setID(){
        this.id = generator.getAndIncrement();
    }

    public void setList(List<Agreement> list) {
        this.list = list;
    }

    public User(String name, String surname, String identificationNumber, String email, Address address, Address corespondentAddress) {
        setAddress(address, corespondentAddress);
        setName(name);
        setSurname(surname);
        setIdentificationNumber(identificationNumber);
        setEmail(email);
        this.id = generator.getAndIncrement();
        list = new ArrayList<>();
    }

    public void setAddress(Address address, Address corespondentAddress){
        if (corespondentAddress != null) {
            this.address = corespondentAddress;
        }
        else {
            this.address = address;
        }
    }

    public Address getAddress() {
        return address;
    }

    public void addAgreement(Agreement agreement) {
        if (agreement == null){
            throw new IllegalArgumentException();
        }
        list.add(agreement);
    }

    public void addAgreement(AgreementResource agreement) {
        if (agreement == null){
            throw new IllegalArgumentException();
        }
        listResources.add(agreement);
    }

    public List<Agreement> getList() {
        return list;
    }

    public List<AgreementResource> getListResources() {
        return listResources;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name is null");
        }
        this.name = name;
    }

    public void setSurname(String surname) {
        if (surname == null) {
            throw new IllegalArgumentException("Name is null");
        }
        this.surname = surname;
    }

    public void setIdentificationNumber(String identificationNumber) {
        if (identificationNumber == null || identificationNumber.length() != 11) {
            throw new IllegalArgumentException("Identification number is empty or not correct size.");
        }
        boolean isNumeric = identificationNumber.substring(0, 5).chars().allMatch(Character::isDigit);
        boolean isNumeric1 = identificationNumber.substring(7, 10).chars().allMatch(Character::isDigit);
        if (isNumeric || isNumeric1) {
            this.identificationNumber = identificationNumber;
        } else {
            throw new IllegalArgumentException("Bad identification number");
        }
    }

    public void setEmail(String email) {
        if (!email.contains("@") && !email.contains(".")) {
            throw new IllegalArgumentException("Bad format of E-mail");
        }
        this.email = email;
    }


    public long getUid() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public String getEmail() {
        return email;
    }


//    @Override
//    public String toString() {
//        return "User ID: " + getUid() + "\n" + "User name: " + getName() + "\n" +
//                "User surname: " + getSurname() + "\n" + "User born number: " + getIdentificationNumber() +
//                "\n" + "User email: " + getEmail() + "\n" + "User address: " + address.getStreetName() +
//                " " + address.getHouseNumber() + " " + address.getPostNumber() + " " + address.getCityName();
//    }
}
