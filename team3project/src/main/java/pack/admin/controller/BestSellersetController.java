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
import pack.model.NewBookDto;

@Controller
@RequiredArgsConstructor
public class BestSellersetController {

    private final AdminDao adminDao;
    private final AdminService adminService;

    @GetMapping("bestsellerset")
    public ModelAndView goBestSellerset(HttpSession session) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return new ModelAndView("admin/admin_login");
        }

        return new ModelAndView("admin/bestsellerset", Map.of(
            "info", adminService.selectAdminData(admin_id),
            "om", adminDao.mbSellerMonth()
        ));
    }

    @PostMapping("monthbestseller")
    public ModelAndView goBestReview(HttpSession session, @RequestParam("sql") String sql) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return new ModelAndView("admin/admin_login");
        }
        return new ModelAndView("admin/bestsellerset", Map.of(
            "info", adminService.selectAdminData(admin_id),
            "om", adminDao.mbSellerMonth(),
            "ol", adminDao.mbestSeller(sql)
        ));
    }

    @PostMapping("addstock")
    public String addStock(HttpSession session, ModelMap model, NewBookDto newBookDto,
        @RequestParam(name = "rn") int[] rank,
        @RequestParam(name = "nb_no") int[] no) {

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null || admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        try {
            adminService.upNbStock(newBookDto.getNb_no(), rank, no);
            return "redirect:/bestsellerset";
        }catch (Exception e) {
            return "redirect:/adminmain";
        }
    }

}
