package pack.mypage.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.mypage.model.MydonorImpl;

@Controller
@RequiredArgsConstructor
public class MydonorController {

    private final MydonorImpl mydonorImpl;

    @RequestMapping("mydonor")
    public ModelAndView mydonorlist(HttpSession session) {
        String id = (String) session.getAttribute("id");
        return new ModelAndView("mypage/mydonor", Map.of(
            "dnabook", mydonorImpl.donorListbyId(id)
        ));
    }

}
