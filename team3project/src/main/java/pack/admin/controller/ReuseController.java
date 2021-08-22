package pack.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminInter;
import pack.model.AdminDto;
import pack.model.OldBookDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReuseController {
	private final AdminInter adminInter;
	
	@RequestMapping(value="reuse", method=RequestMethod.GET)
	public ModelAndView ReuseBook(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		
		List<OldBookDto> reuselist = adminInter.getReuse();
		view.setViewName("admin/reuse");
		view.addObject("reuselist",reuselist);

		return view;
	}
	
	@RequestMapping(value="throwaway", method=RequestMethod.POST)
	public String ObThrow(@RequestParam(name="ob_no") int ob_no[], HttpSession session, ModelMap model) {

		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			return "admin/admin_login";
		}
		AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		boolean b = false;

		for (int index : ob_no) {
			b = adminInter.updateThrow(index);
		}

		if(b) {
			return "redirect:/reuse";
		}

		return "redirect:/adminmain";
	}
}
