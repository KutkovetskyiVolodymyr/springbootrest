package rest.controller;

import rest.repository.EmailValidationRepository;
import rest.repository.UserRepository;

public class ControllerJ {
    private UserRepository userRepository;
    EmailValidationRepository emailRepository;

    public ControllerJ(UserRepository userRepository, EmailValidationRepository emailRepository) {
        this.userRepository = userRepository;
        this.emailRepository = emailRepository;
    }

    public boolean findByUserName(String name) {
        if (userRepository.findUsersByName(name)){
            System.out.print(true);
          return true;
        }

        return false;
    }
}
