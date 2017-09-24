package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.entity.EmailValidation;

public interface EmailValidationRepository extends JpaRepository<EmailValidation, Long> {

}
