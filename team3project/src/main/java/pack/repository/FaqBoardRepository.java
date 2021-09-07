package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.domain.entity.FaqBoard;

import java.util.List;

public interface FaqBoardRepository extends JpaRepository<FaqBoard, Long> {
    List<FaqBoard> findAllByFaqType(String faqType);
}
