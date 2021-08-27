package pack.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.model.FaqBoardDto;
import pack.user.model.FaqDao;

@Controller
@RequiredArgsConstructor
public class CenterController {
	private final FaqDao faqDao;
	

	@GetMapping(value = "center")
	public String centerC() {
		return "center";
	}

	
	@PostMapping("qnaAll")
	@ResponseBody
	public Map<String, Object> qnaAll() {
		
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		Map<String, String> data = new HashMap<>();
		List<FaqBoardDto> qnaList = faqDao.qnaListAll();
		
		for(FaqBoardDto q:qnaList) {
			data.put("faq_no", Integer.toString(q.getFaq_no()));
			data.put("faq_date", q.getFaq_date());
			data.put("faq_title", q.getFaq_title());
			data.put("faq_cont", q.getFaq_content());
			data.put("faq_type", q.getFaq_type());
			dataList.add(data);
		}
		Map<String, Object> qnaDatas = new HashMap<String, Object>();
		qnaDatas.put("datas", dataList);
		return qnaDatas;
	}
	
	@PostMapping("faqDetail")
	@ResponseBody
	public Map<String, Object> faqDetail(@RequestParam("no") String faq_no){
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		Map<String, String> data = new HashMap<>();
		List<FaqBoardDto> faqList = faqDao.faqDetail(faq_no);
		
		for(FaqBoardDto f:faqList) {
			data.put("faq_no", faq_no);
			data.put("faq_title", f.getFaq_title());
			data.put("faq_content", f.getFaq_content());
			data.put("faq_date", f.getFaq_date());
			data.put("faq_type", f.getFaq_type());

			dataList.add(data);
		}
		Map<String, Object> faqDatas = new HashMap<String, Object>();
		faqDatas.put("datas", dataList);
		return faqDatas;
	}
	
	@PostMapping("qnaOrder")
	@ResponseBody
	public Map<String, Object> abc(@RequestParam("faq_type") String faq_type){
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		
		Map<String, String> data = new HashMap<>();
		List<FaqBoardDto> qnaList = faqDao.qnaListPart(faq_type);
		
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
	
	@PostMapping("centerpage")
	public String centerPage(@RequestParam("page") String a, Model model) {

		String returnJsp = "";
		switch (a) {
			case "order":
				model.addAttribute("page", a);
				returnJsp = "centerOrder";
			case "deliver":
				model.addAttribute("page", a);
				returnJsp = "centerDeliver";
			case "product":
				model.addAttribute("page", a);
				returnJsp = "centerProduct";
			default:
				returnJsp = "qnaAll";
		}

		return returnJsp;
	}
	
	
}