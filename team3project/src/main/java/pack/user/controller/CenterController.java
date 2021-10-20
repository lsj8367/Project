package pack.user.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.user.service.CenterService;

@Controller
@RequiredArgsConstructor
public class CenterController {

    private final CenterService centerService;

    @GetMapping(value = "center")
    public String centerC() {
        return "center";
    }

    @PostMapping("qnaAll")
    @ResponseBody
    public Map<String, Object> qnaAll() {
        return returnMap(centerService.qnaAll());
    }

    @PostMapping("faqDetail")
    @ResponseBody
    public Map<String, Object> faqDetail(@RequestParam("no") String faq_no) {
        return returnMap(centerService.faqDetail(faq_no));
    }

    @PostMapping("qnaOrder")
    @ResponseBody
    public Map<String, Object> qnaOrder(@RequestParam("faq_type") String faq_type) {
        return returnMap(centerService.qnaOrder(faq_type));
    }

    @PostMapping("centerpage")
    public String centerPage(@RequestParam("page") String a, Model model) {

        String returnJsp = "";
        switch (a) {
            case "order":
                model.addAttribute("page", a);
                returnJsp = "centerOrder";
                break;
            case "deliver":
                model.addAttribute("page", a);
                returnJsp = "centerDeliver";
                break;
            case "product":
                model.addAttribute("page", a);
                returnJsp = "centerProduct";
                break;
            default:
                return "qnaAll";
        }

        return returnJsp;
    }

    private Map<String, Object> returnMap(Object object) {
        return Map.of("datas", object);
    }

}
