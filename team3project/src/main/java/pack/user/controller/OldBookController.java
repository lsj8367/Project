package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.controller.OldBookBean;
import pack.model.OldBookDto;
import pack.model.UserDto;
import pack.model.OldBookImpl;
import pack.model.UserImpl;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class OldBookController {
    private final OldBookImpl oldBookImpl;
    private final UserImpl userImpl;

    @RequestMapping("oldbook")
    public ModelAndView bookInfo(@RequestParam("book_no") String book_no) { //중고중에 1등급
        ModelAndView modelAndView = new ModelAndView();
        OldBookDto dto = oldBookImpl.bookInfo(book_no);
        boolean b = oldBookImpl.readcnt(Integer.parseInt(book_no));
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
        OldBookDto dto = oldBookImpl.rentalInfo(book_no);
        boolean b = oldBookImpl.readcnt(Integer.parseInt(book_no));
        if (b) {
            modelAndView.addObject("bookinfo", dto);

            UserDto rentUser = userImpl.selectUser(user_id);
            modelAndView.addObject("rentUser", rentUser);

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