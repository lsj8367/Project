package pack.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminInter;
import pack.model.AdminDto;
import pack.model.OrderInfoDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MonthProfitController {
	private final AdminInter adminInter;
	
	@RequestMapping(value="monthprofit", method=RequestMethod.GET)
	public ModelAndView goMonthProfit(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		List<OrderInfoDto> rprofit = adminInter.getProfit();
		List<OrderInfoDto> oprofit = adminInter.getObProfit();
		List<OrderInfoDto> nprofit = adminInter.getNbProfit();
		view.addObject("rp", rprofit);
		view.addObject("op", oprofit);
		view.addObject("np", nprofit);
		view.setViewName("admin/monthprofit");
		return view;
	}
}
