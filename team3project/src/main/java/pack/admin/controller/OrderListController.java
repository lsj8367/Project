package pack.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminInter;
import pack.model.AdminDto;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OrderListController {
	private final AdminInter adminInter;
	
	@RequestMapping(value="orderlist", method=RequestMethod.GET)
	public ModelAndView goOrder(HttpSession session, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			modelAndView.setViewName("admin/admin_login");
			return modelAndView;
		}
		AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		modelAndView.addObject("nborderlist", adminInter.getnbOrderData());
		modelAndView.addObject("oborderlist", adminInter.getobOrderData());

		modelAndView.setViewName("admin/orderinfo");
		return modelAndView;
	}
}
