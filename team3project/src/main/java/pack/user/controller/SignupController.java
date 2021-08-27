package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.model.CardInfoDto;
import pack.model.UserDto;
import pack.user.model.CardInfoDao;
import pack.user.model.UserDao;

@Controller
@RequiredArgsConstructor
public class SignupController {
	private final UserDao userDao;
	private final CardInfoDao cardImpl;

	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String moveLogin() {
		return "signup";
	}

	// 아이디 중복 여부 체크
	@RequestMapping(value = "checkSignupId", method = RequestMethod.POST)
	public @ResponseBody String AjaxView(@RequestParam("id") String id) {
		String str = "";
		UserDto dto;
		if (userDao.selectUser(id).equals(null)) {
			str = "YES";
		} else {
			dto = userDao.selectUser(id);

			if (dto.getUser_id().equals(id)) { // 이미 존재하는 계정
				str = "NO";
			} else { // 사용 가능한 계정
				str = "YES";
			}
		}

		return str;
	}

	// 회원가입 -> 유저 추가
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String login(@RequestParam("id") String id, @RequestParam("pwd1") String pwd,
			@RequestParam("name") String name, @RequestParam("rrnumber1") String rrnumber1,
			@RequestParam("rrnumber2") String rrnumber2, @RequestParam("phone1") String phone1,
			@RequestParam("phone2") String phone2, @RequestParam("phone3") String phone3,
			@RequestParam("email1") String email1, @RequestParam("eamil2") String email2,
			@RequestParam("zipcode") String zipcode, @RequestParam("address1") String address1,
			@RequestParam("address2") String address2, @RequestParam("cardcomp") String cardcomp,
			@RequestParam("card1") String card1, @RequestParam("card2") String card2,
			@RequestParam("card3") String card3, @RequestParam("card4") String card4,
			@RequestParam("cardpwd") String cardpwd) {

		// 유저 삽입
		UserDto user = UserDto.builder()
			.user_id(id)
			.user_passwd(pwd)
			.user_name(name)
			.user_tel(phone1 + "-" + phone2 + "-" + phone3)
			.user_addr(address1 + " " + address2)
			.user_zip(zipcode)
			.user_mail(email1 + "@" + email2)
			.user_birth(rrnumber1 + "-" + rrnumber2)
			.build();

		// 카드 삽입
		CardInfoDto cardInfoDto = CardInfoDto.builder()
			.card_ownerid(id)
			.card_owner(name)
			.card_comp(cardcomp)
			.card_no(card1 + "-" + card2 + "-" + card3 + "-" + card4)
			.card_passwd(cardpwd)
			.build();

		boolean buser = userDao.insertUser(user);
		boolean bcard = cardImpl.insertCard(cardInfoDto);

		if (buser && bcard) {
			return "redirect:/buymain";
		}

		return "redirect:/signup";
	}
}
