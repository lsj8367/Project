package pack.web;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.admin.service.AdminService;
import pack.user.model.UserDto;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserpenaltyController {

    private final AdminDao adminDao;
    private final AdminService adminService;
    private final UserService userService;

    @GetMapping("userpenalty")
    public ModelAndView goLongterm(HttpSession session) {

        String adminId = (String) session.getAttribute("admin_id");
        if (sessionValidation(adminId)) {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/longdelay", Map.of(
            "info", adminService.selectAdminData(adminId),
            "longd", adminDao.selectdelayAll()
        ));
    }

    @PostMapping(value = "delay")
    public ModelAndView goDelayCount(HttpSession session, UserDto userDto,
        @RequestParam(name = "rent_no") int[] rent_no,
        @RequestParam(name = "user_id") String[] user_id,
        @RequestParam(name = "delpoint") int[] delpoint) {

        String adminId = (String) session.getAttribute("admin_id");
        if (sessionValidation(adminId)) {
            return new ModelAndView("admin/admin_login");
        }

        if (rent_no.length == 0) {
            return new ModelAndView("redirect:/delaycount");
        }

        for (int i = 0; i < rent_no.length; i++) {
            userService.updatePenaltyAndPoint(user_id[i], delpoint[i]);
            adminService.removeOb(rent_no[i]);
        }
        return new ModelAndView("redirect:/delaycount", Map.of(
            "info", adminService.selectAdminData(adminId)
        ));
    }

    @GetMapping(value = "delaycount")
    public ModelAndView goDelay(HttpSession session, ModelMap model) {

        String adminId = (String) session.getAttribute("admin_id");
        if (sessionValidation(adminId)) {
            return new ModelAndView("admin/admin_login");
        }

        model.addAttribute("info", adminService.selectAdminData(adminId));

        //TODO: 스케줄러 구현
        List<String> rentId = adminDao.selectdelayid();

        try {
            userService.updateDcount(rentId);
            return new ModelAndView("admin/delayinfo", Map.of(
                "dinfo", userService.selectDelay()
            ));
        } catch (Exception e) {
            return new ModelAndView("redirect:/admin");
        }
    }

    @PostMapping("refusebook")
    public String goRefuse(HttpSession session, ModelMap model,
        @RequestParam(name = "user_id") String[] user_id,
        @RequestParam(name = "user_penalty") String[] user_penalty) {

        String adminId = (String) session.getAttribute("admin_id");
        if (sessionValidation(adminId)) {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(adminId));
        try {
            userService.updatePenalty(user_id, user_penalty);
            model.addAttribute("rflist", userService.selectRefuseCount());
            return "admin/refuse";
        } catch (RuntimeException e) {
            return "redirect:/admin";
        }
    }

    private boolean sessionValidation(String adminId) {
        return Objects.isNull(adminId) || adminId.equals("");
    }

    @GetMapping("refusebook")
    public ModelAndView goRefuse(HttpSession session) {

        String adminId = (String) session.getAttribute("admin_id");
        if (sessionValidation(adminId)) {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/refuse", Map.of(
            "info", adminService.selectAdminData(adminId),
            "rflist", userService.selectRefuseCount()
        ));
    }

    @GetMapping("userpenaltycheck")
    public ModelAndView penaltyCheck(HttpSession session) {

        String adminId = (String) session.getAttribute("admin_id");
        if (sessionValidation(adminId)) {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/penaltycheck", Map.of(
            "info", adminService.selectAdminData(adminId),
            "dulist", userService.selectDelUser(),
            "ulist", userService.selectUserPcheck()
        ));
    }

    @PostMapping("deluser")
    public ModelAndView DelUser(HttpSession session,
        @RequestParam(name = "user_id") String[] userArray) {

        String adminId = (String) session.getAttribute("admin_id");
        if (sessionValidation(adminId)) {
            return new ModelAndView("admin/admin_login");
        }

        try {
            userService.updateDelUser(userArray);
            return new ModelAndView("redirect:/userpenaltycheck", Map.of(
                "info", adminService.selectAdminData(adminId)
            ));
        } catch (RuntimeException e) {
            return new ModelAndView("redirect:/admin");
        }
    }

}
