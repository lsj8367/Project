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
import pack.admin.service.AdminService;
import pack.rentinfo.model.RentInfoDto;
import pack.user.model.UserDto;

@Controller
@RequiredArgsConstructor
public class UserpenaltyController {
    private final AdminDao adminDao;
    private final AdminService adminService;

    @GetMapping("userpenalty")
    public ModelAndView goLongterm(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        List<RentInfoDto> rlist = adminDao.selectdelayAll();
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
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        if (rent_no.length == 0) {
            return "redirect:/delaycount";
        }

        for (int i = 0; i < rent_no.length; i++) {
            ubean.setUser_id(user_id[i]);
            ubean.setDelpoint(delpoint[i]);
            boolean b = adminDao.upuser(ubean);
            if (b) {
                adminService.removeOb(rent_no[i]);
            }
        }
        return "redirect:/delaycount";
    }

    @GetMapping(value = "delaycount")
    public String goDelay(HttpSession session, ModelMap model) {

        List<String> rent_id = adminDao.selectdelayid();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        boolean b = false;

        for (int i = 0; i < rent_id.size(); i++) {
            String rentid = rent_id.get(i);
            b = adminDao.updcount(rentid);
        }

        if (b) {
            List<UserDto> dlist = adminDao.selectdelay();
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
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        boolean b = false;

        for (int i = 0; i < user_id.length; i++) {
            bean.setUser_id(user_id[i]);
            bean.setUser_penalty(user_penalty[i]);
            b = adminDao.uppenalty(bean);
        }
        if (b) {
            model.addAttribute("rflist", adminDao.selectrefusecount());
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
        model.addAttribute("info", adminService.selectAdminData(admin_id));
        model.addAttribute("rflist", adminDao.selectrefusecount());
        return "admin/refuse";
    }

    @GetMapping( "userpenaltycheck")
    public String penaltyCheck(HttpSession session, ModelMap model) {

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null || admin_id == "") {
            return "admin/admin_login";
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));
        model.addAttribute("dulist", adminDao.selectdeluser());
        model.addAttribute("ulist", adminDao.selectuserpcheck());
        return "admin/penaltycheck";
    }

    @PostMapping( "deluser")
    public String DelUser(HttpSession session, ModelMap model,
                          @RequestParam(name = "user_id") String[] user_id) {

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        boolean b = false;

        for (String userId : user_id) {
            b = adminDao.updeluser(userId);
        }

        if (b) {
            return "redirect:/userpenaltycheck";
        }
        return "redirect:/admin";
    }
}
