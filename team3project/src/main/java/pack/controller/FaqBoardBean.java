package pack.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FaqBoardBean {

	private int faq_no;
	private String faq_date, faq_title, faq_content, faq_type;
}
