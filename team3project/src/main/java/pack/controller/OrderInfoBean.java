package pack.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderInfoBean {

	private String orderlist_no, order_person, order_id, order_date, order_passwd, order_state, order_paytype, order_booktype, order_address;
	private int order_no, order_bookno, order_scount, order_sum;
}
