package pack.admin.controller;

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
import pack.model.UserDto;

@Controller
@RequiredArgsConstructor
public class BestRentbooksetController {

    private final AdminDao adminDao;
    private final AdminService adminService;

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
    public String JikwonUpJik(HttpSession session, ModelMap model, UserDto bean,
        @RequestParam(name = "rn") int[] rank,
        @RequestParam(name = "ob_userid") String[] userid) {

        String admin_id = (String) session.getAttribute("admin_id");

        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        boolean b = false;

        for (int i = 0; i < userid.length; i++) {
            if (rank[i] == 1) {
                bean.setPluspoint(3000);
            } else if (rank[i] == 2) {
                bean.setPluspoint(1500);
            } else if (rank[i] == 3) {
                bean.setPluspoint(500);
            } else {
                bean.setPluspoint(0);
            }
            bean.setUser_id(userid[i]);
            b = adminDao.upUserPoint(bean);
        }

        if (b) {
            return "redirect:/bestrentbookset";
        }

        return "redirect:/adminmain";
    }

}