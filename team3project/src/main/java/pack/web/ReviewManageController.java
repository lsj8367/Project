package pack.web;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.admin.service.AdminService;
import pack.review.model.ReviewDto;

@Controller
@RequiredArgsConstructor
public class ReviewManageController {
    private final AdminService adminService;
    private final AdminDao adminDao;

    @GetMapping("reviewmanage")
    public ModelAndView goReviewmanage(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        List<ReviewDto> rvlist = adminDao.selectreviewAll();
        view.addObject("rv", rvlist);
        view.setViewName("admin/reviewmanage");

        return view;
    }

}