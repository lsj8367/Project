package pack.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.ColumnDefault;

@Entity
public class User {

    @Id
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
}

