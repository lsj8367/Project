package pack.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import pack.model.NewBookDto;
import pack.user.model.NewBookListInter;

@Controller
public class BuyMainController {

	private final NewBookListImpl newBookListImpl;

	@RequestMapping("buymain")
	public ModelAndView main() {
		ModelAndView modelAndView = new ModelAndView();
		
		//베스트셀
		NewBookDto bestseller = newBookInter.selectBest();
		modelAndView.addObject("bestseller", bestseller);
		
		
		//조회수top3
		List<NewBookDto> readTop3List = newBookInter.selectReadTop3();
		modelAndView.addObject("readtop3", readTop3List);
		
		//랜덤10권		
		List<NewBookDto> randam10List = newBookInter.selectRandom10();
		modelAndView.addObject("random10", randam10List);


		modelAndView.setViewName("buymain");
		
		return modelAndView;
	}
}
