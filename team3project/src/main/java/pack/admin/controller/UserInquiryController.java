package pack.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.model.AdminInter;
import pack.controller.InqueryBean;
import pack.model.AdminDto;
import pack.model.InqueryDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserInquiryController {
    private final AdminInter adminInter;

    @RequestMapping(value = "userinquiry", method = RequestMethod.GET)
    public ModelAndView goUserinquiry(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        List<InqueryDto> inqlist = adminInter.getinqlist();
        view.addObject("il", inqlist);
        view.setViewName("admin/userinquirymanage");

        return view;
    }

    @RequestMapping(value = "replyinquiry", method = RequestMethod.GET)
    public ModelAndView data(@RequestParam("no") int inq_no, HttpSession session, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }
        AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        modelAndView.addObject("data", adminInter.getInqData(inq_no));
        modelAndView.addObject("m", adminInter.getMaxNum());
        modelAndView.setViewName("admin/replyinquiry");

        return modelAndView;
    }

    @RequestMapping(value = "replyinq", method = RequestMethod.POST)
    public ModelAndView submit(InqueryBean bean, HttpSession session, ModelMap model) {
        ModelAndView modelAndView = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            modelAndView.setViewName("admin/admin_login");
            return modelAndView;
        }
        AdminDto dto = adminInter.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        boolean b = adminInter.upOnum(bean);

        if (b) {
            boolean r = adminInter.insInqReply(bean);
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
