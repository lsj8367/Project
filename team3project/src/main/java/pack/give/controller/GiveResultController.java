package pack.give.controller;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.give.dao.GiveDao;
import pack.oldbook.service.OldBookService;

@Controller
@RequiredArgsConstructor
public class GiveResultController {
    private final OldBookService oldBookService;
    private final GiveDao giveDao;

    @RequestMapping(value = "showgive")
    @ResponseBody
    public Map<String, Object> getGiveResult(HttpSession session) {
        String ob_userid = (String) session.getAttribute("id");
        return Map.of(
            "datas", List.of(oldBookService.selectGiveList(ob_userid))
        );
    }

}
