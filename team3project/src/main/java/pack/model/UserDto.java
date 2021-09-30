package pack.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private String user_id, user_name, user_passwd, user_tel, user_addr, user_zip, user_mail, user_birth, user_penalty;
    private int user_rentcnt, user_point, user_dcount, delpoint, rfcount, pluspoint;

    @Builder
    public UserDto(String user_id, String user_name, String user_passwd, String user_tel,
        String user_addr, String user_zip, String user_mail, String user_birth) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_passwd = user_passwd;
        this.user_tel = user_tel;
        this.user_addr = user_addr;
        this.user_zip = user_zip;
        this.user_mail = user_mail;
        this.user_birth = user_birth;
    }

}
