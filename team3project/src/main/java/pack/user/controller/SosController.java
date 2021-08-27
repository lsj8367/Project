package pack.user.controller;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pack.model.InqueryDto;
import pack.model.UserDto;
import pack.user.model.SosDao;
import pack.user.model.UserDao;

@Controller
@RequiredArgsConstructor
public class SosController {
	private final SosDao sosDao;
	private final UserDao userDao;

	@RequestMapping("sos") // 1:1 문의 버튼 눌렀을때 public String
	public ModelAndView sos(HttpSession session) {
		String inq_id = (String) session.getAttribute("id");
		ModelAndView modelAndView = new ModelAndView();
		
		if(Objects.isNull(inq_id) || Objects.equals(inq_id, "")) {
			modelAndView.setViewName("login");
			return modelAndView;
		}

		List<InqueryDto> inqList = sosDao.inqlist(inq_id);
		modelAndView.setViewName("sos");
		modelAndView.addObject("inqinfo",inqList);
		return modelAndView;
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

		UserDto dto = userDao.selectUser(id);
		model.addAttribute("inqinfo", dto);
		inqueryDto.setInq_id(id);
		boolean b = sosDao.insertInquery(inqueryDto);
		if (!b) {
			return "error";
		}

		return "redirect:/sos";
	}
}
