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
public class Inquery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "inq_no")
    private Long inqNo;

    @Column(name = "inq_title")
    private String inqTitle;

    @Column(name = "inq_context")
    private String inqContext;

    @Column(name = "inq_ddate")
    private LocalDateTime inqDdate;

    @Column(name = "inq_id")
    private String inqId;

    @Column(name = "inq_gnum")
    private int inqGnum;

    @Column(name = "inq_onum")
    private int inqOnum;

    @Column(name = "inq_nested")
    private int inqNested;
}