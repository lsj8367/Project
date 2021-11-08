package pack.review.service;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.review.domain.Review;
import pack.review.repository.ReviewRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void insert(Review review) {
        reviewRepository.save(review);
    }

    public Review updateSympathy(int reviewNo) {
        final Review review = findById(reviewNo);
        review.setReviewGonggam(review.getReviewGonggam() + 1);
        return review;
    }

    public long delete(String reviewNo) {
        final Review review = findById(Long.parseLong(reviewNo));
        reviewRepository.deleteById(Long.valueOf(reviewNo));
        return review.getReviewBookno();
    }

    private Review findById(long reviewNo) {
        return reviewRepository.findById(reviewNo)
            .orElseThrow(() -> new RuntimeException("해당하는 리뷰가 없습니다."));
    }

    public List<Review> findAllByBookNo(int bookNo) {
        return reviewRepository.findAllByReviewBooknoOrderByReviewDateDesc(bookNo);
    }

}
