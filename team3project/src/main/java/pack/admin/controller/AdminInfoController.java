package pack.admin.controller;

import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.model.AdminDto;

@Controller
@RequiredArgsConstructor
public class AdminInfoController {
	private final AdminDao adminDao;
	
	@GetMapping("admininfo")
	public ModelAndView adminData(HttpSession session, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");

		if(Objects.isNull(admin_id) || admin_id.equals("")) {
			modelAndView.setViewName("admin/admin_login");
			return modelAndView;
		}

		pack.model.AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		modelAndView.setViewName("admin/admininfo");
		modelAndView.addObject("info", dto);
		return modelAndView;
	}
	
	@PostMapping("updateadmin")
	public String updateAdmin(AdminDto adminDto) {
		return adminDao.updateAdmin(adminDto) ? "redirect:/admininfo" : "error";
	}
	
}
