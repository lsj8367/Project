package pack.user.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.user.domain.OldSearch;
import pack.user.service.OldSearchService;

@Controller
@RequiredArgsConstructor
public class OldSearchController {

    private final OldSearchService oldSearchService;

    @RequestMapping("oldsearch")
    public ModelAndView DataAll(@RequestParam("type") String type, @RequestParam("search") String search) {
        return new ModelAndView("rentmain", Map.of(
            "oldbooklist", oldSearchService.getDataAllExist(OldSearch.builder()
                                               .type(type)
                                               .search(search)
                                               .build())
        ));
    }

}
