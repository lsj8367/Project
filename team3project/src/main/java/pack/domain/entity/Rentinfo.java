package pack.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class Rentinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rent_no")
    private Long rentNo;

    @Column(name = "rent_id")
    private String rentId;

    @Column(name = "rent_sdate")
    private LocalDateTime rentSdate;

    @Column(name = "rent_edate")
    private LocalDateTime rentEdate;

    @Column(name = "rent_ecount")
    @ColumnDefault("0")
    private int rentEcount;
}