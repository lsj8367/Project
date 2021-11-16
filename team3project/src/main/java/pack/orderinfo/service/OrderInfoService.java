package pack.orderinfo.service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import pack.orderinfo.domain.MemberEnum;
import pack.orderinfo.domain.Orderinfo;
import pack.orderinfo.model.OrderInfoDto;
import pack.orderinfo.repository.OrderinfoRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderInfoService {

    private final OrderinfoRepository orderinfoRepository;

    public void saveOrderInfo(Orderinfo orderinfo) {
        orderinfoRepository.save(orderinfo);
    }

    public Orderinfo selectAllById(int orderNo, String orderPassword) {
        return orderinfoRepository.findByOrderNoAndOrderPasswd(Long.valueOf(orderNo), orderPassword);
    }

    public List<Orderinfo> myOrderInfoAll(String orderListNo) {
        return orderinfoRepository.findAllByOrderlistNo(orderListNo);
    }

    public Orderinfo findByOrderInfo(String orderListNo) {
        return orderinfoRepository.findByOrderlistNo(orderListNo)
            .orElseThrow(() -> new RuntimeException("해당하는 주문 없음"));
    }

    public void updateOrderInfo(OrderInfoDto orderInfoDto) {
        final Orderinfo orderInfo = findByOrderInfo(orderInfoDto.getOrderlist_no());
        orderInfo.setOrderPasswd(orderInfoDto.getOrder_passwd());
        orderInfo.setOrderPerson(orderInfoDto.getOrder_person());
    }

    public Orderinfo findByUnmemberOrder(String orderListNo, String orderPassword) {
        return orderinfoRepository.findByOrderlistNoAndOrderPasswd(orderListNo, orderPassword)
            .orElseThrow(() -> new RuntimeException("해당하는 비회원 주문이 없음"));
    }

    public List<Orderinfo> findTop3OrderList(String orderId) {
        return orderinfoRepository.findTop3ByOrderIdOrderByOrderDateDesc(orderId);
    }

    public List<Orderinfo> findOrderListAll(String orderId) {
        return orderinfoRepository.findAllByOrderIdOrderByOrderDateDesc(orderId);
    }

    public List<Orderinfo> findOldBookOrderListAll(String orderId) {
        return orderinfoRepository.findOldBookOrderListAll(orderId);
    }

    public List<Orderinfo> findNewBookOrderListAll(String orderId) {
        return orderinfoRepository.findNewBookOrderListAll(orderId);
    }

    public Orderinfo findByPerson(String orderPerson) {
        final List<Orderinfo> orderNo = orderinfoRepository.findByOrderPerson(orderPerson, Sort.by(Direction.DESC, "orderNo"));
        return orderNo.get(0);
    }

    public void deleteMyOrder(int orderNo) {
        orderinfoRepository.deleteById(Long.valueOf(orderNo));
    }

    public void order(HttpSession session, Map<String, Object> map) {
        Orderinfo orderinfo = Orderinfo.init(map, makeOrderNumber());

        if (isMember(session)) {
            MemberEnum.MEMBER.makeDataSet(orderinfo, "2");
            orderinfoRepository.save(orderinfo);
            return;
        }

        MemberEnum.NON_MEMBER.makeDataSet(orderinfo, "1");
        orderinfoRepository.save(orderinfo);

    }

    private String makeOrderNumber() {
        String wdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        DecimalFormat df = new DecimalFormat("00");
        Random random = new Random();
        return wdate + "-" + df.format(random.nextInt(99) + 1);
    }

    private boolean isMember(HttpSession session) {
        return Objects.nonNull(session.getAttribute("id"));
    }

}
