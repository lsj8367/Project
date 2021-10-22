package pack.web;

import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.service.AdminService;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserDataController {
    private final AdminService adminService;
    private final UserService userService;

    @GetMapping(value = "userdata")
    public ModelAndView goUser(HttpSession session) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (Objects.isNull(admin_id) || Objects.equals(admin_id, "")) {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/userinfo", Map.of(
            "info", adminService.selectAdminData(admin_id),
            "ulist", userService.selectUserAll()
        ));
    }

}
