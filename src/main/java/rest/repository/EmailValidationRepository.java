package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.entity.EmailValidation;

import java.util.Date;
import java.util.List;

public interface EmailValidationRepository extends JpaRepository<EmailValidation, Long> {

    public List<EmailValidation> findAllByFromAndCreateDateBetween(String from,Date first, Date second);

}
