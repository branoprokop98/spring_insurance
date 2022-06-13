package sk.stuba.fei.oop.springinsurances.service;

public interface Printable {
    void printSignedAgreements(long uuid);
    void printAllSignedAgreement();
    void printUserByUID(long uuid);
    void printAllUsers();
}
