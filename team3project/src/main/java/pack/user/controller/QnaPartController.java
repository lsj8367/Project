package pack.user.controller;

import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.user.service.QnaPartService;

@Controller
@RequiredArgsConstructor
public class QnaPartController {

    private final QnaPartService qnaPartService;

    @RequestMapping("qnaPart")
    @ResponseBody
    public Map<String, Object> qnaPart(@RequestParam("order") String qna_class) {
        return Map.of("datas", qnaPartService.qnaPart(qna_class));
    }

}
