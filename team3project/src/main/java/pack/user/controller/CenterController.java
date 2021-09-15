package pack.user.controller;

import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.user.service.CenterService;

@Controller
@RequiredArgsConstructor
public class CenterController {
	private final CenterService centerService;
	

	@GetMapping(value = "center")
	public String centerC() {
		return "center";
	}

	
	@PostMapping("qnaAll")
	@ResponseBody
	public Map<String, Object> qnaAll() {
		Map<String, Object> qnaDatas = new HashMap<>();
		qnaDatas.put("datas", centerService.qnaAll());
		return qnaDatas;
	}
	
	@PostMapping("faqDetail")
	@ResponseBody
	public Map<String, Object> faqDetail(@RequestParam("no") String faq_no){
		Map<String, Object> faqDatas = new HashMap<>();
		faqDatas.put("datas", centerService.faqDetail(faq_no));
		return faqDatas;
	}
	
	@PostMapping("qnaOrder")
	@ResponseBody
	public Map<String, Object> qnaOrder(@RequestParam("faq_type") String faq_type){
		Map<String, Object> qnaDatas = new HashMap<>();
		qnaDatas.put("datas", centerService.qnaOrder(faq_type));
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
