package pack.admin.controller;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.service.AdminService;

@Controller
@RequiredArgsConstructor
public class SellOblistController {
    private final AdminService adminService;

    @GetMapping("selloblist")
    public ModelAndView goUser(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }

        modelAndView.addObject("info", adminService.selectAdminData(admin_id));
        modelAndView.addObject("soblist", adminService.selectSellObAll());
        modelAndView.setViewName("admin/sellobinfo");

		return modelAndView;
}
	
}
