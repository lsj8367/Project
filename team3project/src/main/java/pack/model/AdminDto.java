package pack.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminDto {

	private String admin_id, admin_passwd, admin_name, admin_jik;
	private int admin_no, admin_acc;
}
