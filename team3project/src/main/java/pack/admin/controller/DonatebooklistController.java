package pack.admin.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.service.AdminService;

@Controller
@RequiredArgsConstructor
public class DonatebooklistController {

    private final AdminService adminService;

    @ExceptionHandler
    public ModelAndView exception(Exception e) {
        return new ModelAndView("admin/admin_login");
    }

    @GetMapping("donatebooklist")
    public ModelAndView StandbyBook(HttpSession session, ModelMap model) {
        String adminId = sessionCheck(session);
        return new ModelAndView("admin/standby", Map.of(
            "info", adminService.selectAdminData(adminId),
            "slist", adminService.selectStandByAll()
        ));
    }

    @PostMapping("standbyok")
    public String upState(@RequestParam(name = "ob_no") int ob_no[],
        @RequestParam(name = "ob_state") String ob_state[],
        HttpSession session, ModelMap model) {
        String admin_id = sessionCheck(session);
        model.addAttribute("info", adminService.selectAdminData(admin_id));
        try {
            for (int i = 0; i < ob_no.length; i++) {
                adminService.upObState(ob_no[i], ob_state[i]);
            }
            return "redirect:/donatebooklist";
        } catch (Exception e) {
            return "redirect:/adminmain";
        }
    }

    private String sessionCheck(HttpSession session) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null || admin_id == "") {
            new Exception("세션 없음");
        }
        return admin_id;
    }

}
