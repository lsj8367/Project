package pack.orderinfo.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.orderinfo.domain.Orderinfo;
import pack.orderinfo.repository.OrderinfoRepository;

@Service
@RequiredArgsConstructor
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
