package pack.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PointTipController {

    @RequestMapping("point")
    public String pointTip() {
        return "point";
    }

}
