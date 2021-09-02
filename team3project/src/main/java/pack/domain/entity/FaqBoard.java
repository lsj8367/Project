package pack.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FaqBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FAQ_NO")
    private Long faqNo;

    @Column(name = "FAQ_TITLE")
    private String faqTitle;

    @Column(name = "FAQ_CONTENT")
    private String faqContent;

    @Column(name = "FAQ_DATE")
    private LocalDateTime localDateTime;

    @Column(name = "FAQ_TYPE")
    private String faqType;
}