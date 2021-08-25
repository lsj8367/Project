package pack.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminInter;
import pack.model.AdminDto;
import pack.model.FaqBoardDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FAQManageController {
	private final AdminInter adminInter;
	
	@RequestMapping(value="faqmanage", method=RequestMethod.GET)
	public ModelAndView goFaqManage(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		List<FaqBoardDto> flist = adminInter.getFaqlist();
		view.addObject("fl", flist);
		view.setViewName("admin/faqmanage");
		return view;
	}
	
}
