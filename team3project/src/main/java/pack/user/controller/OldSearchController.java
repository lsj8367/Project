package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.user.model.OldSearchDao;
import pack.user.domain.OldSearch;

@Controller
@RequiredArgsConstructor
public class OldSearchController {
	private final OldSearchDao oldSearchDao;
	
	@RequestMapping("oldsearch")
	public ModelAndView DataAll(@RequestParam("type") String type, @RequestParam("search") String search) {
		ModelAndView modelAndView = new ModelAndView();

		OldSearch oldSearch = OldSearch.builder()
									   .type(type)
									   .search(search)
									   .build();

		modelAndView.addObject("oldbooklist", oldSearchDao.getDataAll(oldSearch));
		modelAndView.setViewName("rentmain");

		return modelAndView;
	}
	
}
