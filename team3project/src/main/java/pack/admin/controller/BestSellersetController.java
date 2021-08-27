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
import pack.controller.NewBookBean;
import pack.model.AdminDto;
import pack.model.NewBookDto;

@Controller
@RequiredArgsConstructor
public class BestSellersetController {
	private final AdminDao adminDao;

	@RequestMapping(value = "bestsellerset", method = RequestMethod.GET)
	public ModelAndView goBestSellerset(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();

		String admin_id = (String) session.getAttribute("admin_id");
		if (admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		List<NewBookDto> omonth = adminDao.getOmonth();
		view.addObject("om",omonth);
		view.setViewName("admin/bestsellerset");
		return view;
	}

	@RequestMapping(value="monthbestseller", method=RequestMethod.POST)
	public ModelAndView goBestReview(HttpSession session, ModelMap model, @RequestParam("sql") String sql) {
		ModelAndView view = new ModelAndView();

		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);
		List<NewBookDto> omonth = adminDao.getOmonth();
		List<NewBookDto> ol = adminDao.getBestSellermonth(sql);
		view.addObject("om", omonth);
		view.addObject("ol", ol);
		view.setViewName("admin/bestsellerset");

		return view;
	}

	@RequestMapping(value = "addstock", method = RequestMethod.POST)
	public String JikwonUpJik(HttpSession session, ModelMap model, NewBookBean bean,
						@RequestParam(name="rn") int rank[], 
						@RequestParam(name="nb_no") int no[]){
	String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") return "admin/admin_login";

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		boolean b = false;

		for (int i = 0; i < no.length; i++) {
			if(rank[i] == 1) {
				bean.setPlusstock(200);
			}else if(rank[i] == 2) {
				bean.setPlusstock(100);
			}else if(rank[i] == 3) {
				bean.setPlusstock(50);
			}else {
				bean.setPlusstock(0);
			}
			bean.setNb_no(no[i]);
			b = adminDao.upNbStock(bean);
		}

		if(b) {
			return "redirect:/bestsellerset";
		}
		return "redirect:/adminmain";
	}
}
