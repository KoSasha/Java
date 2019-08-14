package ru.eltex;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserController {

    @Autowired
    public UserRepository crudRepository;

    @RequestMapping("/get_users")
    public List<User> getUsers() throws JsonProcessingException {
        List<User> usrs = new ArrayList<>();
        crudRepository.findAll().forEach(usrs::add);
        return usrs;
    }

    @RequestMapping("/get_user/{id}")
    public User getUser(@PathVariable("id") Integer id) {
        return crudRepository.findById(id);
    }
}
