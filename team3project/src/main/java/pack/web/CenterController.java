package pack.web;

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
    public Map<String, Object> faqDetail(@RequestParam("no") String faqNo) {
        return returnMap(centerService.faqDetail(faqNo));
    }

    @PostMapping("qnaOrder")
    @ResponseBody
    public Map<String, Object> qnaOrder(@RequestParam("faq_type") String faqType) {
        return returnMap(centerService.qnaOrder(faqType));
    }

    @PostMapping("centerpage")
    public String centerPage(@RequestParam("page") String page, Model model) {
        final ReturnJsp returnJsp = new ReturnJsp(page);
        model.addAttribute("page", page);
        return returnJsp.getKey();
    }

    private Map<String, Object> returnMap(Object object) {
        return Map.of("datas", object);
    }

    private class ReturnJsp {
        private final Map<String, String> returnJspMap = Map.of(
            "order", "centerOrder",
            "deliver", "centerDeliver",
            "product", "centerProduct"
        );
        private final String key;

        public ReturnJsp(String key) {
            this.key = key;
        }

        public String getKey() {
            return returnJspMap.get(this.key);
        }
    }
}


