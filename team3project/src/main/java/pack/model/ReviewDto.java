package pack.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

	private int review_no, review_bookno, review_rate, review_gonggam,nb_no, review_month, rn;
	private String review_id, review_context, review_date, review_name;
}
