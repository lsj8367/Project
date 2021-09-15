package pack.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.domain.entity.FaqBoard;
import pack.repository.FaqBoardRepository;

@Controller
@RequiredArgsConstructor
public class QnaPartController {
	private final FaqBoardRepository faqBoardRepository;

	
	@RequestMapping("qnaPart")
	@ResponseBody
	public Map<String, Object> abc(@RequestParam("order") String qna_class){
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		
		Map<String, String> data = new HashMap<>();
		List<FaqBoard> qnaList = faqBoardRepository.findAllByFaqType(qna_class);

		qnaList.forEach(faqBoard -> {
			data.put("faq_no", String.valueOf(faqBoard.getFaqNo()));
			data.put("faq_title", faqBoard.getFaqTitle());
			data.put("faq_content", faqBoard.getFaqContent());
			data.put("faq_date", faqBoard.getFaqDate());
			data.put("faq_type", faqBoard.getFaqType());
			dataList.add(data);
		});

		Map<String, Object> qnaDatas = new HashMap<String, Object>();
		qnaDatas.put("datas", dataList);
		return qnaDatas;
	}

}
