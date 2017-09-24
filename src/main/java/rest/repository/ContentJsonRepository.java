package rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.entity.ContentJson;

public interface ContentJsonRepository extends JpaRepository<ContentJson,Long> {
}
