package pack.faqboard.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FaqBoardDto {

	private int faq_no;
	private String faq_date, faq_title, faq_content, faq_type;
}
