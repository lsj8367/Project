package pack.admin.controller;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.admin.model.AdminDao;
import pack.model.AdminDto;
import pack.model.NewBookDto;

@Controller
@RequiredArgsConstructor
public class BookRegisterController {
	private final AdminDao adminDao;

	@GetMapping("bookregister")
	public String insert(HttpSession session, ModelMap model) {
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		return "admin/bookregister";
	}

	@PostMapping("bookregister")
	public String submit(NewBookDto newBookDto, HttpSession session, ModelMap model, @RequestParam("nb_image") String nb_image) throws Exception {
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			return "admin/admin_login";
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		if(nb_image.equals("") | nb_image == null) {
			newBookDto.setNb_image("resources/static/images/notready.jpg");
		}
		
		boolean b = adminDao.insertBookData(newBookDto);
		if (b) {
			return "redirect:/bookregister";
		}
		return "error";
	}
}
