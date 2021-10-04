package pack.inquery.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pack.inquery.domain.Inquery;

public interface InqueryRepository extends JpaRepository<Inquery, Long>, InqueryRepositorySupport{
    List<Inquery> findByInqIdOrderByInqNoAsc(String inqId);
    List<Inquery> findAllByInqNo(Long inqNo);
}