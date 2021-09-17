package pack.admin.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.admin.service.AdminService;
import pack.model.OrderInfoDto;

@Controller
@RequiredArgsConstructor
public class MonthProfitController {
	private final AdminDao adminDao;
	private final AdminService adminService;
	
	@GetMapping("monthprofit")
	public ModelAndView goMonthProfit(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		model.addAttribute("info", adminService.selectAdminData(admin_id));
		List<OrderInfoDto> rprofit = adminDao.profit();
		List<OrderInfoDto> oprofit = adminDao.obprofit();
		List<OrderInfoDto> nprofit = adminDao.nbprofit();
		view.addObject("rp", rprofit);
		view.addObject("op", oprofit);
		view.addObject("np", nprofit);
		view.setViewName("admin/monthprofit");
		return view;
	}
}
