package pack.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.model.*;
import pack.validation.AdminLoginValidation;
import pack.validation.LoginValidation;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AdminController {

	private final AdminDao adminDao;

	private final LoginValidation loginValidation =  new AdminLoginValidation();

	@GetMapping("adminlogin")
	public ModelAndView goLogin(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String admin_id = loginValidation.sessionCheck(session, modelAndView);
		if (isViewNameExist(modelAndView)) {
			return modelAndView;
		}

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		modelAndView.addObject("info", dto);
		modelAndView.setViewName("redirect:/admin");
		return modelAndView;
	}

	private boolean isViewNameExist(final ModelAndView modelAndView) {
		return !Objects.isNull(modelAndView.getViewName());
	}

	@GetMapping("adminregister")
	public String goAdminSignup() {
		return "admin/adminsignup";
	}
	
	// 아이디 중복 여부 체크
	@PostMapping(value = "checkSignupAdminId")
	public @ResponseBody String AjaxView(@RequestParam("admin_id") String admin_id){
		if(adminDao.getAdminLoginInfo(admin_id) == null) {
			return "YES";
		}
		return "NO";
	}
	
	@PostMapping("adminsignupok")
	public String submit(AdminDto adminDto){

		boolean b = adminDao.insertAdmin(adminDto);
		if (b) {
			return "redirect:/adminlogin";
		}
		return "error";
	}
	
	@GetMapping("idcheck")
	public ModelAndView goIdCheck(@RequestParam("id") String adminid) {
		ModelAndView view = new ModelAndView();
		
		String dto = adminDao.IdCheck(adminid);
		view.setViewName("admin/adminidcheck");

		if(dto == null ||dto == "") {
			view.addObject("alert",adminid +"는 사용가능한 아이디입니다.");
			return view;
		}

		view.addObject("alert",adminid +"는 존재하는 아이디입니다.");
		return view;
	}
	

	@PostMapping("admin_login")
	public String submitLogin(HttpSession session, 
			@RequestParam("admin_id") String admin_id,
			@RequestParam("admin_passwd") String admin_passwd) {

		AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
		String retPasswd = dto.getAdmin_passwd();
		if (retPasswd.equals(admin_passwd)) {
			if(dto.getAdmin_acc() == 2) {
				session.setAttribute("admin_id", admin_id);
				return "redirect:/admin";
			}
		}
		return "admin/admin_login";
	}
	
	@GetMapping("admin")
    public ModelAndView list(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();
        
        String admin_id = (String)session.getAttribute("admin_id");
        
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);
        
        List<RentInfoDto> rulist = adminDao.getRentKing();
        List<OrderInfoDto> bulist = adminDao.getBuyKing();
        OrderInfoDto oprofit2 = adminDao.getObProfitmonth();
        OrderInfoDto nprofit2 = adminDao.getNbProfitmonth();
        OrderInfoDto rprofit2 = adminDao.getProfitmonth();
        List<NewBookDto> ocmonth = adminDao.getOcmonth();
        List<RentInfoDto> rentcmonth = adminDao.getRentcmonth();
        List<NewBookDto> msbook = adminDao.theMostSellBook();
        List<OldBookDto> mrbook = adminDao.theMostRentBook();
        view.addObject("bsb", msbook);
        view.addObject("brb", mrbook);
        view.addObject("rtm", rentcmonth);
		view.addObject("om",ocmonth);
		view.addObject("rp", rprofit2);
        view.addObject("ru", rulist);
        view.addObject("bu", bulist);
        view.addObject("op", oprofit2);
        view.addObject("np", nprofit2);
        view.setViewName("admin/adminmain");
        
        return view;
    }

	@GetMapping("admin_logout")
	public String logout(HttpSession session) {
		session.removeAttribute("admin_id");
		return "redirect:/adminlogin";
	}
	
	@GetMapping("gomain")
	public String adminlogout(HttpSession session) {
		session.removeAttribute("admin_id");
		return "redirect:/main";
	}
	
}
