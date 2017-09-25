package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rest.entity.EmailValidation;
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

    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public Object redirectStatusUser(@PathVariable("user") String user) {
        List<EmailValidation> emailValidationStatus = emailValidationRepository.findAllByFromAndCreateDateBetween(user, new Date(System.currentTimeMillis() - oneDayMillis), new Date(System.currentTimeMillis()));
        int sizeList=1;
        try {
            sizeList= emailValidationStatus.size();
        }catch (ArithmeticException e){
            System.out.print("0");
            return "Вы еще не отправляли Email";
        }
        //Объем заказа
        long v=0;
        for (int i =0;i<=sizeList-1;i++) {
            v +=(long) emailValidationStatus.get(i).getContent().getValue().length() * (emailValidationStatus.get(i).getTo().length + emailValidationStatus.get(i).getCc().length + emailValidationStatus.get(i).getBcc().length);
        }

        if(restrictionOutboundTrafficIMAPGmailDay>=v) {
            long center = (v / (long) sizeList);
            long resul = (restrictionOutboundTrafficIMAPGmailDay*(1024*1024) - v) / center;
            return "Вы еще можете отправлять Email в течении дня примерно: " + resul + " раз";
        }
        String retur = "Вы больше не можете Сегодня отправлять Email  "+v;
        return new ResponseEntity<String>(retur, HttpStatus.SERVICE_UNAVAILABLE);

    }
}
