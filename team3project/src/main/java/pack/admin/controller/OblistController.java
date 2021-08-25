package pack.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminInter;
import pack.model.AdminDto;
import pack.model.OldBookDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OblistController {
	private final AdminInter adminInter;
	
	@RequestMapping(value="oblist", method = RequestMethod.GET)
	public ModelAndView getRentBook(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		
		List<OldBookDto> rblist = adminInter.getRBook();
		
		view.addObject("rblist", rblist);
		view.setViewName("admin/rbookinfo");

		return view;
	}
}
