package pack.admin.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.model.AdminDto;
import pack.model.UserDto;

@Controller
@RequiredArgsConstructor
public class UserPointCheckController {
	private final AdminDao adminDao;
	
	@GetMapping("userpointcheck")
	public ModelAndView getUserPoint(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		
		List<UserDto> plist = adminDao.getUserPoint();
		view.addObject("plist", plist);
		view.setViewName("admin/pointcheck");
		return view;
	}
}
