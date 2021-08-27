package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.model.FaqBoardDto;
import pack.user.model.FaqDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class QnaPartController {
	private final FaqDao qnaDao;
	
	@RequestMapping("qnaPart")
	@ResponseBody
	public Map<String, Object> abc(@RequestParam("order") String qna_class){
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		
		Map<String, String> data = new HashMap<>();
		List<FaqBoardDto> qnaList = qnaDao.qnaListPart(qna_class);
		
		for(FaqBoardDto q:qnaList) {
			data.put("faq_no", Integer.toString(q.getFaq_no()));
			data.put("faq_title", q.getFaq_title());
			data.put("faq_content", q.getFaq_content());
			data.put("faq_date", q.getFaq_date());
			data.put("faq_type", q.getFaq_type());
			dataList.add(data);
		}
		Map<String, Object> qnaDatas = new HashMap<String, Object>();
		qnaDatas.put("datas", dataList);
		return qnaDatas;
	}

}
