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
import pack.model.NewBookDto;

@Controller
@RequiredArgsConstructor
public class NewBookDataController {
	private final AdminDao adminDao;
	
	@RequestMapping(value="newbookdata", method=RequestMethod.GET)
	public ModelAndView goUser(HttpSession session, ModelMap model) {
		ModelAndView modelAndView = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			modelAndView.setViewName("admin/admin_login");
			return modelAndView;
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		List<NewBookDto> nblist = adminDao.getNewBook();
		
		modelAndView.setViewName("admin/nbinfo");
		modelAndView.addObject("nblist", nblist);

		return modelAndView;
	}
}
