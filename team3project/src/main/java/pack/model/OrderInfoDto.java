package pack.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderInfoDto {

	private String orderlist_no, order_person, order_id, order_bookname, order_date, order_passwd, order_paytype, order_booktype, order_state, order_address, ob_name, nb_name, order_month;
	private int order_no, order_bookno, order_scount, order_delay, order_profit, order_count, month_scount, order_sum;
	private String user_id, user_tel, user_mail; 
	private int omonth, sum, count, rn;
	
}
