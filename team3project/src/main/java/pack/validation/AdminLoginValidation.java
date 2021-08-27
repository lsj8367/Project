package pack.validation;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
@Qualifier("adminLoginValidation")
public class AdminLoginValidation implements LoginValidation {
    private static final String URL = "admin/admin_login";

    @Override
    public ModelAndView sessionCheck(String adminId, ModelAndView modelAndView) {
        if(Objects.isNull(adminId) || adminId.equals("")) {
            modelAndView.setViewName(URL);
            return modelAndView;
        }

        return null;
    }

    @Override
    public String idCheck(String adminId) {
        if(Objects.isNull(adminId) || Objects.equals(adminId, "")) {
            return URL;
        }
        return null;
    }
}

