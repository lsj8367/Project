package pack.web;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.admin.model.AdminDao;
import pack.admin.service.AdminService;
import pack.admin.utils.PointState;
import pack.admin.utils.Rank;
import pack.orderinfo.service.OrderInfoService;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class MonthBuyerController {

    private final AdminDao adminDao;
    private final AdminService adminService;
    private final PointState pointState;
    private final UserService userService;
    private final OrderInfoService orderInfoService;

    @PostMapping(value = "givepoint4")
    public String JikwonUpJik(HttpSession session, ModelMap model,
        @RequestParam(name = "rn") int[] rank,
        @RequestParam(name = "user_id") String[] userid) {
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
            return "redirect:/monthbuyer";
        } catch (Exception e) {
            return "redirect:/adminmain";
        }
    }

}
