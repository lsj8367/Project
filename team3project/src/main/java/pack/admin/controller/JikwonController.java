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
import pack.admin.model.AdminUpdateDto;
import pack.admin.service.AdminService;
import pack.model.AdminDto;

@Controller
@RequiredArgsConstructor
public class JikwonController {

    private final AdminDao adminDao;
    private final AdminService adminService;

    @GetMapping("jikwonok")
    public ModelAndView goJikwonRegister(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));
        view.addObject("newa", adminService.adminYetAll());
        view.setViewName("admin/jikwonok");

        return view;
    }

    @PostMapping("jikwonadmit")
    public String JikwonAdmit(HttpSession session, ModelMap model,
        @RequestParam(name = "adminId") String adminId[],
        @RequestParam(name = "adminAcc") int adminAcc[]) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null || admin_id == "") {
            return "admin/admin_login";
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        boolean b = false;

        for (int i = 0; i < adminId.length; i++) {
            if (adminAcc[i] == 1) {
                try {
                    adminService.deleteAdminInfo(adminId[i]);
                    b = true;
                }catch (Exception e) {
                    b = false;
                }
            }else {
                b = adminService.updateAdmin(AdminUpdateDto.of(adminId[i], adminAcc[i]));
            }
        }

        if (b) {
            return "redirect:/jikwonok";
        }

        return "redirect:/adminmain";
    }


    @GetMapping("jikwoninfo")
    public ModelAndView goJikwoninfo(HttpSession session, ModelMap model) {
        ModelAndView view = new ModelAndView();

        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null || admin_id == "") {
            view.setViewName("admin/admin_login");
            return view;
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        view.addObject("al", adminService.findAll());
        view.setViewName("admin/jikwoninfo");
        return view;
    }

    @PostMapping("upadminjik")
    public String JikwonUpJik(AdminDto adminDto, HttpSession session, ModelMap model,
        @RequestParam(name = "admin_id") String adminid[],
        @RequestParam(name = "admin_jik") String adminjik[]) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null | admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        boolean b = false;

        for (int i = 0; i < adminid.length; i++) {
            b = adminService.updateAdmin(AdminUpdateDto.of(adminid[i], adminjik[i]));
        }

        if (b) {
            return "redirect:/jikwoninfo";
        }
        return "redirect:/adminmain";
    }

}
