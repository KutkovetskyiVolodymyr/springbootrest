package rest.repository;

import rest.entity.EmailValidation;
import java.util.LinkedList;
import java.util.List;


public class EmailValidationRepository {
    public List< EmailValidation>  validation;
   public EmailValidationRepository(){
        validation = new LinkedList<>();
    }


}
