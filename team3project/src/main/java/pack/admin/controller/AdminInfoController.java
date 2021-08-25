package pack.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminInter;
import pack.controller.AdminBean;
import pack.model.AdminDto;

import javax.servlet.http.HttpSession;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AdminInfoController {
	private final AdminInter adminInter;
	
	@RequestMapping(value = "admininfo", method = RequestMethod.GET)
	public ModelAndView adminData(HttpSession session, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");

		if(Objects.isNull(admin_id) || admin_id.equals("")) {
			modelAndView.setViewName("admin/admin_login");
			return modelAndView;
		}

		AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
		modelAndView.setViewName("admin/admininfo");
		modelAndView.addObject("info", dto);
		return modelAndView;
	}
	
	@RequestMapping(value="updateadmin", method = RequestMethod.POST)
	public String updateAdmin(AdminBean bean) {
		return adminInter.updateAdmin(bean) ? "redirect:/admininfo" : "error";
	}
	
}
