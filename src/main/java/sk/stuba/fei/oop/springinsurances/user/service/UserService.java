package sk.stuba.fei.oop.springinsurances.user.service;

import org.springframework.stereotype.Service;
import sk.stuba.fei.oop.springinsurances.user.Address;
import sk.stuba.fei.oop.springinsurances.user.User;
import sk.stuba.fei.oop.springinsurances.web.requests.UserResource;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements Register{
    private final Map<Long, User> users;
    private final Map<Long, UserResource> usersResources;

    public UserService() {
        users = new HashMap<>();
        this.usersResources = new HashMap<>();
    }


    @Override
    public User register(String name, String surname, String identificationNumber, String email, Address address, Address corespondentAddress) {
        User user = new User(name, surname, identificationNumber, email, address, corespondentAddress);
        users.put(user.getUid(), user);
        return user;
    }

    public Map<Long, UserResource> getUsersResources() {
        return usersResources;
    }

    public User findById(long id){
        return users.get(id);
    }

    public User editUser(long id){
            return users.get(id);
//        user.setAddress(address, null);
//        user.setEmail(email);
//        user.setName(name);
//        user.setSurname(surname);
    }

    public void editUser(long id, String name, String surname, String email, Address address, Address corespondentAddress){
        User user = users.get(id);
        user.setAddress(address, corespondentAddress);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
    }


    public Map<Long, User> getUsers() {
        return users;
    }

    public Map<Long, UserResource> getUserResource(){
        return usersResources;
    }

}
