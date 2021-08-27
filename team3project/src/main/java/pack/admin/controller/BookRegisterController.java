package pack.admin.controller;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pack.admin.model.AdminDao;
import pack.controller.NewBookBean;
import pack.model.AdminDto;

@Controller
@RequiredArgsConstructor
public class BookRegisterController {
	private final AdminDao adminDao;

	@RequestMapping(value = "bookregister", method = RequestMethod.GET)
	public String insert(HttpSession session, ModelMap model) {
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		return "admin/bookregister";
	}

	@RequestMapping(value = "bookregister", method = RequestMethod.POST)
	public String submit(NewBookBean bean, HttpSession session, ModelMap model, @RequestParam("nb_image") String nb_image) throws Exception {
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			return "admin/admin_login";
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		if(nb_image.equals("") | nb_image == null) {
			bean.setNb_image("resources/static/images/notready.jpg");
		}
		
		boolean b = adminDao.insertBookData(bean);
		if (b) {
			return "redirect:/bookregister";
		}
		return "error";
	}
}
