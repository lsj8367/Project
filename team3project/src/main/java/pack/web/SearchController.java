package pack.web;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.newbook.service.NewBookService;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final NewBookService newBookService;

    @RequestMapping("search")
    public ModelAndView dataAll(@RequestParam("search") String search) {
        return new ModelAndView("list", Map.of(
            "newbooklist", newBookService.getDataNewAllExist(search)
        ));
    }
}
