package pack.mypage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.model.InqueryDto;
import pack.mypage.model.MyInqImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyInqController {
	private final MyInqImpl myInqImpl;
	
	@RequestMapping("myqna")
	public ModelAndView myInqList(HttpSession session) {
		String user_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();
		
		List<InqueryDto> inqList = myInqImpl.inqListAll(user_id);
		modelAndView.setViewName("mypage/myqna");
		modelAndView.addObject("inqlist",inqList);
		
		return modelAndView;
	}
}
