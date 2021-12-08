package pack.web;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pack.newbook.domain.NewBook;
import pack.newbook.service.NewBookService;
import pack.orderinfo.domain.Orderinfo;
import pack.orderinfo.service.OrderInfoService;
import pack.review.domain.Review;
import pack.review.service.ReviewService;
import pack.user.domain.User;
import pack.user.model.UserDto;
import pack.user.service.CardInfoService;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class NewbookController {

    private static final String NEWBOOK = "newbook";
    private static final String REDIRECT_URL = "redirect:/newbook?book_no=";

    private final CardInfoService cardInfoService;
    private final NewBookService newBookService;
    private final UserService userService;
    private final OrderInfoService orderInfoService;
    private final ReviewService reviewService;

    @GetMapping("newbook")
    public ModelAndView main(@RequestParam("book_no") String nbNo) {
        newBookService.plusReadCnt(Integer.parseInt(nbNo));
        final NewBook newBook = newBookService.selectNewBook(Long.parseLong(nbNo));
        return new ModelAndView(NEWBOOK, Map.of(
            NEWBOOK, newBook,
            "authorList", newBookService.selectAuthorList(newBook.getNbAuthor()),
            "reviewList", reviewService.findAllByBookNo(Integer.parseInt(nbNo))
        ));
    }

    // 해당책의 리뷰 쓰기
    @PostMapping("writeReview")
    public String reviewWrite(@RequestParam("review_id") String reviewId,
        @RequestParam("review_bookno") String reviewBookno,
        @RequestParam("review_context") String reviewContext) {

        reviewService.insert(Review.builder()
            .reviewId(reviewId)
            .reviewBookno(Long.parseLong(reviewBookno))
            .reviewContext(reviewContext)
            .reviewRate(0)
            .reviewGonggam(0)
            .build());

        return REDIRECT_URL + reviewBookno;
    }

    // 해당책의 리뷰 쓰기
    @GetMapping("plusGonggam")
    public String plusSympathy(@RequestParam("review_no") String reviewNo) {
        final Review review = reviewService.updateSympathy(Integer.parseInt(reviewNo));
        return REDIRECT_URL + review.getReviewBookno();
    }

    @GetMapping("deleteReview")
    public String deleteReview(@RequestParam("review_no") String reviewNo) {
        return REDIRECT_URL + reviewService.delete(reviewNo);
    }


    //바로구매 페이지로 넘어가기
    @PostMapping("directbuy")
    public ModelAndView moveDirectBuy(@RequestParam("order_bookno") String orderBookno,
        @RequestParam("id") String id, @RequestParam("orderscount") String orderScount) {

        ModelAndView modelAndView = new ModelAndView();
        final NewBook orderBook = newBookService.selectNewBook(Long.parseLong(orderBookno));

        //회원이면 할인된 가격
        if (!id.equals("")) {
            // 책 가격 설정

            orderBook.setNbPrice((int) (orderBook.getNbPrice() * 0.9));

            // 회원이면 등록된 카드 가져오기
            //결제 창에서 회원일 시 등록된 카드 가져오기
            modelAndView = cardInfoService.getCardInfo(id, modelAndView);

            // 회원이면 등록된 포인트 가져오기
            modelAndView.setViewName("directbuy");
            modelAndView.addObject("userDto", userService.selectUser(id));
        }
        //주문한 책의 개수 설정
        modelAndView.addObject("orderscount", orderScount);

        //주문한 책 금액 합계

        int orderSum = orderBook.getNbPrice() * Integer.parseInt(orderScount);

        modelAndView.addObject("order_sum", orderSum);

        //주문한 책 정보
        modelAndView.addObject("orderbook", orderBook);
        modelAndView.setViewName("directbuy");
        return modelAndView;
    }

    @GetMapping("directbuy_pay")
    public String directBuy(HttpSession session, HttpServletRequest request) throws NoSuchAlgorithmException {
        final String orderId = request.getParameter("id");
        final String orderBookno = request.getParameter("order_bookno");
        final String orderScount = request.getParameter("order_scount");
        final String orderSum = request.getParameter("order_sum");
        final String radioPaytype = request.getParameter("radioPaytype");
        final String orderpass = request.getParameter("orderpwd");
        final String realPointStr = request.getParameter("realpoint");
        final String address1 = request.getParameter("address1");
        final String address2 = request.getParameter("address2");
        final int order_bookno = Integer.parseInt(orderBookno);
        final int order_scount = Integer.parseInt(orderScount);
        final int order_sum = Integer.parseInt(orderSum);
        final int realpoint = convertToInt(realPointStr);

        //orderlist_no 부분
        final String wdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        final DecimalFormat df = new DecimalFormat("00");


        int count = SecureRandom.getInstanceStrong().nextInt(99) + 1;

        final Orderinfo orderInfo = Orderinfo.builder()
            .orderlistNo(wdate + "-" + df.format(count))
            .orderScount(order_scount)
            .orderSum(order_sum)
            .orderBookno(order_bookno)
            .orderBooktype("1")
            .orderAddress(address1 + " " + address2)
            .build();

        //회원일 경우
        if (!orderId.equals("")) {
            User user = userService.selectUser(orderId);

            //포인트 쓸경우 user_id랑 user_point만 가져온다.
            if (realpoint != 0) {
                final UserDto dto = new UserDto();
                final User resultUser = userService.selectUser(orderId);

                dto.setUser_id(orderId);
                dto.setUser_point(realpoint);

                userService.usePoint(dto);
                session.setAttribute("point", resultUser.getUserPoint());
            }

            selectPayType(radioPaytype, orderInfo, user);
            orderInfoService.saveOrderInfo(orderInfo);
            return "redirect:/buymain";
        }
        //비회원일 경우

        orderInfo.setOrderPaytype("0");//0은 무통장입금
        orderInfo.setOrderPasswd(orderpass);
        orderInfo.setOrderState("0"); // 무통장입금는 주문 상태로 무조건 0로 된다
        orderInfo.setOrderPerson("비회원");

        orderInfoService.saveOrderInfo(orderInfo);

        return "redirect:/unmemberorder?order_no=" + orderInfo.getOrderNo()
            + "&order_passwd=" + orderInfo.getOrderPasswd();
    }

    private int convertToInt(String realpoint) {
        if (Objects.isNull(realpoint) || realpoint.equals("")) {
            return 0;
        }
        return Integer.parseInt(realpoint);
    }

    private void selectPayType(String radioPaytype, Orderinfo orderinfo, User user) {
        //카드결제일 경우
        orderinfo.setOrderPerson(user.getUserName());
        orderinfo.setOrderId(user.getUserId());

        if (radioPaytype.equals("카드결제")) {
            orderinfo.setOrderPaytype("1");//1은 카드결제
            orderinfo.setOrderState("1"); // 카드 결제는 주문 상태로 무조건 1로 된다
        }
        //무통장입금일 경우
        orderinfo.setOrderPaytype("0");//0은 무통장입금
        orderinfo.setOrderState("0"); // 무통장입금는 주문 상태로 무조건 0로 된다
    }

    // 비회원dl 구매했을 때 주문내역 불러오기
    @GetMapping("unmemberorder")
    public ModelAndView unmemberOrder(@RequestParam("order_no") int orderNo,
        @RequestParam("order_passwd") String orderPasswd) {
        final Orderinfo orderInfo = orderInfoService.selectAllById(orderNo, orderPasswd);

        return new ModelAndView("unmemberorder", Map.of(
            "orderDto", orderInfo,
            NEWBOOK, newBookService.selectNewBook((long) orderInfo.getOrderBookno())
        ));
    }

}