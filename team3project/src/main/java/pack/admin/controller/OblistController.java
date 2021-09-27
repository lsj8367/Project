package pack.admin.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.service.AdminService;
import pack.domain.entity.OldBook;

@Controller
@RequiredArgsConstructor
public class OblistController {

    private final AdminService adminService;

    @GetMapping("oblist")
    public ModelAndView getRentBook(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        List<OldBook> rbList = adminService.selectRentBookAll();

        view.addObject("rblist", rbList);
        view.setViewName("admin/rbookinfo");

        return view;
    }

}
