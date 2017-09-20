package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.entity.EmailValidation;
import rest.repository.EmailValidationRepository;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("order")
public class EmailController {

    private EmailValidationRepository emailRepository = new EmailValidationRepository();
    private int num=0;

    @RequestMapping( method = RequestMethod.GET)
    public List<EmailValidation> findAllEmail() {
        return emailRepository.validation;
    }

    @RequestMapping(produces="application/json",method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmailValidation> addEmailValidation(@RequestBody EmailValidation emailValidation) {
        emailRepository.validation.add(emailValidation);
        return new ResponseEntity<EmailValidation>(emailValidation, HttpStatus.OK);
    }

}
