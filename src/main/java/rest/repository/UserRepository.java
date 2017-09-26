package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rest.entity.EmailValidation;
import rest.entity.User;

import javax.jws.soap.SOAPBinding;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUsersByName(String name);
    public User findUsersByNameAndPassword(String name, String password);
}
