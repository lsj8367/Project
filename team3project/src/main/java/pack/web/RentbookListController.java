package pack.web;

import java.util.HashMap;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.oldbook.service.OldBookService;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
@Transactional
public class RentbookListController {

    private static final String RENTMAIN = "rentmain";

    private final OldBookService oldBookService;
    private final UserService userService;

    @RequestMapping("rentlist1")
    public ModelAndView rentList(@RequestParam("book") String book) {
        ModelAndView modelAndView = new ModelAndView();
        getBest(modelAndView);
        return getList(modelAndView, editGenre().get(book)); //modelAndView 반환
    }

    private ModelAndView getList(ModelAndView modelAndView, String obGenre) {
        if (obGenre.equals(RENTMAIN)) {
            modelAndView.addObject("oldbooklist", oldBookService.randomFirstGrade());
            modelAndView.addObject("oldbooklow", oldBookService.oldLowLimit2());
            modelAndView.setViewName(RENTMAIN);
            return modelAndView;
        }
        if (obGenre.equals("high")) {
            modelAndView.addObject("list", oldBookService.oldHigh());
            modelAndView.setViewName("alllist");
            return modelAndView;
        }

        if (obGenre.equals("low")) {
            modelAndView.addObject("list", oldBookService.oldLow());
            modelAndView.setViewName("alllist");
            return modelAndView;
        }

        modelAndView.addObject("oldbooklist", oldBookService.genreForFirstGrade(obGenre));
        modelAndView.addObject("oldbooklow", oldBookService.genreForAnotherGrade(obGenre));
        modelAndView.setViewName(RENTMAIN);

        return modelAndView;
    }


    private void getBest(ModelAndView modelAndView) {
        modelAndView.addObject("best", oldBookService.selectBestRentBook());
        modelAndView.addObject("readbest", userService.selectBestRead());
    }

    private Map<String, String> editGenre() {
        Map<String, String> map = new HashMap<>();

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
        map.put(RENTMAIN, RENTMAIN);
        map.put("high", "high");
        map.put("low", "low");
        return map;
    }

}
