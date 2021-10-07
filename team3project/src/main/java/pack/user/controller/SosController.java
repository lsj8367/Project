package pack.user.controller;

import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private final UserService userService;

    @RequestMapping("sos") // 1:1 문의 버튼 눌렀을때 public String
    public ModelAndView sos(HttpSession session) {
        String inq_id = (String) session.getAttribute("id");
        if (Objects.isNull(inq_id) || Objects.equals(inq_id, "")) {
            return new ModelAndView("login");
        }

        return new ModelAndView("sos", Map.of(
            "inqinfo", inqueryService.selectInqList(inq_id)
        ));
    }


    @RequestMapping(value = "sospage", method = RequestMethod.GET) // 문의 안에서 문의하기 버튼 눌렀을때
    public String sospage() {
        return "sospage";
    }

    @RequestMapping(value = "sospage", method = RequestMethod.POST)
    public String submit(InqueryDto inqueryDto, HttpSession session, ModelMap model)
        throws Exception {
        String id = (String) session.getAttribute("id");

        if (Objects.isNull(id) || Objects.equals(id, "")) {
            return "login";
        }

        model.addAttribute("inqinfo", inqueryService.findAllOrderByInqOnumASC());
        inqueryDto.setInq_id(id);
        boolean b = inqueryService.insertInquery(inqueryDto);
        if (!b) {
            return "error";
        }

        return "redirect:/sos";
    }

}
