package pack.admin.controller;

import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.admin.service.AdminService;
import pack.newbook.model.NewBookDto;

@Controller
@RequiredArgsConstructor
public class BookRegisterController {

    private final AdminService adminService;

    @GetMapping("bookregister")
    public String insert(HttpSession session, ModelMap model) {
        String admin_id = (String) session.getAttribute("admin_id");
        if (admin_id == null || admin_id == "") {
            return "admin/admin_login";
        }

        model.addAttribute("info", adminService.selectAdminData(admin_id));

        return "admin/bookregister";
    }

    @PostMapping("bookregister")
    public String submit(NewBookDto newBookDto, HttpSession session, ModelMap model, @RequestParam("nb_image") String nb_image) throws Exception {
        String admin_id = (String) session.getAttribute("admin_id");
        if (Objects.isNull(admin_id) || Objects.equals(admin_id, "")) {
            return "admin/admin_login";
        }
        model.addAttribute("info", adminService.selectAdminData(admin_id));

        if (nb_image.equals("") | nb_image == null) {
            newBookDto.setNb_image("resources/static/images/notready.jpg");
        }

        adminService.insertBookData(newBookDto);

        return "redirect:/bookregister";
    }

}
