package pack.orderinfo.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.orderinfo.domain.Orderinfo;
import pack.orderinfo.model.OrderInfoDto;
import pack.orderinfo.repository.OrderinfoRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderInfoService {

    private final OrderinfoRepository orderinfoRepository;

    public List<Orderinfo> selectNewBookOrderAll() {
        return orderinfoRepository.selectNewBookOrderAll();
    }

    public List<Orderinfo> selectOldBookOrderAll() {
        return orderinfoRepository.selectOldBookOrderAll();
    }

    public List<Orderinfo> noBankAll() {
        return orderinfoRepository.findAllByOrderStateGroupByOrderListNo();
    }

    public List<Orderinfo> notNoBankAll() {
        return orderinfoRepository.notEqualOrderStateGroupByOrderListNo();
    }

    public List<Orderinfo> selectDelayDeposit() {
        List<Orderinfo> resultList = orderinfoRepository.findAll();
        return resultList.stream()
            .filter(this::isOrderStateEqualZero)
            .filter(this::isBookTypeEqualToOne)
            .filter(this::isDifferenceGreaterThen2Days)
            .collect(Collectors.toList());
    }

    public void updateOrderState(int id, String orderState) {
        final Orderinfo orderinfo = orderinfoRepository.findById((long) id).orElseThrow(() -> new RuntimeException("해당하는 주문 없음"));
        orderinfo.setOrderState(orderState);
    }

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

    private boolean isOrderStateEqualZero(Orderinfo orderinfo) {
        return orderinfo.getOrderState().equals("0");
    }

    private boolean isBookTypeEqualToOne(Orderinfo orderinfo) {
        return orderinfo.getOrderBooktype().equals("1");
    }

    private boolean isDifferenceGreaterThen2Days(Orderinfo orderinfo) {
        return ChronoUnit.DAYS.between(orderinfo.getOrderDate(), LocalDateTime.now()) > 2;
    }

}
