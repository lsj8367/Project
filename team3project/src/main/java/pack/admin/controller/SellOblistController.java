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
import pack.model.OldBookDto;

@Controller
@RequiredArgsConstructor
public class SellOblistController {
    private final AdminService adminService;
    private final AdminDao adminDao;

    @GetMapping("selloblist")
    public ModelAndView goUser(HttpSession session, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        List<OldBookDto> selloblist = adminDao.selectSellObAll();

        modelAndView.setViewName("admin/sellobinfo");
        modelAndView.addObject("soblist", selloblist);

		return modelAndView;
}
	
}
