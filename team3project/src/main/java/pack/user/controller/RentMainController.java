package pack.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RentMainController {

    @RequestMapping("rentmain1")
    public String moveRentmain() {
        return "rentmain";
    }

}
