package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.user.service.BuyService;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class BuyController {
	private final BuyService buyService;

	@RequestMapping("buy")
	public ModelAndView buy(@RequestParam("ob_no") String ob_no, HttpSession session) {
		String user_id = (String)session.getAttribute("id");
		return buyService.buy(ob_no, user_id);
	}
}
