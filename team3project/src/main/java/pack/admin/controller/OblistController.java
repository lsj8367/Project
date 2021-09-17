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
import pack.model.OldBookDto;

@Controller
@RequiredArgsConstructor
public class OblistController {
	private final AdminService adminService;
	private final AdminDao adminDao;
	
	@GetMapping("oblist")
	public ModelAndView getRentBook(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		model.addAttribute("info", adminService.selectAdminData(admin_id));
		
		List<OldBookDto> rblist = adminDao.selectRentBookAll();
		
		view.addObject("rblist", rblist);
		view.setViewName("admin/rbookinfo");

		return view;
	}
}
