package pack.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pack.cardinfo.domain.CardInfo;
import pack.user.domain.User;
import pack.user.service.SignUpService;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class SignupController {

    private final SignUpService signUpService;
    private final UserService userService;

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String moveLogin() {
        return "signup";
    }

    // 아이디 중복 여부 체크
    @RequestMapping(value = "checkSignupId", method = RequestMethod.POST)
    public @ResponseBody String AjaxView(@RequestParam("id") String id) {
        if (userService.validationCheck(id)) { // 이미 존재하는 계정
            return "NO";
        }
        return "YES";
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

        userService.signUp(User.builder()
            .userId(id)
            .userPasswd(pwd)
            .userName(name)
            .userTel(phone1 + "-" + phone2 + "-" + phone3)
            .userAddr(address1 + " " + address2)
            .userZip(zipcode)
            .userMail(email1 + "@" + email2)
            .userBirth(rrnumber1 + "-" + rrnumber2)
            .build());


        signUpService.insertCard(CardInfo.builder()
            .cardOwnerid(id)
            .cardOwner(name)
            .cardComp(cardcomp)
            .cardNo(card1 + "-" + card2 + "-" + card3 + "-" + card4)
            .cardPasswd(cardpwd)
            .build());

        return "redirect:/buymain";
    }

}
