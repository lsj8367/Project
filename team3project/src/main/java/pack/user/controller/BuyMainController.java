package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.user.service.BuyMainService;

@Controller
@RequiredArgsConstructor
public class BuyMainController {

	private final BuyMainService buyMainService;

	@RequestMapping("buymain")
	public ModelAndView main() {
		ModelAndView modelAndView = buyMainService.buyMain();
		modelAndView.setViewName("buymain");
		return modelAndView;
	}
}
