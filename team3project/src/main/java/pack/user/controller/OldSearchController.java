package pack.user.controller;

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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("oldbooklist", oldSearchService.getDataAllExist(OldSearch.builder()
            .type(type)
            .search(search)
            .build()));
        modelAndView.setViewName("rentmain");
        return modelAndView;
    }

}
