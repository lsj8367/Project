package pack.admin.controller;

import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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
    public ModelAndView adminData(HttpSession session) {
        String admin_id = (String) session.getAttribute("admin_id");

        if (Objects.isNull(admin_id) || admin_id.equals("")) {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/admininfo", Map.of(
            "info", adminService.selectAdminData(admin_id)
        ));
    }

    @PostMapping("updateadmin")
    public String updateAdmin(AdminDto adminDto) {
        return adminService.updateAdminInfo(adminDto.getAdmin_id(), adminDto.getAdmin_passwd(), adminDto.getAdmin_name())
            ? "redirect:/admininfo" : "error";
    }

}
