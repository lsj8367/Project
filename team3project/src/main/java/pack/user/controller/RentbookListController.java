package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.user.model.OldBookListDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RentbookListController {
	private final OldBookListDao oldBookListDao;
	
	@RequestMapping("rentlist1")
	public ModelAndView rentlist(@RequestParam("book") String book) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		//장르
		Map<String, String> map = editGenre();
		
		//다독왕
		getBest(modelAndView);

		return getList(modelAndView, map.get(book)); //modelAndView 반환

	}
	
	private ModelAndView getList(ModelAndView modelAndView, String ob_genre) {
		List<OldBookDto> list;
		List<OldBookDto> list2;
		if(ob_genre.equals("rentmain")) {
			list = oldBookListDao.rentmain();
			list2 = oldBookListDao.rentmain2();
			modelAndView.addObject("oldbooklist", list); //1등급 책 리스트
			modelAndView.addObject("oldbooklow", list2); //2,3등급 책 리스트

			modelAndView.setViewName("rentmain");
			
		}else if (ob_genre.equals("high")) {
			list = oldBookListDao.selectHighAll();
			modelAndView.addObject("list", list);

			modelAndView.setViewName("alllist");
			
		}else if (ob_genre.equals("low")) {
			list = oldBookListDao.selectLowAll();
			modelAndView.addObject("list", list);
			modelAndView.setViewName("alllist");
			
		}else {
			list = oldBookListDao.selectGenre(ob_genre);
			list2 = oldBookListDao.selectGenre2(ob_genre);

			modelAndView.addObject("oldbooklist", list);
			modelAndView.addObject("oldbooklow", list2);
			modelAndView.setViewName("rentmain");
			
		}
		return modelAndView;
	}
	
	
	private void getBest(ModelAndView modelAndView) {
		OldBookDto best = oldBookListDao.bestOne();
		modelAndView.addObject("best", best);
		
		UserDto readbest = oldBookListDao.bestRead();
		modelAndView.addObject("readbest", readbest);
		System.out.println("getBest");
	}
	
	
	private Map<String, String> editGenre() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("a", "자기계발");
		map.put("b", "소설");
		map.put("c", "에세이");
		map.put("d", "어린이");
		map.put("e", "유아");
		map.put("f", "경제경영");
		map.put("g", "인문학");
		map.put("h", "외국어");
		map.put("i", "사회과학");
		map.put("j", "자격증");
		map.put("k", "대학교재");
		map.put("l", "it");
		map.put("rentmain", "rentmain");
		map.put("high", "high");
		map.put("low", "low");
		return map;
	}
	
}
