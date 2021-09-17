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
import pack.admin.service.AdminService;
import pack.model.OldBookDto;

@Controller
@RequiredArgsConstructor
public class ReuseController {
	private final AdminDao adminDao;
	private final AdminService adminService;
	
	@GetMapping("reuse")
	public ModelAndView ReuseBook(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		model.addAttribute("info", adminService.selectAdminData(admin_id));
		
		List<OldBookDto> reuselist = adminDao.selectReuseAll();
		view.setViewName("admin/reuse");
		view.addObject("reuselist",reuselist);

		return view;
	}
	
	@PostMapping("throwaway")
	public String ObThrow(@RequestParam(name="ob_no") int ob_no[], HttpSession session, ModelMap model) {

		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			return "admin/admin_login";
		}
		model.addAttribute("info", adminService.selectAdminData(admin_id));
		boolean b = false;

		for (int index : ob_no) {
			b = adminDao.obthrow(index);
		}

		if(b) {
			return "redirect:/reuse";
		}

		return "redirect:/adminmain";
	}
}
