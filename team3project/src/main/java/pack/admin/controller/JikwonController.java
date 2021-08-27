package pack.admin.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.controller.AdminBean;
import pack.model.AdminDto;

@Controller
@RequiredArgsConstructor
public class JikwonController {
	private final AdminDao adminDao;
	
	@RequestMapping(value="jikwonok", method=RequestMethod.GET)
	public ModelAndView goJikwonRegister(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		List<AdminDto> standbyAdmin = adminDao.getAdminyet();
		view.addObject("newa", standbyAdmin);
		view.setViewName("admin/jikwonok");

		return view;
	}
	
	@RequestMapping(value = "jikwonadmit", method = RequestMethod.POST)
	public String JikwonAdmit(AdminBean bean, HttpSession session, ModelMap model,
							@RequestParam(name="admin_id") String adminid[], 
							@RequestParam(name="admin_acc") int adminacc[]){
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			return "admin/admin_login";
		}
			AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
	        model.addAttribute("info", dto);

		boolean b = false;
		
		for (int i = 0; i < adminid.length; i++) {
			if(adminacc[i]==1) {
				b = adminDao.delAdmin(adminid[i]);
			}else {
				bean.setAdmin_id(adminid[i]);
				bean.setAdmin_acc(adminacc[i]);
				b = adminDao.upAdmin(bean);
			}
		}
		if(b) {
			return "redirect:/jikwonok";
		}

		return "redirect:/adminmain";
	}

	
	@RequestMapping(value="jikwoninfo", method=RequestMethod.GET)
	public ModelAndView goJikwoninfo(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if (admin_id == null || admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
	        
		List<AdminDto> admin = adminDao.getAdminInfo();
		view.addObject("al", admin);
		view.setViewName("admin/jikwoninfo");
		return view;
	}
	
	@RequestMapping(value = "upadminjik", method = RequestMethod.POST)
	public String JikwonUpJik(AdminBean bean, HttpSession session, ModelMap model,
							@RequestParam(name="admin_id") String adminid[], 
							@RequestParam(name="admin_jik") String adminjik[]){
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			return "admin/admin_login";
		}

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		boolean b = false;

		for (int i = 0; i < adminid.length; i++) {
			bean.setAdmin_id(adminid[i]);
			bean.setAdmin_jik(adminjik[i]);
			b = adminDao.upAdminJik(bean);
		}
		if(b) {
			return "redirect:/jikwoninfo";
		}
		return "redirect:/adminmain";
	}
}
