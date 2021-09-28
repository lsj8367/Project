package pack.user.controller;

import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.domain.entity.OldBook;
import pack.model.UserDto;
import pack.user.model.OldBookDao;
import pack.user.model.UserDao;
import pack.user.service.OldBookService;

@Controller
@RequiredArgsConstructor
public class OldBookController {
    private final OldBookService oldBookService;
    private final OldBookDao oldBookDao;
    private final UserDao userDao;

    @RequestMapping("oldbook")
    public ModelAndView bookInfo(@RequestParam("book_no") String book_no) { //중고중에 1등급
        OldBook oldBook = oldBookService.bookInfo(book_no);
        boolean b = oldBookDao.readcnt(Integer.parseInt(book_no));
        if (b) {
            return new ModelAndView("oldbook", Map.of("bookinfo", oldBook));
        }
        return new ModelAndView("error");
    }

    @RequestMapping("oldrental")
    public ModelAndView rentalInfo(@RequestParam("book_no") String book_no,
                                   HttpSession session) {// 중고중에 2,3등급

        String user_id = (String) session.getAttribute("id");

        boolean b = oldBookDao.readcnt(Integer.parseInt(book_no));

        if (b) {
            UserDto user = userDao.selectUser(user_id);
            return new ModelAndView("oldrental", Map.of(
                "bookinfo", oldBookService.rentalInfo(book_no),
                "rentUser", user
            ));
        }
        return new ModelAndView("error");
    }


    public String submit() throws Exception {
        return "oldbook";
    }


}