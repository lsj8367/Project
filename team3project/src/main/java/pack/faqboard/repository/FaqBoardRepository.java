package pack.faqboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.faqboard.domain.FaqBoard;

import java.util.List;

public interface FaqBoardRepository extends JpaRepository<FaqBoard, Long> {
    List<FaqBoard> findAllByFaqType(String faqType);
}
