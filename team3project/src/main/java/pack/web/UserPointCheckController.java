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
public class UserPointCheckController {

    private final AdminService adminService;
    private final UserService userService;

    @GetMapping("userpointcheck")
    public ModelAndView getUserPoint(HttpSession session) {
        String adminId = (String) session.getAttribute("admin_id");
        if (Objects.isNull(adminId) || Objects.equals(adminId, "")) {
            return new ModelAndView("admin/admin_login");
        }
        return new ModelAndView("admin/pointcheck", Map.of(
            "info", adminService.selectAdminData(adminId),
            "plist", userService.selectUserAll()
        ));
    }

}
