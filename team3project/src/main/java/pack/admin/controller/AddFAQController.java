package pack.admin.controller;

import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.admin.service.AddFAQService;
import pack.faqboard.domain.FaqBoard;
import pack.validation.AdminLoginValidation;
import pack.validation.LoginValidation;

@Controller
@RequiredArgsConstructor
public class AddFAQController {
    private final AddFAQService addFAQService;

    private final LoginValidation loginValidation = new AdminLoginValidation();

    @GetMapping("addfaq")
    public ModelAndView goAddfaq(HttpSession session) {
        ModelAndView view = new ModelAndView();
        String adminId = loginValidation.sessionCheck(session, view);
        return isViewNameExist(view) ? view : addFAQService.getAdminLoginInfo(view, adminId);
    }

    @GetMapping("faqadd")
    public ModelAndView insertFaqAfterLogin(HttpSession session, FaqBoard faqBoard) {
        ModelAndView view = new ModelAndView();
        String adminId = loginValidation.sessionCheck(session, view);
        return addFAQService.insertFaqAfterLogin(view, faqBoard, adminId);
    }

    private boolean isViewNameExist(final ModelAndView view) {
        return !Objects.isNull(view.getViewName());
    }
}
