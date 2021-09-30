package pack.user.controller;

import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.user.service.BuyService;

@Controller
@RequiredArgsConstructor
public class BuyController {

    private final BuyService buyService;

    @PostMapping("buy")
    public ModelAndView buy(@RequestParam("ob_no") String ob_no, HttpSession session) {
        return buyService.buy(ob_no, (String) session.getAttribute("id"));
    }

}
