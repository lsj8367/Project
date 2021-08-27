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
import pack.model.RentInfoDto;
import pack.model.UserDto;

@Controller
@RequiredArgsConstructor
public class UserpenaltyController {
    private final AdminDao adminDao;

    @GetMapping("userpenalty")
    public ModelAndView goLongterm(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        List<RentInfoDto> rlist = adminDao.getDelayData();
        view.addObject("longd", rlist);
        view.setViewName("admin/longdelay");

        return view;
    }

    @PostMapping(value = "delay")
    public String goDelayCount(HttpSession session, ModelMap model, UserDto ubean,
                               @RequestParam(name = "rent_no") int[] rent_no,
                               @RequestParam(name = "user_id") String[] user_id,
                               @RequestParam(name = "delpoint") int[] delpoint) {

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        if (rent_no.length == 0) {
            return "redirect:/delaycount";
        }
        boolean o = false;

        for (int i = 0; i < rent_no.length; i++) {
            ubean.setUser_id(user_id[i]);
            ubean.setDelpoint(delpoint[i]);
            boolean b = adminDao.updateUser(ubean);
            if (b) {
                o = adminDao.removeOb(rent_no[i]);
            }
        }
        if (o) {
            return "redirect:/delaycount";
        }

        return "redirect:/admin";
    }

    @GetMapping(value = "delaycount")
    public String goDelay(HttpSession session, ModelMap model) {

        List<String> rent_id = adminDao.getDelayId();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }

        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        boolean b = false;

        for (int i = 0; i < rent_id.size(); i++) {
            String rentid = rent_id.get(i);
            b = adminDao.updateDcount(rentid);
        }

        if (b) {
            List<UserDto> dlist = adminDao.getDelay();
            model.addAttribute("dinfo", dlist);
            return "admin/delayinfo";
        }
        return "redirect:/admin";
    }

    @PostMapping("refusebook")
    public String goRefuse(HttpSession session, ModelMap model, UserDto bean,
                           @RequestParam(name = "user_id") String[] user_id,
                           @RequestParam(name = "user_penalty") String[] user_penalty) {

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null || admin_id == "") {
            return "admin/admin_login";
        }
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        boolean b = false;

        for (int i = 0; i < user_id.length; i++) {
            bean.setUser_id(user_id[i]);
            bean.setUser_penalty(user_penalty[i]);
            b = adminDao.updatePenalty(bean);
        }
        if (b) {
            model.addAttribute("rflist", adminDao.getRefuse());
            return "admin/refuse";
        }
        return "redirect:/admin";
    }

    @GetMapping("refusebook")
    public String goRefuse(HttpSession session, ModelMap model) {

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null || admin_id == "") {
            return "admin/admin_login";
        }
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);
        model.addAttribute("rflist", adminDao.getRefuse());
        return "admin/refuse";
    }

    @GetMapping( "userpenaltycheck")
    public String penaltyCheck(HttpSession session, ModelMap model) {

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null || admin_id == "") {
            return "admin/admin_login";
        }
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);
        model.addAttribute("dulist", adminDao.getUserdel());
        model.addAttribute("ulist", adminDao.getUsercheck());
        return "admin/penaltycheck";
    }

    @PostMapping( "deluser")
    public String DelUser(HttpSession session, ModelMap model,
                          @RequestParam(name = "user_id") String[] user_id) {

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }
        AdminDto dto = adminDao.getAdminLoginInfo(admin_id);
        model.addAttribute("info", dto);

        boolean b = false;

        for (String userId : user_id) {
            b = adminDao.updateDelUser(userId);
        }

        if (b) {
            return "redirect:/userpenaltycheck";
        }
        return "redirect:/admin";
    }
}
