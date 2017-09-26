package rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.entity.User;
import rest.entity.request.AddUserRequest;
import rest.repository.UserRepository;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(produces="application/json",method =RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object addNewWebUser(@RequestBody AddUserRequest addUserRequest) {
        if(addUserRequest.getName()==null || addUserRequest.getPassword()==null){
            String retur = "Введите все данные";
            return new ResponseEntity<String>(retur, HttpStatus.NO_CONTENT);
        }

        User user = new User();
        user.setName(addUserRequest.getName());
        user.setPassword(addUserRequest.getPassword());
        userRepository.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
