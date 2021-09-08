package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.domain.entity.Inquery;

public interface InqueryRepository extends JpaRepository<Inquery, Long>, InqueryRepositorySupport{
}