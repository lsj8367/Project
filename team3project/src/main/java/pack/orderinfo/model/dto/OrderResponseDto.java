package pack.orderinfo.model.dto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pack.orderinfo.domain.Orderinfo;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderResponseDto {
    private String orderListNo;
    private String orderPerson;
    private String orderDelay;
    private String orderState;
    private String orderScount;
    private String orderBookNo;

    @Builder
    private OrderResponseDto(String orderListNo, String orderPerson, String orderDelay, String orderState, String orderScount, String orderBookNo) {
        this.orderListNo = orderListNo;
        this.orderPerson = orderPerson;
        this.orderDelay = orderDelay;
        this.orderState = orderState;
        this.orderScount = orderScount;
        this.orderBookNo = orderBookNo;
    }

    public static List<OrderResponseDto> of(List<Orderinfo> orderinfoList) {
        List<OrderResponseDto> resultList = new ArrayList<>();
        for (Orderinfo orderinfo : orderinfoList) {
            resultList.add(OrderResponseDto.builder()
                                           .orderListNo(orderinfo.getOrderlistNo())
                                           .orderPerson(orderinfo.getOrderPerson())
                                           .orderDelay(String.valueOf(ChronoUnit.DAYS.between(orderinfo.getOrderDate(), LocalDateTime.now())))
                                           .orderState(orderinfo.getOrderState())
                                           .orderScount(String.valueOf(orderinfo.getOrderScount()))
                                           .orderBookNo(String.valueOf(orderinfo.getOrderBookno()))
                .build());
        }
        return resultList;
    }

}
