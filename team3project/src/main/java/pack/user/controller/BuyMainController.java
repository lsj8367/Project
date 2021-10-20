package pack.user.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.newbook.service.NewBookService;

@Controller
@RequiredArgsConstructor
public class BuyMainController {

    private final NewBookService newBookService;

    @GetMapping("buymain")
    public ModelAndView main() {
        return new ModelAndView("buymain", Map.of(
            "bestseller", newBookService.bestSeller(),
            "readtop3", newBookService.readTop3(),
            "random10", newBookService.random10()
        ));
    }

}
