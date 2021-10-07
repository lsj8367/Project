package pack.validation;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

public interface LoginValidation {
    String sessionCheck(HttpSession httpSession, ModelAndView modelAndView);
    String sessionCheck(HttpSession session);
}
