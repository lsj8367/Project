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
import pack.model.AdminDto;
import pack.model.OrderInfoDto;
import pack.model.UserDto;

@Controller
@RequiredArgsConstructor
public class MonthBuyerController {
	private final AdminDao adminDao;
	
	@RequestMapping(value="monthbuyer", method=RequestMethod.GET)
	public ModelAndView goMonthBuyer(HttpSession session, ModelMap model) {
		ModelAndView view = new ModelAndView();
		
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") {
			view.setViewName("admin/admin_login");
			return view;
		}
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		String cmonth = adminDao.getMonth();
		List<OrderInfoDto> bulist = adminDao.getBuyKing();
		view.addObject("cm", cmonth);
		view.addObject("bu", bulist);
		view.setViewName("admin/monthbuyer");

		return view;
	}
	
	@RequestMapping(value = "givepoint4", method = RequestMethod.POST)
	public String JikwonUpJik(HttpSession session, ModelMap model, UserDto bean,
							@RequestParam(name="rn") int[] rank,
							@RequestParam(name="user_id") String[] userid){
		String admin_id = (String)session.getAttribute("admin_id");
		if(admin_id == null | admin_id == "") 
			return "admin/admin_login";
		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		model.addAttribute("info", dto);

		
		boolean b = false;
		
		for (int i = 0; i < userid.length; i++) {
			if(userid[i] != null) {
				if(rank[i] == 1) {
					bean.setPluspoint(3000);
				}else if(rank[i] == 2) {
					bean.setPluspoint(2000);
				}else if(rank[i] == 3) {
					bean.setPluspoint(1000);
				}else {
					bean.setPluspoint(0);
				}
				bean.setUser_id(userid[i]);
				b = adminDao.upUserPoint(bean);
			}else {
				b = true;
			}
		}
		if(b) {
			return "redirect:/monthbuyer";
		}
		return "redirect:/adminmain";
	}
}
