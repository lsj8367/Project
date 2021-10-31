package pack.oldbook.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pack.rentinfo.domain.Rentinfo;

@Getter
@Setter
@Entity
@Table(name = "oldbook")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class OldBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ob_no")
    private Long obNo;

    @Column(name = "ob_name")
    private String obName;

    @Column(name = "ob_author")
    private String obAuthor;

    @Column(name = "ob_inter")
    private String obInter;

    @Column(name = "ob_genre")
    private String obGenre;

    @Column(name = "ob_comp")
    private String obComp;

    @Column(name = "ob_bdate")
    private String obBdate;

    @Column(name = "ob_state")
    private String obState;

    @Column(name = "ob_price")
    private long obPrice;

    @Column(name = "ob_scount")
    private long obScount;

    @Column(name = "ob_readcnt")
    private long obReadcnt;

    @Column(name = "ob_donor")
    private String obDonor;

    @Column(name = "ob_comment")
    private String obComment;

    @Column(name = "ob_image")
    private String obImage;

    @Column(name = "ob_ddate")
    @CreatedDate
    private String obDdate;

    @Column(name = "ob_userid")
    private String obUserid;

    @JoinColumn(name = "rent_no")
    @OneToOne(fetch = FetchType.LAZY)
    private Rentinfo rentinfo;

    @PrePersist
    void onPrePersist() {
        this.obDdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Builder
    public OldBook(String obName, String obAuthor, String obInter, String obGenre, String obComp, LocalDateTime obBdate, String obState, long obPrice, long obScount, long obReadcnt, String obDonor, String obComment, String obImage,
        String obUserid, Rentinfo rentinfo) {
        this.obName = obName;
        this.obAuthor = obAuthor;
        this.obInter = obInter;
        this.obGenre = obGenre;
        this.obComp = obComp;
        this.obBdate = obBdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.obState = obState;
        this.obPrice = obPrice;
        this.obScount = obScount;
        this.obReadcnt = obReadcnt;
        this.obDonor = obDonor;
        this.obComment = obComment;
        this.obImage = obImage;
        this.obUserid = obUserid;
        this.rentinfo = rentinfo;
    }

    public static OldBook toEntity(Map<String, Object> map) {
        return OldBook.builder()
            .obName((String) map.get("ob_name"))
            .obAuthor((String) map.get("ob_author"))
            .obInter((String) map.get("ob_inter"))
            .obGenre((String) map.get("ob_genre"))
            .obComp((String) map.get("ob_comp"))
            .obBdate((LocalDateTime) map.get("ob_bdate"))
            .obPrice((Long) map.get("ob_price"))
            .obDonor((String) map.get("ob_donor"))
            .obComment((String) map.get("ob_comment"))
            .obUserid((String) map.get("ob_userid"))
            .build();
    }

}