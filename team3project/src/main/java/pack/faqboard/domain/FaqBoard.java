package pack.faqboard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@Table(name = "FAQBOARD")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class FaqBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAQ_NO")
    private Long faqNo;

    @Column(name = "FAQ_TITLE")
    private String faqTitle;

    @Column(name = "FAQ_CONTENT")
    private String faqContent;

    @Column(name = "FAQ_DATE")
    private String faqDate;

    @Column(name = "FAQ_TYPE")
    private String faqType;

    @PrePersist
    void onPrePersist() {
        this.faqDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Builder
    public FaqBoard(final Long faqNo, final String faqTitle, final String faqContent, final String faqType) {
        this.faqNo = faqNo;
        this.faqTitle = faqTitle;
        this.faqContent = faqContent;
        this.faqType = faqType;
    }
}