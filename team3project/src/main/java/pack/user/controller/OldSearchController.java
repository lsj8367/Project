package pack.user.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.oldbook.service.OldBookService;
import pack.user.domain.OldSearch;

@Controller
@RequiredArgsConstructor
public class OldSearchController {

    private final OldBookService oldBookService;

    @RequestMapping("oldsearch")
    public ModelAndView DataAll(@RequestParam("type") String type,
        @RequestParam("search") String search) {
        return new ModelAndView("rentmain", Map.of(
            "oldbooklist", oldBookService.getDataAllExist(OldSearch.builder()
                .type(type)
                .search(search)
                .build())
        ));
    }

}
