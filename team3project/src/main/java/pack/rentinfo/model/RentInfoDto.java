package pack.rentinfo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RentInfoDto {

    private String rent_id, rent_sdate, rent_edate, ob_userid;
    private int rent_no, rent_ecount;
    private String ob_name, ob_author, ob_comp, user_name, user_id, ob_state, user_tel, user_mail;
    private int delpoint, delaydate, rmonth, rn, count;

}
