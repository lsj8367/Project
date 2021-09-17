package pack.admin.controller;

import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.service.AdminService;
import pack.model.AdminDto;

@Controller
@RequiredArgsConstructor
public class AdminInfoController {

    private final AdminService adminService;

    @GetMapping("admininfo")
    public ModelAndView adminData(HttpSession session, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        String admin_id = (String) session.getAttribute("admin_id");

        if (Objects.isNull(admin_id) || admin_id.equals("")) {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }

        modelAndView.addObject("info", adminService.selectAdminData(admin_id));
        modelAndView.setViewName("admin/admininfo");
        return modelAndView;
    }

    @PostMapping("updateadmin")
    public String updateAdmin(AdminDto adminDto) {
        return adminService.updateAdminInfo(adminDto.getAdmin_id(), adminDto.getAdmin_passwd(), adminDto.getAdmin_name())
            ? "redirect:/admininfo" : "error";
    }

}
