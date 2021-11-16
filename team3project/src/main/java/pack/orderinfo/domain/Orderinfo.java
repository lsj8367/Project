package pack.orderinfo.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Orderinfo")
@NoArgsConstructor
@Getter
@Setter
public class Orderinfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no")
    private Long orderNo;

    @Column(name = "orderlist_no")
    private String orderlistNo;

    @Column(name = "order_person")
    private String orderPerson;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "order_bookno")
    private int orderBookno;

    @Column(name = "order_booktype")
    private String orderBooktype;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "order_passwd")
    private String orderPasswd;

    @Column(name = "order_scount")
    private int orderScount;

    @Column(name = "order_paytype")
    private String orderPaytype;

    @Column(name = "order_state")
    private String orderState;

    @Column(name = "order_sum")
    private int orderSum;

    @Column(name = "order_address")
    private String orderAddress;
    @Builder
    public Orderinfo(String orderlistNo, String orderPerson, String orderId, int orderBookno, String orderBooktype, LocalDateTime orderDate, String orderPasswd, int orderScount, String orderPaytype, String orderState, int orderSum,
        String orderAddress) {
        this.orderlistNo = orderlistNo;
        this.orderPerson = orderPerson;
        this.orderId = orderId;
        this.orderBookno = orderBookno;
        this.orderBooktype = orderBooktype;
        this.orderDate = Timestamp.valueOf(orderDate);
        this.orderPasswd = orderPasswd;
        this.orderScount = orderScount;
        this.orderPaytype = orderPaytype;
        this.orderState = orderState;
        this.orderSum = orderSum;
        this.orderAddress = orderAddress;
    }

    public static Orderinfo init(Map<String, Object> map, String orderlistNo) {
        return Orderinfo.builder()
            .orderlistNo(orderlistNo)
            .orderId(String.valueOf(map.get("orderId")))
            .orderPerson(String.valueOf(map.get("orderPerson")))
            .orderSum(Integer.parseInt(String.valueOf(map.get("orderSum"))))
            .orderPaytype(String.valueOf(map.get("payType")))
            .orderPasswd(String.valueOf(map.get("userPasswd")))
            .orderAddress(String.valueOf(map.get("address")))
            .orderDate(LocalDateTime.now())
            .orderBookno((Integer) map.get("obNo"))
            .orderState("0")
            .orderScount(1)
            .build();
    }

}
