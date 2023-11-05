package com.example.demoservice.controller;

import com.example.demoservice.entity.User;
import com.example.demoservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //Save operation
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public User saveUser(@Valid @RequestBody User user){
        return userService.saveUser(user);
    }


    //read operation
    @RequestMapping(value = "fetch", method = RequestMethod.GET)
    public List<User> fetchUserList(){
       return userService.fetchUserList();

    }

    //update operation
    @RequestMapping(value = "update/{id:.+}",method = RequestMethod.POST)
    public User updateUser(@RequestBody User user, @PathVariable("id")Integer id){
       return userService.updateUser(user,id);

    }

    //delete operation
    @RequestMapping(value = "delete/{id:.+}")
    public String deleteUser(@PathVariable("id")Integer id){
        userService.deleteUserById(id);
        return "Delete successfully";
    }

}
