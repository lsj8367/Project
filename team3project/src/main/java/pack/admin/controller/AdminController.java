package pack.admin.controller;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.admin.service.AdminService;
import pack.domain.entity.Admin;
import pack.model.AdminDto;
import pack.model.NewBookDto;
import pack.model.OldBookDto;
import pack.model.OrderInfoDto;
import pack.model.RentInfoDto;
import pack.validation.AdminLoginValidation;
import pack.validation.LoginValidation;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminDao adminDao;
    private final AdminService adminService;
    private final LoginValidation loginValidation = new AdminLoginValidation();

    @GetMapping("adminlogin")
    public ModelAndView goLogin(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        String admin_id = loginValidation.sessionCheck(session, modelAndView);
        if (isViewNameExist(modelAndView)) {
            return modelAndView;
        }

        modelAndView.addObject("info", adminService.selectAdminData(admin_id));
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
    public @ResponseBody
    String AjaxView(@RequestParam("admin_id") String admin_id) {
        Admin admin = adminService.selectAdminData(admin_id);
        if (Objects.isNull(admin.getAdminId())) {
            return "YES";
        }
        return "NO";
    }

    @PostMapping("adminsignupok")
    public String submit(AdminDto adminDto) {

        boolean b = adminDao.adminInsert(adminDto);
        if (b) {
            return "redirect:/adminlogin";
        }
        return "error";
    }

    @GetMapping("idcheck")
    public ModelAndView goIdCheck(@RequestParam("id") String adminid) {
        ModelAndView view = new ModelAndView();

        view.setViewName("admin/adminidcheck");
        try {
            adminService.selectAdminData(adminid);
            view.addObject("alert", adminid + "는 사용가능한 아이디입니다.");
        }catch (Exception e) {
            view.addObject("alert", adminid + "는 존재하는 아이디입니다.");
        }
        return view;
    }


    @PostMapping("admin_login")
    public String submitLogin(HttpSession session,
        @RequestParam("admin_id") String admin_id,
        @RequestParam("admin_passwd") String admin_passwd) {

        System.out.println("123123123");
        Admin admin = adminService.selectAdminData(admin_id);
        int adminAcc = admin.getAdminAcc();
        String retPasswd = admin.getAdminPassword();
        if (retPasswd.equals(admin_passwd)) {
            if (admin.getAdminAcc() == 2) {
                session.setAttribute("admin_id", admin_id);
                return "redirect:admin";
            }
        }
        return "admin/admin_login";
    }

    @GetMapping("admin")
    public ModelAndView list(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        List<RentInfoDto> rulist = adminDao.rentKing();
        List<OrderInfoDto> bulist = adminDao.buyKing();
        OrderInfoDto oprofit2 = adminDao.obprofitmonth();
        OrderInfoDto nprofit2 = adminDao.nbprofitmonth();
        OrderInfoDto rprofit2 = adminDao.profitMonth();
        List<NewBookDto> ocmonth = adminDao.mbSellerCmonth();
        List<RentInfoDto> rentcmonth = adminDao.mbRentCmonth();
        List<NewBookDto> msbook = adminDao.getMostSellBook();
        List<OldBookDto> mrbook = adminDao.getMostRentBook();
        view.addObject("bsb", msbook);
        view.addObject("brb", mrbook);
        view.addObject("rtm", rentcmonth);
        view.addObject("om", ocmonth);
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
