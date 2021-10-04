package pack.admin.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_no")
    private Long adminNo;

    @Column(name = "admin_id")
    private String adminId;

    @Column(name = "admin_passwd")
    private String adminPassword;

    @Column(name = "admin_name")
    private String adminName;

    @Column(name = "admin_jik")
    private String adminJik;

    @Column(name = "admin_acc")
    private int adminAcc;

    @Builder
    public Admin(String adminId, String adminPassword, String adminName,
        String adminJik, int adminAcc) {
        this.adminId = adminId;
        this.adminPassword = adminPassword;
        this.adminName = adminName;
        this.adminJik = adminJik;
        this.adminAcc = adminAcc;
    }
}
