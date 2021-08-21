package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pack.model.NewBookDto;
import pack.model.NewBookListImpl;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BuyMainController {

	private final NewBookListImpl newBookListImpl;

	@RequestMapping("buymain")
	public ModelAndView main() {
		ModelAndView modelAndView = new ModelAndView();
		
		//베스트셀
		NewBookDto bestseller = newBookListImpl.selectBest();
		modelAndView.addObject("bestseller", bestseller);
		
		
		//조회수top3
		List<NewBookDto> readTop3List = newBookListImpl.selectReadTop3();
		modelAndView.addObject("readtop3", readTop3List);
		
		//랜덤10권		
		List<NewBookDto> randam10List = newBookListImpl.selectRandom10();
		modelAndView.addObject("random10", randam10List);


		modelAndView.setViewName("buymain");
		
		return modelAndView;
	}
}
