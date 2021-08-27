package pack.admin.controller;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.model.AdminDto;

@Controller
@RequiredArgsConstructor
public class OrderListController {
	private final AdminDao adminDao;
	
	@GetMapping(value="orderlist")
	public ModelAndView goOrder(HttpSession session, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			modelAndView.setViewName("admin/admin_login");
			return modelAndView;
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		modelAndView.addObject("nborderlist", adminDao.getnbOrderData());
		modelAndView.addObject("oborderlist", adminDao.getobOrderData());

		modelAndView.setViewName("admin/orderinfo");
		return modelAndView;
	}
}
