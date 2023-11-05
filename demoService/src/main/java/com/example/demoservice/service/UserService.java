package com.example.demoservice.service;

import com.example.demoservice.entity.User;
import com.example.demoservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    //Save operation
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //Read operation
    public List<User> fetchUserList() {
        return (List<User>) userRepository.findAll();
    }

    //UpdateOperation
    public User updateUser(User user, Integer id) {

        User userDB = userRepository.findById(id).get();

        if (Objects.nonNull(user.getUsername()) && !"".equalsIgnoreCase(user.getUsername())) {
            userDB.setUsername(user.getUsername());
        }

        if (Objects.nonNull(user.getBirthDate()) && !"".equalsIgnoreCase(user.getBirthDate())) {
            userDB.setBirthDate(user.getBirthDate());
        }

        return userRepository.save(userDB);
    }

    //Check if this id already has more than 3 prenotations
    public boolean userCheck(Integer id){
        User userDB = userRepository.findById(id).get();
        if(userDB.countSet()>=3){
            return false; //L' utente ha già troppe prenotazioni
        }
        else{
            return true;
        }
    }

    //Delete operation

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }


}
