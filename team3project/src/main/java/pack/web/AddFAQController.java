package pack.web;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.service.AdminService;
import pack.faqboard.domain.FaqBoard;
import pack.faqboard.service.FaqBoardService;
import pack.validation.AdminLoginValidation;
import pack.validation.LoginValidation;

@Controller
@RequiredArgsConstructor
public class AddFAQController {
    private final AdminService adminService;
    private final FaqBoardService faqBoardService;
    private final LoginValidation loginValidation = new AdminLoginValidation();

    @GetMapping("addfaq")
    public ModelAndView goAddfaq(HttpSession session) {
        return returnView("admin/addfaq", loginValidation.sessionCheck(session));
    }

    @GetMapping("faqadd")
    public ModelAndView insertFaqAfterLogin(HttpSession session, FaqBoard faqBoard) {
        faqBoardService.create(faqBoard);
        return returnView("redirect:admin/addfaq", loginValidation.sessionCheck(session));
    }

    private ModelAndView returnView(String url, String adminId) {
        return new ModelAndView(url, Map.of(
            "info", adminService.selectAdminData(adminId)
        ));
    }

}
