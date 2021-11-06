package pack.rentinfo.domain;

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
import org.hibernate.annotations.ColumnDefault;

@Entity
@NoArgsConstructor
@Table(name = "rentinfo")
public class Rentinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rent_no")
    private Long rentNo;

    @Column(name = "rent_id")
    private String rentId;

    @Column(name = "rent_sdate")
    @Getter
    private String rentSdate;

    @Column(name = "rent_edate")
    private String rentEdate;

    @Column(name = "rent_ecount")
    @ColumnDefault("0")
    private int rentEcount;

    @Builder
    public Rentinfo(String rentId) {
        this.rentId = rentId;
        this.rentSdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.rentEdate = LocalDateTime.now().plusDays(13L).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}