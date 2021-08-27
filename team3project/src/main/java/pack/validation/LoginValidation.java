package pack.validation;

import org.springframework.web.servlet.ModelAndView;

public interface LoginValidation {
    ModelAndView sessionCheck(String adminId, ModelAndView modelAndView);
    String idCheck(String adminId);
}
