package pack.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.model.NewBookDto;
import pack.user.model.NewBookDao;

@Controller
@RequiredArgsConstructor
public class ListController {
	private final NewBookDao newBookDao;
	
	@PostMapping("list")
	public ModelAndView list(@RequestParam("book") String book) {
		Map<String, String> map = editGenre();
		return getGenre(map.get(book));
	}

	public ModelAndView getGenre(String nb_genre) {
		List<NewBookDto> list;
		if(nb_genre.equals("best")) { //best30
			list = newBookDao.getBest();
		}else if(nb_genre.equals("new")){ //신간
			list =  newBookDao.getNew();
		} else if (nb_genre.equals("all")) { // 전체목록
			list = newBookDao.getBookAll();
		}else {
			list = newBookDao.getGenre(nb_genre); //나머지 장르들
		}
		return viewAndObject(list);
	}
	
	private ModelAndView viewAndObject(List<NewBookDto> list) { //view로 보내는 메소드
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		modelAndView.addObject("newbooklist", list);
		return modelAndView;
	}
	
	private Map<String, String> editGenre() {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("a", "성공학");
		map.put("b", "소설");
		map.put("c", "에세이");
		map.put("d", "어린이");
		map.put("e", "유아");
		map.put("f", "경제경영");
		map.put("g", "인문학");
		map.put("h", "외국어");
		map.put("i", "사회과학");
		map.put("j", "수험서");
		map.put("k", "대학교재");
		map.put("l", "it");
		map.put("best", "best");
		map.put("new", "new");
		map.put("all", "all");
		return map;
	}
	
	
}
