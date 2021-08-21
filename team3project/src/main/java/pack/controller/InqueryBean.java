package pack.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InqueryBean {

	private int inq_no, inq_gnum, inq_onum, inq_nested;
	private String inq_title, inq_context, inq_ddate, inq_id;
}
