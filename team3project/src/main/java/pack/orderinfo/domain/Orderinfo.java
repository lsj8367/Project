package pack.orderinfo.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Orderinfo {

    @Id
    @GeneratedValue
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
    private LocalDateTime orderDate;

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
        this.orderDate = orderDate;
        this.orderPasswd = orderPasswd;
        this.orderScount = orderScount;
        this.orderPaytype = orderPaytype;
        this.orderState = orderState;
        this.orderSum = orderSum;
        this.orderAddress = orderAddress;
    }

}
