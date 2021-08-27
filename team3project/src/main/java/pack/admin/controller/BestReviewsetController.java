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
import pack.model.ReviewDto;
import pack.model.UserDto;

@Controller
@RequiredArgsConstructor
public class BestReviewsetController {
	private final AdminDao adminDao;
	
	@GetMapping("bestreviewset")
	public ModelAndView goBestReviewset(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		List<ReviewDto> rmonth = adminDao.getRmonth();
		view.addObject("rm", rmonth);
		view.setViewName("admin/bestreviewset");
		return view;
	}
	
	@PostMapping("monthbestreview")
	public ModelAndView goBestReview(HttpSession session, ModelMap model, @RequestParam("sql") String sql) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		List<ReviewDto> rmonth = adminDao.getRmonth();
		List<ReviewDto> rl = adminDao.getBestReviewmonth(sql);
		view.addObject("rm", rmonth);
		view.addObject("rl", rl);
		view.setViewName("admin/bestreviewset");
		return view;
	}
	
	@PostMapping("givepoint")
	public String JikwonUpJik(HttpSession session, ModelMap model, UserDto bean,
							@RequestParam(name="rn") int rank[], 
							@RequestParam(name="review_id") String userid[]){
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") return "admin/admin_login";

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		boolean b = false;

		for (int i = 0; i < userid.length; i++) {
			if(rank[i] == 1) {
				bean.setPluspoint(5000);
			}else if(rank[i] == 2) {
				bean.setPluspoint(3000);
			}else if(rank[i] == 3) {
				bean.setPluspoint(2000);
			}else {
				bean.setPluspoint(0);
			}
			bean.setUser_id(userid[i]);
			b = adminDao.upUserPoint(bean);
		}

		if(b) {
			return "redirect:/bestreviewset";
		}
		return "redirect:/adminmain";
	}
}
