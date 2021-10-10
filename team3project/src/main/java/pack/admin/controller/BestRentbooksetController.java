package pack.admin.controller;

import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.admin.service.AdminService;
import pack.admin.utils.PointState;
import pack.admin.utils.Rank;
import pack.user.service.UserService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BestRentbooksetController {

    private final AdminDao adminDao;
    private final AdminService adminService;
    private final PointState pointState;
    private final UserService userService;

    @ExceptionHandler
    public ModelAndView exception(Exception e) {
        log.error(e.getMessage());
        return new ModelAndView("redirect:/adminmain");
    }

    @GetMapping("bestrentbookset")
    public ModelAndView goBestRentbookset(HttpSession session) {
        String admin_id = (String) session.getAttribute("admin_id");

        if (Objects.isNull(admin_id) || admin_id.equals("")) {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/bestrentbookset", Map.of(
            "info", adminService.selectAdminData(admin_id),
            "rtm", adminDao.mbRentMonth()
        ));
    }

    @PostMapping("monthbestrent")
    public ModelAndView goBestReview(HttpSession session, @RequestParam("sql") String sql) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/bestrentbookset", Map.of(
            "info", adminService.selectAdminData(admin_id),
            "rtm", adminDao.mbRentMonth(),
            "rtl", adminDao.mbEstRent(sql)
        ));
    }

    @PostMapping("givepoint2")
    public String JikwonUpJik(HttpSession session, ModelMap model,
        @RequestParam(name = "rn") int[] rank,
        @RequestParam(name = "ob_userid") String[] userid) {

        String admin_id = (String) session.getAttribute("admin_id");

        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        for (int i = 0; i < userid.length; i++) {
            if (rank[i] > 3) {
                rank[i] = -1;
            }
            Rank ranked = pointState.getPointStateMap(rank[i]);
            userService.updateUserPoint(userid[i], ranked.giveUserPoint());
        }
        return "redirect:/bestrentbookset";
    }

}