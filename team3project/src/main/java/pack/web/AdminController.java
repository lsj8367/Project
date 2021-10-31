package pack.web;

import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.admin.service.AdminService;
import pack.admin.domain.Admin;
import pack.admin.model.AdminDto;
import pack.orderinfo.service.OrderInfoService;
import pack.validation.AdminLoginValidation;
import pack.validation.LoginValidation;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminDao adminDao;
    private final AdminService adminService;
    private final OrderInfoService orderInfoService;
    private final LoginValidation loginValidation = new AdminLoginValidation();

    @GetMapping("adminlogin")
    public ModelAndView goLogin(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        String admin_id = loginValidation.sessionCheck(session, modelAndView);
        if (isViewNameExist(modelAndView)) {
            return modelAndView;
        }

        return new ModelAndView("redirect:/admin", Map.of(
            "info", adminService.selectAdminData(admin_id)
        ));
    }

    private boolean isViewNameExist(final ModelAndView modelAndView) {
        return !Objects.isNull(modelAndView.getViewName());
    }

    @GetMapping("adminregister")
    public String goAdminSignup() {
        return "admin/adminsignup";
    }

    @PostMapping(value = "checkSignupAdminId")
    @ResponseBody
    public String AjaxView(@RequestParam("admin_id") String admin_id) {
        Admin admin = adminService.selectAdminData(admin_id);
        if (Objects.isNull(admin.getAdminId())) {
            return "YES";
        }
        return "NO";
    }

    @PostMapping("adminsignupok")
    public String submit(AdminDto adminDto) {
        adminDao.adminInsert(adminDto);
        return "redirect:/adminlogin";
    }

    @GetMapping("idcheck")
    public ModelAndView goIdCheck(@RequestParam("id") String adminid) {
        try {
            adminService.selectAdminData(adminid);
            return new ModelAndView("admin/adminidcheck", Map.of("alert", adminid + "는 사용가능한 아이디입니다."));
        }catch (Exception e) {
            return new ModelAndView("admin/adminidcheck", Map.of("alert", adminid + "는 존재하는 아이디입니다."));
        }
    }


    @PostMapping("admin_login")
    public String submitLogin(HttpSession session,
        @RequestParam("adminId") String adminId,
        @RequestParam("adminPasswd") String adminPasswd) {

        Admin admin = adminService.selectAdminData(adminId);
        String retPasswd = admin.getAdminPassword();
        if (retPasswd.equals(adminPasswd)) {
            if (admin.getAdminAcc() == 2) {
                session.setAttribute("admin_id", adminId);
                return "redirect:admin";
            }
        }
        return "admin/admin_login";
    }

    @GetMapping("admin")
    public ModelAndView list(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/adminmain");
        modelAndView.addObject("info", adminService.selectAdminData((String) session.getAttribute("admin_id")));
        modelAndView.addObject("bsb", adminService.getMostSellBook());
        modelAndView.addObject("brb", adminService.getMostRentBook());
        modelAndView.addObject("rtm", adminDao.mbRentCmonth());
        modelAndView.addObject("om", adminDao.mbSellerCmonth());
        modelAndView.addObject("ru", adminDao.rentKing());
        return modelAndView;
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
