package pack.web;

import java.util.List;
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
import pack.orderinfo.model.OrderInfoDto;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class MonthBuyerController {

    private final AdminDao adminDao;
    private final AdminService adminService;
    private final PointState pointState;
    private final UserService userService;

    @GetMapping("monthbuyer")
    public ModelAndView goMonthBuyer(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        String cmonth = adminDao.currentMonth();
        List<OrderInfoDto> bulist = adminDao.buyKing();
        view.addObject("cm", cmonth);
        view.addObject("bu", bulist);
        view.setViewName("admin/monthbuyer");

        return view;
    }

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
