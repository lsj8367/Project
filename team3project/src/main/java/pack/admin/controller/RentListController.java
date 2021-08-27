package pack.admin.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.model.AdminDto;
import pack.model.RentInfoDto;

@Controller
@RequiredArgsConstructor
public class RentListController {
	private final AdminDao adminDao;
	
	@RequestMapping(value="rentlist", method = RequestMethod.GET)
	public ModelAndView getRentList(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		
		List<RentInfoDto> rilist = adminDao.getRentList();
		view.addObject("rlist", rilist);
		view.setViewName("admin/rentinfo");

		return view;
	}
	
}
