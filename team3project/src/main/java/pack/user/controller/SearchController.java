package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.model.NewBookDto;
import pack.user.model.SearchDao;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {
	private final SearchDao searchDao;
	
	@RequestMapping("search")
	public ModelAndView DataAll(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String search = request.getParameter("search");
		
		List<NewBookDto> list = searchDao.getDataAll(search);
		modelAndView.addObject("newbooklist", list);
		modelAndView.setViewName("list");
		
		return modelAndView;
	}
	
}
