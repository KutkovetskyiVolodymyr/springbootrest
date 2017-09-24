package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.entity.ContentJson;
import rest.entity.EmailValidation;
import rest.entity.request.AddEmailRequest;
import rest.repository.ContentJsonRepository;
import rest.repository.EmailValidationRepository;
import rest.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("order")
public class EmailController {

    private EmailValidationRepository emailValidationRepository;
    private ContentJsonRepository contentJsonRepository;



    @Autowired
    public EmailController(EmailValidationRepository emailValidationRepository, ContentJsonRepository contentJsonRepository) {
        this.emailValidationRepository = emailValidationRepository;
        this.contentJsonRepository=contentJsonRepository;
    }

    @RequestMapping( method = RequestMethod.GET)
    public List<EmailValidation> findAllEmail() {
        return emailValidationRepository.findAll();
    }

    @RequestMapping(produces="application/json",method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmailValidation> addEmailValidation(@RequestBody AddEmailRequest addEmailRequest) {
        EmailValidation emailValidation = new EmailValidation();
        ContentJson contentJson = new ContentJson();

        contentJson.setType(addEmailRequest.getContent().getType());
        contentJson.setValue(addEmailRequest.getContent().getValue());

        contentJsonRepository.save(contentJson);

        emailValidation.setCc(addEmailRequest.getCc());
        emailValidation.setBcc(addEmailRequest.getBcc());
        emailValidation.setFrom(addEmailRequest.getFrom());
        emailValidation.setReply_to(addEmailRequest.getReply_to());
        emailValidation.setSubject(addEmailRequest.getSubject());
        emailValidation.setTo(addEmailRequest.getTo());
        emailValidation.setContent(contentJson);

        emailValidationRepository.save(emailValidation);
        return new ResponseEntity<EmailValidation>(emailValidation, HttpStatus.OK);

    }

}
