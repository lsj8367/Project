package pack.cardinfo.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cardinfo")
@Getter
@NoArgsConstructor
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

    @Builder
    public CardInfo(final String cardOwnerid, final String cardOwner, final String cardComp, final String cardNo, final String cardPasswd) {
        this.cardOwnerid = cardOwnerid;
        this.cardOwner = cardOwner;
        this.cardComp = cardComp;
        this.cardNo = cardNo;
        this.cardPasswd = cardPasswd;
    }
}
