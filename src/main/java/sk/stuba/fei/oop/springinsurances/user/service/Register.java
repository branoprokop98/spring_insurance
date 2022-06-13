package sk.stuba.fei.oop.springinsurances.user.service;


import sk.stuba.fei.oop.springinsurances.user.Address;
import sk.stuba.fei.oop.springinsurances.user.User;

public interface Register {
    //User register(String name, String surname, String identificationNumber, String email, String postNumber, String cityName, String streetName, String houseNumber);
    User register(String name, String surname, String identificationNumber, String email, Address address, Address corespondentAddress);
}
