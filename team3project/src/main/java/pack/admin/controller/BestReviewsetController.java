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
import pack.model.UserDto;

@Controller
@RequiredArgsConstructor
public class BestReviewsetController {

    private final AdminDao adminDao;
    private final AdminService adminService;

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
        @RequestParam(name = "rn") int rank[],
        @RequestParam(name = "review_id") String userid[]) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        boolean b = false;

        for (int i = 0; i < userid.length; i++) {
            if (rank[i] == 1) {
                bean.setPluspoint(5000);
            } else if (rank[i] == 2) {
                bean.setPluspoint(3000);
            } else if (rank[i] == 3) {
                bean.setPluspoint(2000);
            } else {
                bean.setPluspoint(0);
            }
            bean.setUser_id(userid[i]);
            b = adminDao.upUserPoint(bean);
        }

        if (b) {
            return "redirect:/bestreviewset";
        }
        return "redirect:/adminmain";
    }

}
