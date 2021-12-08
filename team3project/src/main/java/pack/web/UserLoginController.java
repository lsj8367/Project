package pack.web;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pack.user.domain.User;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserLoginController {

    private final UserService userService;

    @GetMapping(value = "login")
    public String moveLogin() {
        return "login";
    }

    @PostMapping(value = "login")
    public String login(@RequestParam("id") String id,
        @RequestParam("pwd") String pwd, HttpSession session) {
        User user = userService.selectUser(id);

        //페이지에 뿌릴 내용
        session.setAttribute("id", user.getUserId());
        session.setAttribute("name", user.getUserName());
        session.setAttribute("point", user.getUserPoint());
        return "redirect:/buymain";
    }

    //로그 아웃!
    @RequestMapping(value = "logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/buymain";
    }

}
