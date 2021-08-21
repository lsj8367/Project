package pack.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.controller.OrderInfoBean;
import pack.model.OrderInfoDto;
import pack.model.UnmemberImpl;

@Controller
public class UnmemberController {
	@Autowired
	private UnmemberImpl unmemberImpl;
	
	@RequestMapping(value = "unmembercheck", method = RequestMethod.GET)
	public String check() {
		return "unmembercheck";
	}
	
	@RequestMapping(value = "unmember", method = RequestMethod.POST)
	public ModelAndView search(@RequestParam("orderlist_no") String orderlist_no, @RequestParam("order_passwd") String order_passwd, OrderInfoBean bean,
			Model model) {
		ModelAndView modelAndView = new ModelAndView();
		OrderInfoDto dto = unmemberImpl.search(bean);
		if(orderlist_no.equals(bean.getOrderlist_no()) && order_passwd.equals(bean.getOrder_passwd())) {
			modelAndView.addObject("list", dto);
			modelAndView.setViewName("unmember");
			model.addAttribute("order_passwd", order_passwd);
		}else {
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}
	
}
