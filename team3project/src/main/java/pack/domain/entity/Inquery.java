package pack.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Inquery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inq_no")
    private Long inqNo;

    @Column(name = "inq_title")
    private String inqTitle;

    @Column(name = "inq_context")
    private String inqContext;

    @Column(name = "inq_ddate")
    @CreatedDate
    private LocalDateTime inqDdate;

    @Column(name = "inq_id")
    private String inqId;

    @Column(name = "inq_gnum")
    private int inqGnum;

    @Column(name = "inq_onum")
    private int inqOnum;

    @Column(name = "inq_nested")
    private int inqNested;

    @Builder
    public Inquery(final Long inqNo, final String inqTitle, final String inqContext, final String inqId, final int inqGnum, final int inqOnum, final int inqNested) {
        this.inqNo = inqNo;
        this.inqTitle = inqTitle;
        this.inqContext = inqContext;
        this.inqId = inqId;
        this.inqGnum = inqGnum;
        this.inqOnum = inqOnum;
        this.inqNested = inqNested;
    }
}