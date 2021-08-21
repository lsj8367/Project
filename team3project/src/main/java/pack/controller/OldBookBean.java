package pack.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OldBookBean {

	private String ob_name, ob_author, ob_inter, ob_genre, ob_comp, ob_bdate, ob_state, ob_donor, ob_comment, ob_image;
	private int ob_no, ob_price, ob_scount, ob_readcnt;
}
