package pack.user.controller;


import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import pack.review.model.ReviewDto;
import pack.user.domain.User;
import pack.user.model.ReviewDao;
import pack.user.model.UserDto;
import pack.user.service.CardInfoService;
import pack.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class NewbookController {

    private final ReviewDao reviewDao;
    private final CardInfoService cardInfoService;
    private final NewBookService newBookService;
    private final UserService userService;
    private final OrderInfoService orderInfoService;

    @GetMapping("newbook")
    public ModelAndView main(@RequestParam("book_no") String nb_no) {
        newBookService.plusReadCnt(Integer.parseInt(nb_no));
        NewBook newBook = newBookService.selectNewBook(Long.parseLong(nb_no));
        return new ModelAndView("newbook", Map.of(
            "newbook", newBook,
            "authorList", newBookService.selectAuthorList(newBook.getNbAuthor()),
            "reviewList", reviewDao.selectNewbookReviewList(Integer.parseInt(nb_no))
        ));
    }

    // 해당책의 리뷰 쓰기
    @PostMapping("writeReview")
    public String reviewWrite(@RequestParam("review_id") String review_id,
        @RequestParam("review_bookno") String review_bookno,
        @RequestParam("review_context") String review_context) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format_time = format.format(System.currentTimeMillis());

        ReviewDto bean = ReviewDto.builder()
            .review_id(review_id)
            .review_bookno(Integer.parseInt(review_bookno))
            .review_context(review_context)
            .review_date(format_time)
            .review_rate(0)
            .review_gonggam(0)
            .build();

        boolean b = reviewDao.insertNewbookReview(bean);
        if (b) {
            return "redirect:/newbook?book_no=" + review_bookno;
        } else {
            return "error";
        }
    }

    // 해당책의 리뷰 쓰기
    @GetMapping("plusGonggam")
    public String plusGonggam(@RequestParam("review_no") String review_no) {
        ReviewDto dto = reviewDao.selectNewbookReview(Integer.parseInt(review_no));
        boolean b = reviewDao.plusGonggam(Integer.parseInt(review_no));

        if (b) {
            return "redirect:/newbook?book_no=" + dto.getReview_bookno();
        } else {
            return "error";
        }
    }

    // 해당책의 리뷰 지우기
    @GetMapping("deleteReview")
    public String deleteReview(HttpSession session, @RequestParam("review_no") String review_no, HttpServletResponse response) throws Exception {
        ReviewDto dto = reviewDao.selectNewbookReview(Integer.parseInt(review_no));
        String id = (String) session.getAttribute("id");

        //아이디가 같을 때만 지울 수 있다.
        if (id.equals(dto.getReview_id())) {
            boolean b = reviewDao.deleteReview(Integer.parseInt(review_no));

            if (b) {
                return "redirect:/newbook?book_no=" + dto.getReview_bookno();
            }

            return "error";
        }
        response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<script>alert('일치하지 않는 계정입니다'); history.back(); </script>");
        out.flush();
        return "";
    }


    //바로구매 페이지로 넘어가기
    @PostMapping("directbuy")
    public ModelAndView moveDirectBuy(@RequestParam("order_bookno") String order_bookno,
        @RequestParam("id") String id, @RequestParam("orderscount") String orderscount) {

        ModelAndView modelAndView = new ModelAndView();
        NewBook orderBook = newBookService.selectNewBook(Long.parseLong(order_bookno));

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
        modelAndView.addObject("orderscount", orderscount);

        //주문한 책 금액 합계

        int order_sum = orderBook.getNbPrice() * Integer.parseInt(orderscount);

        modelAndView.addObject("order_sum", order_sum);

        //주문한 책 정보
        modelAndView.addObject("orderbook", orderBook);
        modelAndView.setViewName("directbuy");
        return modelAndView;
    }

    @GetMapping("directbuy_pay")
    public String directBuy(HttpSession session, HttpServletRequest request) {
        String orderId = request.getParameter("id");
        String orderBookno = request.getParameter("order_bookno");
        String orderScount = request.getParameter("order_scount");
        String orderSum = request.getParameter("order_sum");
        String radioPaytype = request.getParameter("radioPaytype");
        String orderpass = request.getParameter("orderpwd");
        String realpoint1 = request.getParameter("realpoint");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");

        int order_bookno = Integer.parseInt(orderBookno);

        int order_scount = Integer.parseInt(orderScount);

        int order_sum = Integer.parseInt(orderSum);

        int realpoint;
        if (realpoint1 == "") {
            realpoint = 0;
        } else {
            realpoint = Integer.parseInt(realpoint1);
        }

        //orderlist_no 부분
        String wdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        DecimalFormat df = new DecimalFormat("00");

        Random random = new Random();
        int count = random.nextInt(99) + 1;

        Orderinfo orderInfo = Orderinfo.builder()
            .orderlistNo(wdate + "-" + df.format(count))
            .orderDate(LocalDateTime.now())
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
                UserDto dto = new UserDto();
                dto.setUser_id(orderId);
                dto.setUser_point(realpoint);

                userService.usePoint(dto);
                User resultUser = userService.selectUser(orderId);
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

    private Orderinfo selectPayType(String radioPaytype, Orderinfo orderinfo, User user) {
        //카드결제일 경우
        orderinfo.setOrderPerson(user.getUserName());
        orderinfo.setOrderId(user.getUserId());

        if (radioPaytype.equals("카드결제")) {
            orderinfo.setOrderPaytype("1");//1은 카드결제
            orderinfo.setOrderState("1"); // 카드 결제는 주문 상태로 무조건 1로 된다
            return orderinfo;
        }
        //무통장입금일 경우
        orderinfo.setOrderPaytype("0");//0은 무통장입금
        orderinfo.setOrderState("0"); // 무통장입금는 주문 상태로 무조건 0로 된다
        return orderinfo;
    }

    // 비회원dl 구매했을 때 주문내역 불러오기
    @GetMapping("unmemberorder")
    public ModelAndView unmemberOrder(@RequestParam("order_no") int order_no,
        @RequestParam("order_passwd") String order_passwd) {
        final Orderinfo orderInfo = orderInfoService.selectAllById(order_no, order_passwd);

        return new ModelAndView("unmemberorder", Map.of(
            "orderDto", orderInfo,
            "newbook", newBookService.selectNewBook((long) orderInfo.getOrderBookno())
        ));
    }

}