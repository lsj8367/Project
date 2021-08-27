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
import pack.admin.model.AdminDao;
import pack.model.AdminDto;
import pack.model.InqueryDto;

@Controller
@RequiredArgsConstructor
public class UserInquiryController {
    private final AdminDao adminDao;

    @GetMapping("userinquiry")
    public ModelAndView goUserinquiry(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        List<InqueryDto> inqlist = adminDao.getinqlist();
        view.addObject("il", inqlist);
        view.setViewName("admin/userinquirymanage");

        return view;
    }

    @GetMapping(value = "replyinquiry")
    public ModelAndView data(@RequestParam("no") int inq_no, HttpSession session, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        modelAndView.addObject("data", adminDao.getInqData(inq_no));
        modelAndView.addObject("m", adminDao.getMaxNum());
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
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        boolean b = adminDao.upOnum(inqueryDto);

        if (b) {
            boolean r = adminDao.insInqReply(inqueryDto);
            if (r) {
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
