package pack.admin.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.model.AdminDto;
import pack.model.OldBookDto;

@Controller
@RequiredArgsConstructor
public class DonatebooklistController {
	private final AdminDao adminDao;
	
	@GetMapping("donatebooklist")
	public ModelAndView StandbyBook(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		List<OldBookDto> standbylist = adminDao.getStandby();
		view.setViewName("admin/standby");
		view.addObject("slist",standbylist);

		return view;
	}
	
	@PostMapping("standbyok")
	public String upState(OldBookDto oldBookDto,
			@RequestParam(name="ob_no") int ob_no[], 
			@RequestParam(name="ob_state") String ob_state[],
			HttpSession session, ModelMap model) {
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		boolean b = false;

		for (int i = 0; i < ob_no.length; i++) {
			oldBookDto.setOb_no(ob_no[i]);
			oldBookDto.setOb_state(ob_state[i]);
			b = adminDao.updateState(oldBookDto);
		}

		if(b) {
			return "redirect:/donatebooklist";
		}
		return "redirect:/adminmain";
	}
}
