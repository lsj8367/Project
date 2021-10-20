package pack.user.controller;

import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pack.inquery.model.InqueryDto;
import pack.inquery.service.InqueryService;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class SosController {

    private final InqueryService inqueryService;

    @ExceptionHandler
    public ModelAndView error(RuntimeException e) {
        return new ModelAndView("login");
    }

    @RequestMapping("sos") // 1:1 문의 버튼 눌렀을때 public String
    public ModelAndView sos(HttpSession session) {
        String id = sessionCheck((String) session.getAttribute("id"));

        return new ModelAndView("sos", Map.of(
            "inqinfo", inqueryService.selectInqList(id)
        ));
    }

    @RequestMapping(value = "sospage", method = RequestMethod.GET) // 문의 안에서 문의하기 버튼 눌렀을때
    public String sospage() {
        return "sospage";
    }

    @RequestMapping(value = "sospage", method = RequestMethod.POST)
    public String submit(InqueryDto inqueryDto, HttpSession session, ModelMap model) throws Exception {
        String id = sessionCheck((String) session.getAttribute("id"));

        model.addAttribute("inqinfo", inqueryService.findAllOrderByInqOnumASC());
        inqueryDto.setInq_id(id);
        boolean b = inqueryService.insertInquery(inqueryDto);
        if (!b) {
            return "error";
        }

        return "redirect:/sos";
    }

    private String sessionCheck(String inq_id) {
        if (Objects.isNull(inq_id) || Objects.equals(inq_id, "")) {
            throw new RuntimeException("해당하는 로그인 정보 없음");
        }
        return inq_id;
    }

}
