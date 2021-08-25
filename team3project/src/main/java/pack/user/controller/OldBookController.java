package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.controller.OldBookBean;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.user.model.OldBookDao;
import pack.user.model.UserDao;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OldBookController {
    private final OldBookDao oldBookDao;
    private final UserDao userDao;

    @RequestMapping("oldbook")
    public ModelAndView bookInfo(@RequestParam("book_no") String book_no) { //중고중에 1등급
        ModelAndView modelAndView = new ModelAndView();
        OldBookDto dto = oldBookDao.bookInfo(book_no);
        boolean b = oldBookDao.readcnt(Integer.parseInt(book_no));
        if (b) {
            modelAndView.addObject("bookinfo", dto);
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
        OldBookDto dto = oldBookDao.rentalInfo(book_no);
        boolean b = oldBookDao.readcnt(Integer.parseInt(book_no));
        if (b) {
            modelAndView.addObject("bookinfo", dto);

            UserDto user = userDao.selectUser(user_id);
            modelAndView.addObject("rentUser", user);

            modelAndView.setViewName("oldrental");

            return modelAndView;
        }

        modelAndView.setViewName("error");
        return modelAndView;
    }


    public String submit(OldBookBean bean) throws Exception {

        return "oldbook";
    }


}