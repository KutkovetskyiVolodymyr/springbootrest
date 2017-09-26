package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;

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
    public Object addEmailValidation(@RequestBody AddEmailRequest addEmailRequest) {
        //проверка на заполнение json
        if(addEmailRequest.getContent()==null || addEmailRequest.getFrom()==null || addEmailRequest.getBcc()==null ||
                addEmailRequest.getCc()==null || addEmailRequest.getReply_to()==null ||addEmailRequest.getTo()==null ||
                addEmailRequest.getSubject()==null){
            String retur = "Введите все данные";
            return new ResponseEntity<String>(retur, HttpStatus.NO_CONTENT);
        }

        EmailValidation emailValidation = new EmailValidation();
        ContentJson contentJson = new ContentJson();

        //проверка,на то что запрашиваемый пользователь существует? (true)-проходим  : (false)-404
        if(userRepository.findUsersByName(addEmailRequest.getFrom())==null) {
            String retur = "Пользователя с именем "+addEmailRequest.getFrom()+" не существует";
            return new ResponseEntity<String>(retur, HttpStatus.NOT_FOUND);
        }
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

            //redirect на статус URL (http://localhost:8097/order/status/...)
            return new ResponseEntity<>(httpHeaders, HttpStatus.PERMANENT_REDIRECT);
    }
}
