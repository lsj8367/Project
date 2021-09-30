package pack.mypage.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.user.service.OldBookService;

@Controller
@RequiredArgsConstructor
public class MydonorController {
    private final OldBookService oldBookService;

    @RequestMapping("mydonor")
    public ModelAndView mydonorlist(HttpSession session) {
        String id = (String) session.getAttribute("id");
        return new ModelAndView("mypage/mydonor", Map.of(
            "dnabook", oldBookService.selectGiveList(id)
        ));
    }

}
