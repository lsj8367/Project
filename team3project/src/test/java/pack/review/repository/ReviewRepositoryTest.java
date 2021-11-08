package pack.review.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import pack.config.QuerydslConfig;
import pack.review.domain.Review;

@DataJpaTest
@Import(QuerydslConfig.class)
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void findAllByDesc() {
        Review review = Review.builder().build();
        reviewRepository.saveAndFlush(review);
        reviewRepository.findAll(Sort.by(Direction.DESC, "reviewNo"));
    }

}