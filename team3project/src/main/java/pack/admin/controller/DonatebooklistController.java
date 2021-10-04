package pack.admin.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.service.AdminService;
import pack.oldbook.domain.OldBook;

@Controller
@RequiredArgsConstructor
public class DonatebooklistController {

    private final AdminService adminService;

    @GetMapping("donatebooklist")
    public ModelAndView StandbyBook(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));
        List<OldBook> standByList = adminService.selectStandByAll();
        view.setViewName("admin/standby");
        view.addObject("slist", standByList);

        return view;
    }

    @PostMapping("standbyok")
    public String upState(@RequestParam(name = "ob_no") int ob_no[],
        @RequestParam(name = "ob_state") String ob_state[],
        HttpSession session, ModelMap model) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        try {
            for (int i = 0; i < ob_no.length; i++) {
                adminService.upObState(ob_no[i], ob_state[i]);
            }
            return "redirect:/donatebooklist";
        } catch (Exception e) {
            return "redirect:/adminmain";
        }
    }

}
