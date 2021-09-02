package pack.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "admin_no")
    private Long adminNo;

    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "admin_password")
    private String adminPassword;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "admin_jik")
    private String adminJik;

    @Column(name = "admin_acc")
    private String adminAcc;

    @Builder
    public Admin(String adminId, String adminPassword, String adminName,
        String adminJik, String adminAcc) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.adminJik = adminJik;
        this.adminAcc = adminAcc;
    }
}
