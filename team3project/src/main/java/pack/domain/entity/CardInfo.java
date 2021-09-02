package pack.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class CardInfo {
  @Id
  @Column(name = "card_ownerid")
  private String cardOwnerid;

  @Column(name = "card_owner")
  private String cardOwner;

  @Column(name = "card_comp")
  private String cardComp;

  @Column(name = "card_no")
  private String cardNo;

  @Column(name = "card_passwd")
  private String cardPasswd;
}
