package pack.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RentMainController {
	
	
	@RequestMapping("rentmain1")
	public String moveRentmain() {
		return "rentmain";
	}
	
}
