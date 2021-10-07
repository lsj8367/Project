package pack.oldbook.web;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.oldbook.domain.OldBook;
import pack.oldbook.service.OldBookService;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class OldBookController {
    private final OldBookService oldBookService;
    private final UserService userService;

    @RequestMapping("oldbook")
    public ModelAndView bookInfo(@RequestParam("book_no") String bookNo) { //중고중에 1등급
        OldBook oldBook = oldBookService.bookInfo(bookNo);

        if (oldBookService.updateReadCnt(Integer.parseInt(bookNo))) {
            return new ModelAndView("oldbook", Map.of("bookinfo", oldBook));
        }
        return new ModelAndView("error");
    }

    @RequestMapping("oldrental")
    public ModelAndView rentalInfo(@RequestParam("book_no") String bookNo,
                                   HttpSession session) {// 중고중에 2,3등급

        String userId = (String) session.getAttribute("id");

        if (oldBookService.updateReadCnt(Integer.parseInt(bookNo))) {
            return new ModelAndView("oldrental", Map.of(
                "bookinfo", oldBookService.rentalInfo(bookNo),
                "rentUser", userService.selectUser(userId)
            ));
        }
        return new ModelAndView("error");
    }

    @RequestMapping("mydonor")
    public ModelAndView mydonorlist(HttpSession session) {
        String id = (String) session.getAttribute("id");
        return new ModelAndView("mypage/mydonor", Map.of(
            "dnabook", oldBookService.selectGiveList(id)
        ));
    }

    public String submit() throws Exception {
        return "oldbook";
    }

}