package pack.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long reviewNo;

    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "review_bookno")
    private long reviewBookno;

    @Column(name = "review_context")
    private String reviewContext;

    @Column(name = "review_date")
    private LocalDateTime reviewDate;

    @Column(name = "review_rdate")
    private long reviewRate;

    @Column(name = "review_gonggam")
    private long reviewGonggam;
}