package pack.admin.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminUpdateDto {

    private String adminId;
    private int adminAcc;
    private String adminJik;

    private AdminUpdateDto(String adminId, int adminAcc) {
        this.adminId = adminId;
        this.adminAcc = adminAcc;
    }

    private AdminUpdateDto(String adminId, String adminJik) {
        this.adminId = adminId;
        this.adminJik = adminJik;
    }

    public static AdminUpdateDto of(String adminId, int adminAcc) {
        return new AdminUpdateDto(adminId, adminAcc);
    }

    public static AdminUpdateDto of(String adminId, String adminJik) {
        return new AdminUpdateDto(adminId, adminJik);
    }
}
