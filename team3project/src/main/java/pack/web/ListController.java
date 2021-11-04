package pack.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.newbook.domain.NewBook;
import pack.newbook.service.NewBookService;

@Controller
@RequiredArgsConstructor
public class ListController {

    private final NewBookService newBookService;

    @GetMapping("list")
    public ModelAndView list(@RequestParam("book") String book) {
        return getGenre(editGenre().get(book));
    }

    public ModelAndView getGenre(String nb_genre) {
        List<NewBook> list;
        switch (nb_genre) {
            case "best":  //best30
                list = newBookService.selectBest30();
                break;
            case "new":  //신간
                list = newBookService.selectNew();
                break;
            case "all":  // 전체목록
                list = newBookService.selectBookAll();
                break;
            default:
                list = newBookService.selectGenre(nb_genre);
                break;
        }
        return viewAndObject(list);
    }

    private ModelAndView viewAndObject(List<NewBook> list) { //view로 보내는 메소드
        Map<String, Object> map = Map.of(
            "newbooklist", list
        );
        return new ModelAndView("list", map);
    }

    private Map<String, String> editGenre() {
        Map<String, String> map = new HashMap<>();
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