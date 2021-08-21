package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.model.CardInfoDto;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.model.BuyImpl;
import pack.model.CardInfoImpl;
import pack.model.OldBookImpl;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class BuyController {
	private final OldBookImpl oldBookImpl;
	private final BuyImpl buyImpl;
	private final CardInfoImpl cardInfoImpl;
	
	@RequestMapping("buy")
	public ModelAndView buy(@RequestParam("ob_no") String ob_no, HttpSession session) {
		String user_id = (String)session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();
		OldBookDto dto = oldBookImpl.bookInfo(ob_no);
		modelAndView.addObject("buyinfo", dto);
		//System.out.println("buyController : buyinfo 추가");
		UserDto user = buyImpl.point(user_id);
		modelAndView.addObject("point", user);

		//System.out.println("buyController : point 추가");
		CardInfoDto card = cardInfoImpl.selectCard(user_id);
		modelAndView.addObject("card", card);

		//System.out.println("buyController : card 추가");
		modelAndView.setViewName("buy");
		return modelAndView;
	}
}
