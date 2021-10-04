package pack.admin.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminDao;
import pack.admin.service.AdminService;
import pack.user.model.UserDto;

@Controller
@RequiredArgsConstructor
public class UserDataController {
    private final AdminDao adminDao;
    private final AdminService adminService;

    @GetMapping(value = "userdata")
    public ModelAndView goUser(HttpSession session, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        List<UserDto> userlist = adminDao.selectUserAll();

        modelAndView.setViewName("admin/userinfo");
        modelAndView.addObject("ulist", userlist);

        return modelAndView;
    }

}
