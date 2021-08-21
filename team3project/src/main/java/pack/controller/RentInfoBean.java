package pack.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RentInfoBean {
	private String rent_id, rent_sdate, rent_edate;
	private int rent_no, rent_ecount;
	private String ob_name, ob_author, ob_comp, user_name, user_id;
}
