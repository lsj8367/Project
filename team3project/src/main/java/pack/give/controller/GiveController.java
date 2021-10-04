package pack.give.controller;

import java.util.Iterator;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import pack.give.common.CommandMap;
import pack.give.service.GiveService;

@Controller
@SessionAttributes("user")
@RequiredArgsConstructor
public class GiveController {

    Log log = LogFactory.getLog(GiveController.class);

    private final GiveService giveService;

    @GetMapping("give")
    public String give() { // 기증메소드
        return "give";
    }

    @PostMapping("give")
    public ModelAndView donate(CommandMap commandMap, HttpServletRequest request, HttpSession session) throws Exception {
        //commandMap 사용
        ModelAndView mv = new ModelAndView("giveresult");
        //세션으로 id와 name 값을 받아서, db에 입력.
        String user_id = (String) session.getAttribute("id");
        String user_name = (String) session.getAttribute("name");
        commandMap.put("ob_userid", user_id);
        commandMap.put("ob_donor", user_name);

        giveService.insertOldBook(commandMap.getMap(), request);

        if (!commandMap.isEmpty()) {
            Iterator<Entry<String, Object>> iterator = commandMap.getMap().entrySet().iterator();
            Entry<String, Object> entry = null;

            while (iterator.hasNext()) {
                entry = iterator.next();
                log.debug("key : " + entry.getKey() + ", value : " + entry.getValue());
            }
        }
        mv.addObject(user_id);

        return mv;
    }


}
