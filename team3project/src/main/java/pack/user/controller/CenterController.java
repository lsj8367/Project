package pack.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.domain.entity.FaqBoard;
import pack.repository.FaqBoardRepository;

@Controller
@RequiredArgsConstructor
public class CenterController {
	private final FaqBoardRepository faqBoardRepository;
	

	@GetMapping(value = "center")
	public String centerC() {
		return "center";
	}

	
	@PostMapping("qnaAll")
	@ResponseBody
	public Map<String, Object> qnaAll() {
		
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		Map<String, String> data = new HashMap<>();
		List<FaqBoard> qnaList = faqBoardRepository.findAll();
		
		for(FaqBoard q:qnaList) {
			data.put("faq_no", String.valueOf(q.getFaqNo()));
			data.put("faq_date", q.getFaqDate());
			data.put("faq_title", q.getFaqTitle());
			data.put("faq_cont", q.getFaqContent());
			data.put("faq_type", q.getFaqType());
			dataList.add(data);
		}
		Map<String, Object> qnaDatas = new HashMap<String, Object>();
		qnaDatas.put("datas", dataList);
		return qnaDatas;
	}
	
	@PostMapping("faqDetail")
	@ResponseBody
	public Map<String, Object> faqDetail(@RequestParam("no") String faq_no){
		List<Map<String, String>> dataList = new ArrayList<>();
		Map<String, String> data = new HashMap<>();
		Optional<FaqBoard> optionalFaqBoard = faqBoardRepository.findById(Long.parseLong(faq_no));

		optionalFaqBoard.ifPresent(faqBoard -> {
			data.put("faq_no", faq_no);
			data.put("faq_title", faqBoard.getFaqTitle());
			data.put("faq_content", faqBoard.getFaqContent());
			data.put("faq_date", faqBoard.getFaqDate());
			data.put("faq_type", faqBoard.getFaqType());
			dataList.add(data);
		});

		Map<String, Object> faqDatas = new HashMap<String, Object>();
		faqDatas.put("datas", dataList);
		return faqDatas;
	}
	
	@PostMapping("qnaOrder")
	@ResponseBody
	public Map<String, Object> abc(@RequestParam("faq_type") String faq_type){
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		
		Map<String, String> data = new HashMap<>();
		List<FaqBoard> qnaList = faqBoardRepository.findAllByFaqType(faq_type);

		for(FaqBoard q:qnaList) {
			data.put("faq_no", String.valueOf(q.getFaqNo()));
			data.put("faq_title", q.getFaqTitle());
			data.put("faq_content", q.getFaqContent());
			data.put("faq_date", q.getFaqDate());
			data.put("faq_type", q.getFaqType());
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
