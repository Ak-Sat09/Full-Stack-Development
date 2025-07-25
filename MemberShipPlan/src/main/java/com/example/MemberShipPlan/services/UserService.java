package com.example.MemberShipPlan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MemberShipPlan.dtos.UserDto;
import com.example.MemberShipPlan.entities.User;
import com.example.MemberShipPlan.exceptions.UserNotFoundException;
import com.example.MemberShipPlan.repositories.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    public String save(UserDto user) {
        User userToSave = new User();
        userToSave.setName(user.getName());
        userToSave.setEmail(user.getEmail());

        repo.save(userToSave);
        return "Saved";
    }

    public List<User> getAllUsers(User user) {
        return repo.findAll();
    }

    public User getById(Long id) throws UserNotFoundException {
        return repo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " not found"));
    }

    public String deleteAllUsers(User user) {
        repo.deleteAll();
        return "All User Deleted Successfully";
    }

    public String DeleteById(User user) {
        repo.deleteById(user.getId());
        return "User" + user.getId() + "Deleted Successfully";
    }

}
