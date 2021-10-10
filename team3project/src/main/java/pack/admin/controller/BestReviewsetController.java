package pack.admin.controller;

import java.util.Map;
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
import pack.admin.utils.PointState;
import pack.admin.utils.Rank;
import pack.user.model.UserDto;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class BestReviewsetController {

    private final AdminDao adminDao;
    private final AdminService adminService;
    private final UserService userService;
    private final PointState pointState;

    @GetMapping("bestreviewset")
    public ModelAndView goBestReviewset(HttpSession session) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/bestreviewset", Map.of(
            "info", adminService.selectAdminData(admin_id),
            "rm", adminDao.mbReviewMonth()
        ));
    }

    @PostMapping("monthbestreview")
    public ModelAndView goBestReview(HttpSession session, @RequestParam("sql") String sql) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/bestreviewset", Map.of(
            "info", adminService.selectAdminData(admin_id),
            "rm", adminDao.mbReviewMonth(),
            "rl", adminDao.mbEstReview(sql)
        ));
    }

    @PostMapping("givepoint")
    public String JikwonUpJik(HttpSession session, ModelMap model, UserDto bean,
        @RequestParam(name = "rn") int[] rank,
        @RequestParam(name = "review_id") String[] userid) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        try {
            for (int i = 0; i < userid.length; i++) {
                if (rank[i] > 3) {
                    rank[i] = -1;
                }
                Rank ranked = pointState.getPointStateMap(rank[i]);
                userService.updateUserPoint(userid[i], ranked.giveUserPoint());
            }
            return "redirect:/bestreviewset";
        } catch (Exception e) {
            return "redirect:/adminmain";
        }

    }

}
