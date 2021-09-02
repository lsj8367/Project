package pack.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Oldbook {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ob_no")
  private long obNo;

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
  private LocalDateTime obBdate;

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
  private LocalDateTime obDdate;

  @Column(name = "ob_userid")
  private String obUserid;
}