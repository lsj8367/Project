package pack.user.controller;

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
        ModelAndView modelAndView = new ModelAndView();

        OldBook oldBook = oldBookService.bookInfo(book_no);
        boolean b = oldBookDao.readcnt(Integer.parseInt(book_no));
        if (b) {
            modelAndView.addObject("bookinfo", oldBook);
            modelAndView.setViewName("oldbook");
            return modelAndView;
        } else {
            modelAndView.setViewName("error");
            return modelAndView;
        }
    }

    @RequestMapping("oldrental")
    public ModelAndView rentalInfo(@RequestParam("book_no") String book_no,
                                   HttpSession session) {// 중고중에 2,3등급

        String user_id = (String) session.getAttribute("id");

        ModelAndView modelAndView = new ModelAndView();

        boolean b = oldBookDao.readcnt(Integer.parseInt(book_no));
        if (b) {
            modelAndView.addObject("bookinfo", oldBookService.rentalInfo(book_no));

            UserDto user = userDao.selectUser(user_id);
            modelAndView.addObject("rentUser", user);

            modelAndView.setViewName("oldrental");

            return modelAndView;
        }

        modelAndView.setViewName("error");
        return modelAndView;
    }


    public String submit() throws Exception {

        return "oldbook";
    }


}