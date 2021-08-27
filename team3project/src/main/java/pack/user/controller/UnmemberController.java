package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.model.OrderInfoDto;
import pack.user.model.UnmemberDao;

@Controller
@RequiredArgsConstructor
public class UnmemberController {
	private final UnmemberDao unmemberDao;
	
	@RequestMapping(value = "unmembercheck", method = RequestMethod.GET)
	public String check() {
		return "unmembercheck";
	}
	
	@RequestMapping(value = "unmember", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("orderlist_no") String orderlist_no, @RequestParam("order_passwd") String order_passwd, OrderInfoDto orderInfoDto,
			Model model) {
		ModelAndView modelAndView = new ModelAndView();

		if(orderlist_no.equals(orderInfoDto.getOrderlist_no()) && order_passwd.equals(orderInfoDto.getOrder_passwd())) {
			OrderInfoDto dto = unmemberDao.search(orderInfoDto);
			modelAndView.addObject("list", dto);
			modelAndView.setViewName("unmember");
			model.addAttribute("order_passwd", order_passwd);
			return modelAndView;
		}

		modelAndView.setViewName("error");
		return modelAndView;
	}
	
}
