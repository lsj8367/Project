package pack.validation;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class AdminLoginValidation implements LoginValidation {
    private static final String URL = "admin/admin_login";

    @Override
    public String sessionCheck(HttpSession session, ModelAndView modelAndView) {
        String adminId = (String) session.getAttribute("admin_id");
        if(Objects.isNull(adminId) || adminId.equals("")) {
            modelAndView.setViewName(URL);
        }
        return adminId;
    }
}

