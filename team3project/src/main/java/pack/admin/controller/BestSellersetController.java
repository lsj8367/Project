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
import pack.model.NewBookDto;

@Controller
@RequiredArgsConstructor
public class BestSellersetController {
	private final AdminDao adminDao;

	@GetMapping("bestsellerset")
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

	@PostMapping("monthbestseller")
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

	@PostMapping("addstock")
	public String addStock(HttpSession session, ModelMap model, NewBookDto newBookDto,
						@RequestParam(name="rn") int rank[], 
						@RequestParam(name="nb_no") int no[]){
	String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") return "admin/admin_login";

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		boolean b = false;

		for (int i = 0; i < no.length; i++) {
			if(rank[i] == 1) {
				newBookDto.setPlusstock(200);
			}else if(rank[i] == 2) {
				newBookDto.setPlusstock(100);
			}else if(rank[i] == 3) {
				newBookDto.setPlusstock(50);
			}else {
				newBookDto.setPlusstock(0);
			}
			newBookDto.setNb_no(no[i]);
			b = adminDao.upNbStock(newBookDto);
		}

		if(b) {
			return "redirect:/bestsellerset";
		}
		return "redirect:/adminmain";
	}
}
