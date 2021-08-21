package pack.mypage.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.model.OldBookDto;
import pack.mypage.model.MydonorImpl;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MydonorController {
	private final MydonorImpl mydonorImpl;

	@RequestMapping("mydonor")
	public ModelAndView mydonorlist(HttpSession session) {
		ModelAndView modelAndView=new ModelAndView();
		String id = (String)session.getAttribute("id");
		//도서 기부 내역 모델앤뷰
		List<OldBookDto> donorlistall = mydonorImpl.donorListbyId(id);
		modelAndView.setViewName("mypage/mydonor");
		modelAndView.addObject("dnabook", donorlistall);
		
		return modelAndView;
	}
}
