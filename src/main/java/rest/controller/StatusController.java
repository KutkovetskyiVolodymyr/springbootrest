package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rest.entity.EmailValidation;
import rest.entity.User;
import rest.repository.ContentJsonRepository;
import rest.repository.EmailValidationRepository;
import rest.repository.UserRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("order/status")
public class StatusController {
    private EmailValidationRepository emailValidationRepository;
    private ContentJsonRepository contentJsonRepository;
    private UserRepository userRepository;

    int restrictionOutboundTrafficIMAPGmailDay=500;
    int oneDayMillis = 86400000;


    @Autowired
    public StatusController(EmailValidationRepository emailValidationRepository, ContentJsonRepository contentJsonRepositorym, UserRepository userRepository) {
        this.emailValidationRepository = emailValidationRepository;
        this.contentJsonRepository=contentJsonRepositorym;
        this.userRepository=userRepository;
    }

    @RequestMapping(value = "/{user}/{password}", method = RequestMethod.GET)
    public Object redirectStatusUser(@PathVariable("user") String user, @PathVariable("password") String password) {
        User userAsser =userRepository.findUsersByNameAndPassword(user,password);
        if(userAsser==null)
            return "Пользователь с такими данными отсутствует";
        List<EmailValidation> emailValidationStatus = emailValidationRepository.findAllByFromAndCreateDateBetween(user, new Date(System.currentTimeMillis() - oneDayMillis), new Date(System.currentTimeMillis()));

        int sizeList=1;
        sizeList= emailValidationStatus.size();
        if(sizeList==0)
            return "Вы еще не отправляли Email ";
        //Объем заказа
        long v=0;
        for (int i =0;i<=sizeList-1;i++) {
            v +=(long) emailValidationStatus.get(i).getContent().getValue().length() * (emailValidationStatus.get(i).getTo().length + emailValidationStatus.get(i).getCc().length + emailValidationStatus.get(i).getBcc().length);
        }

        if(restrictionOutboundTrafficIMAPGmailDay>=v) {
            long resul = (restrictionOutboundTrafficIMAPGmailDay*(1024*1024) - v) / (v /  sizeList);
            return "Вы еще можете отправлять Email в течении дня примерно: " + resul + " раз";
        }
        String retur = "Вы больше не можете Сегодня отправлять Email  ";
        return new ResponseEntity<String>(retur, HttpStatus.SERVICE_UNAVAILABLE);

    }
}
