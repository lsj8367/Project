package pack.mypage.controller;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.inquery.service.InqueryService;

@Controller
@RequiredArgsConstructor
public class MyInqController {
    private final InqueryService inqueryService;

    @RequestMapping("myqna")
    public ModelAndView myInqList(HttpSession session) {
        String user_id = (String) session.getAttribute("id");
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("mypage/myqna");
        modelAndView.addObject("inqlist", inqueryService.selectInqList(user_id));

        return modelAndView;
    }

}
