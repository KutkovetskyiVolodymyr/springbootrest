package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import rest.entity.ContentJson;
import rest.entity.EmailValidation;
import rest.entity.request.AddEmailRequest;
import rest.repository.ContentJsonRepository;
import rest.repository.EmailValidationRepository;
import rest.repository.UserRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("order")
public class EmailController {

    private EmailValidationRepository emailValidationRepository;
    private ContentJsonRepository contentJsonRepository;
    private UserRepository userRepository;


    @Autowired
    public EmailController(EmailValidationRepository emailValidationRepository, ContentJsonRepository contentJsonRepositorym, UserRepository userRepository) {
        this.emailValidationRepository = emailValidationRepository;
        this.contentJsonRepository=contentJsonRepositorym;
        this.userRepository=userRepository;
    }

    @RequestMapping( method = RequestMethod.GET)
    public List<EmailValidation> findAllEmail() {
        return emailValidationRepository.findAll();
    }


    @RequestMapping(produces="application/json",method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object/*ResponseEntity<EmailValidation>*/ addEmailValidation(@RequestBody AddEmailRequest addEmailRequest) {
        EmailValidation emailValidation = new EmailValidation();
        ContentJson contentJson = new ContentJson();

        //проверка,на то что запрашиваемый пользователь существует? (true)-проходим  : (false)-404
        if(userRepository.findUsersByName(addEmailRequest.getFrom())!=null) {
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
            emailValidation.setCreateDate(new Date(System.currentTimeMillis()));
            emailValidationRepository.save(emailValidation);


            URI status = null;
            try {
                status = new URI("http://localhost:8097/order/status/"+emailValidation.getFrom());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(status);

            //redirect на статус URL ()
            return new ResponseEntity<>(httpHeaders, HttpStatus.PERMANENT_REDIRECT);
        }
        return new ResponseEntity<AddEmailRequest>(addEmailRequest, HttpStatus.NOT_FOUND);
    }




}
