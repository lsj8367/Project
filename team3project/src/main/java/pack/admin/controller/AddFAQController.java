package pack.admin.controller;

import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.model.AdminDto;
import pack.model.FaqBoardDto;
import pack.validation.LoginValidation;

@Controller
@RequiredArgsConstructor
public class AddFAQController {

	private final AdminDao adminDao;

	@Autowired
	@Qualifier("adminLoginValidation")
	private LoginValidation loginValidation;

	@GetMapping("addfaq")
	public ModelAndView goAddfaq(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		String admin_id = session.getAttribute("admin_id").toString();

		loginValidation.sessionCheck(admin_id, view);
		if (!Objects.isNull(view.getViewName())) {
			return view;
		}

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		view.setViewName("admin/addfaq");
		return view;
	}

	@GetMapping("faqadd")
	public String goFaqAdd(HttpSession session, ModelMap model, FaqBoardDto faqBoardDto) {
		String admin_id = (String)session.getAttribute("admin_id");

		String url = loginValidation.idCheck(admin_id);
		if (!Objects.isNull(url)) {
			return url;
		}

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		boolean b = adminDao.insertFaqData(faqBoardDto);
		if (b) {
			return "redirect:admin/addfaq";
		}

		return "error";
	}
}
