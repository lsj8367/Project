package pack.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import pack.cardinfo.domain.CardInfo;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_passwd")
    private String userPasswd;

    @Column(name = "user_tel")
    private String userTel;

    @Column(name = "user_addr")
    private String userAddr;

    @Column(name = "user_zip")
    private String userZip;

    @Column(name = "user_mail")
    private String userMail;

    @Column(name = "user_rentcnt")
    @ColumnDefault("0")
    private long userRentcnt;

    @Column(name = "user_point")
    @ColumnDefault("2000")
    private long userPoint;

    @Column(name = "user_birth")
    private String userBirth;

    @Column(name = "user_penalty")
    @ColumnDefault("0")
    private long userPenalty;

    @Column(name = "user_dcount")
    @ColumnDefault("0")
    private long userDcount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardInfo_id")
    private CardInfo cardInfo;

    @Builder
    public User(final String userId, final String userName, final String userPasswd, final String userTel, final String userAddr, final String userZip, final String userMail, final long userRentcnt, final long userPoint, final String userBirth, final long userPenalty, final long userDcount, final CardInfo cardInfo) {
        this.userId = userId;
        this.userName = userName;
        this.userPasswd = userPasswd;
        this.userTel = userTel;
        this.userAddr = userAddr;
        this.userZip = userZip;
        this.userMail = userMail;
        this.userRentcnt = userRentcnt;
        this.userPoint = userPoint;
        this.userBirth = userBirth;
        this.userPenalty = userPenalty;
        this.userDcount = userDcount;
        this.cardInfo = cardInfo;
    }
}

