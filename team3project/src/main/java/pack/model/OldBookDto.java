package pack.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OldBookDto {

	private String ob_name, ob_author, ob_inter, ob_genre, ob_comp, ob_bdate, ob_state, ob_donor, ob_comment, ob_image, ob_ddate, ob_userid, user_id,user_name;
	private int ob_no, ob_price, ob_scount, ob_readcnt;
}
