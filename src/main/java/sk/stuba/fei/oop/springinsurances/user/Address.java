package sk.stuba.fei.oop.springinsurances.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Address {
    @Size(min = 1)
    private String postNumber;
    @Size(min = 1)
    private String cityName;
    @Size(min = 1)
    private String streetName;
    @Size(min = 1)
    private String houseNumber;

//    public Address(String postNumber, String cityName, String streetName, String houseNumber) {
//        setPostNumber(postNumber);
//        setCityName(cityName);
//        setHouseNumber(houseNumber);
//        setStreetName(streetName);
//    }
//
//    public Address() {
//    }
//
//    public void setPostNumber(String postNumber) {
//        if (postNumber == null){
//            throw new IllegalArgumentException();
//        }
//        this.postNumber = postNumber;
//    }
//
//    public void setCityName(String cityName) {
//        if (cityName == null){
//            throw new IllegalArgumentException();
//        }
//        this.cityName = cityName;
//    }
//
//    public void setStreetName(String streetName) {
//        if (streetName == null){
//            throw new IllegalArgumentException();
//        }
//        this.streetName = streetName;
//    }
//
//    public void setHouseNumber(String houseNumber) {
//        if (houseNumber == null){
//            throw new IllegalArgumentException();
//        }
//        this.houseNumber = houseNumber;
//    }
//
//    public String getPostNumber() {
//        return postNumber;
//    }
//
//    public String getCityName() {
//        return cityName;
//    }
//
//    public String getStreetName() {
//        return streetName;
//    }
//
//    public String getHouseNumber() {
//        return houseNumber;
//    }
}
