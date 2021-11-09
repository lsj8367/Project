package pack.review.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "review")
@NoArgsConstructor
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "review_no")
    private Long reviewNo;

    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "review_bookno")
    private long reviewBookno;

    @Column(name = "review_context")
    private String reviewContext;

    @Column(name = "review_date")
    private String reviewDate;

    @Column(name = "review_rate")
    private long reviewRate;

    @Column(name = "review_gonggam")
    @Setter
    private long reviewGonggam;

    @Builder
    public Review(String reviewId, long reviewBookno, String reviewContext, long reviewRate, long reviewGonggam) {
        this.reviewId = reviewId;
        this.reviewBookno = reviewBookno;
        this.reviewContext = reviewContext;
        this.reviewDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.reviewRate = reviewRate;
        this.reviewGonggam = reviewGonggam;
    }

}