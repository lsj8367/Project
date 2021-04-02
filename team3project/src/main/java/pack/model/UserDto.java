package pack.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

	private String user_id, user_name, user_passwd, user_tel, user_addr, user_zip, user_mail, user_birth, user_penalty;
	private int user_rentcnt, user_point, user_dcount, delpoint, rfcount, pluspoint;
}
