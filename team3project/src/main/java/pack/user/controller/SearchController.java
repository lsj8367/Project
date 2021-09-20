package pack.user.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.domain.entity.NewBook;
import pack.user.service.NewBookService;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final NewBookService newBookService;

    @RequestMapping("search")
    public ModelAndView DataAll(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String search = request.getParameter("search");

        List<NewBook> list = newBookService.getDataNewAllExist(search);
        modelAndView.addObject("newbooklist", list);
        modelAndView.setViewName("list");

        return modelAndView;
    }

}
