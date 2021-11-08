package pack.review.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pack.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByReviewBooknoOrderByReviewDateDesc(long reviewBookno);

}