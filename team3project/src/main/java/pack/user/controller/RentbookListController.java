package pack.user.controller;

import java.util.HashMap;
import java.util.Map;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.model.UserDto;
import pack.user.model.OldBookListDao;
import pack.user.service.RentBookListService;

@Controller
@RequiredArgsConstructor
@Transactional
public class RentbookListController {

    private final RentBookListService rentBookListService;
    private final OldBookListDao oldBookListDao;

    @RequestMapping("rentlist1")
    public ModelAndView rentlist(@RequestParam("book") String book) {
        ModelAndView modelAndView = new ModelAndView();
        getBest(modelAndView);
        return getList(modelAndView, editGenre().get(book)); //modelAndView 반환
    }

    private ModelAndView getList(ModelAndView modelAndView, String ob_genre) {
        if (ob_genre.equals("rentmain")) {
            return new ModelAndView("rentmain", Map.of(
                "oldbooklist", rentBookListService.randomFirstGrade(),
                "oldbooklow", rentBookListService.oldLowLimit2()
            ));
        }
        if (ob_genre.equals("high")) {
            modelAndView.addObject("list", rentBookListService.oldHigh());
            modelAndView.setViewName("alllist");
            return modelAndView;
        }

        if (ob_genre.equals("low")) {
            modelAndView.addObject("list", rentBookListService.oldLow());
            modelAndView.setViewName("alllist");

        } else {
            modelAndView.addObject("oldbooklist", rentBookListService.genreForFirstGrade(ob_genre));
            modelAndView.addObject("oldbooklow", rentBookListService.genreForAnotherGrade(ob_genre));
            modelAndView.setViewName("rentmain");
        }
        return modelAndView;
    }


    private void getBest(ModelAndView modelAndView) {
        modelAndView.addObject("best", rentBookListService.selectBestRentBook());

        UserDto readbest = oldBookListDao.bestRead();
        modelAndView.addObject("readbest", readbest);
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
