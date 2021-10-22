package pack.web;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.mypage.service.MypageService;

@Controller
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    @RequestMapping("mypage")
    public ModelAndView list3(HttpSession session) {
        return mypageService.list3((String) session.getAttribute("id"));
    }

}