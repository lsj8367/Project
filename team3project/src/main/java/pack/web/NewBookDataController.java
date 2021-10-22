package pack.web;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.service.AdminService;

@Controller
@RequiredArgsConstructor
public class NewBookDataController {

    private final AdminService adminService;

    @GetMapping("newbookdata")
    public ModelAndView goUser(HttpSession session, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        modelAndView.setViewName("admin/nbinfo");
        modelAndView.addObject("nblist", adminService.selectBookDataAll());

        return modelAndView;
    }

}
