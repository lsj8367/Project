package pack.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewBean {

	private int review_no, review_bookno, review_rate, review_gonggam;
	private String review_id, review_context, review_date;
}
