package pack.admin.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.domain.entity.FaqBoard;
import pack.model.AdminDto;
import pack.repository.FaqBoardRepository;

@Controller
@RequiredArgsConstructor
public class FAQManageController {
	private final AdminDao adminDao;
	private final FaqBoardRepository faqBoardRepository;
	
	@GetMapping("faqmanage")
	public ModelAndView goFaqManage(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		List<FaqBoard> flist = faqBoardRepository.findAll();
		view.addObject("fl", flist);
		view.setViewName("admin/faqmanage");
		return view;
	}
	
}
