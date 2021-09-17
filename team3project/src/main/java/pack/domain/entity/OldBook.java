package pack.domain.entity;

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

@Getter
@Setter
@Entity
@Table(name = "oldbook")
@NoArgsConstructor
public class OldBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String obDdate;

    @Column(name = "ob_userid")
    private String obUserid;

    @Builder
    public OldBook(String obName, String obAuthor, String obInter, String obGenre, String obComp, LocalDateTime obBdate, String obState, long obPrice, long obScount, long obReadcnt, String obDonor, String obComment, String obImage,
        LocalDateTime obDdate, String obUserid) {
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
        this.obDdate = obDdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.obUserid = obUserid;
    }

}