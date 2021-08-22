package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.model.OldBookDto;
import pack.model.OldSearchInter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
@RequiredArgsConstructor
public class OldSearchController {
	private final OldSearchInter oldSearchInter;
	
	@RequestMapping("oldsearch")
	public ModelAndView DataAll(HttpServletRequest request, HttpServletResponse response, Model model) {
		ModelAndView modelAndView = new ModelAndView();
		
		String type = request.getParameter("type");
		String search = request.getParameter("search");
		
		ArrayList<OldBookDto> list = oldSearchInter.getDataAll(type, search);
		modelAndView.setViewName("rentmain");
		modelAndView.addObject("oldbooklist", list);
		
		return modelAndView;
	}
	
}
