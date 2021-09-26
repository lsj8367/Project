package pack.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pack.domain.entity.OldBook;

public interface OldBookRepository extends JpaRepository<OldBook, Long>, OldBookRepositorySupport {
    List<OldBook> findByObState(String obState);
    List<OldBook> findByObStateOrObState(String obState1, String obState2);
    OldBook findByObStateAndObNo(String obState, Long obNo);
}