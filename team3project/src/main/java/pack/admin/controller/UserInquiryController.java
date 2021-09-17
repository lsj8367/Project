package pack.admin.controller;

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
import pack.model.InqueryDto;
import pack.service.InqueryService;

@Controller
@RequiredArgsConstructor
public class UserInquiryController {

    private final AdminService adminService;
    private final InqueryService inqueryService;

    private final AdminDao adminDao;

    @GetMapping("userinquiry")
    public ModelAndView goUserinquiry(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }
        modelAndView.addObject("info", adminService.selectAdminData(admin_id));
        modelAndView.addObject("il", inqueryService.findAllOrderByInqOnumASC());
        modelAndView.setViewName("admin/userinquirymanage");

        return modelAndView;
    }

    @GetMapping(value = "replyinquiry")
    public ModelAndView data(@RequestParam("no") int inq_no, HttpSession session, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        modelAndView.addObject("data", inqueryService.selectInqPart(Long.valueOf(inq_no)));
        modelAndView.addObject("m", inqueryService.getMaxNum());
        modelAndView.setViewName("admin/replyinquiry");

        return modelAndView;
    }

    @PostMapping(value = "replyinq")
    public ModelAndView submit(InqueryDto inqueryDto, HttpSession session, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        boolean b = inqueryService.updateOnum(inqueryDto.getInq_onum(), inqueryDto.getInq_gnum());

        if (b) {
            if (inqueryService.insertingReply(inqueryDto)) {
                modelAndView.setViewName("redirect:/userinquiry");
                return modelAndView;
            }
            modelAndView.setViewName("redirect:/admin");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/admin");
        return modelAndView;
    }

}
