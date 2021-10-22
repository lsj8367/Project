package pack.orderinfo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;
import pack.config.QuerydslConfig;
import pack.newbook.domain.NewBook;
import pack.newbook.repository.NewBookRepository;
import pack.oldbook.domain.OldBook;
import pack.oldbook.repository.OldBookRepository;
import pack.orderinfo.domain.Orderinfo;

@DataJpaTest
@Import(QuerydslConfig.class)
class OrderinfoRepositoryTest {

    @Autowired
    private OrderinfoRepository orderinfoRepository;

    @Autowired
    private NewBookRepository newBookRepository;

    @Autowired
    private OldBookRepository oldBookRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void selectNewBookOrderAll() {
        NewBook newBook = newBookRepository.saveAndFlush(NewBook.builder()
            .nbName("test")
                .nbAuthor("author")
                .nbBdate(LocalDateTime.now())
                .nbGenre("genre")
                .nbComp("comp")
            .build());

        Orderinfo orderinfo = orderinfoRepository.saveAndFlush(Orderinfo.builder()
                .orderPerson("test")
                .orderBookno(1)
            .orderBooktype("1")
            .build());

        testEntityManager.clear();
        List<Orderinfo> orderInfoList = orderinfoRepository.selectNewBookOrderAll();
        assertThat(orderInfoList.get(0).getOrderBooktype()).isEqualTo("1");
    }

    @Test
    void selectOldBookOrderAll() {
        oldBookRepository.saveAndFlush(OldBook.builder()
            .obName("test")
            .obAuthor("author")
            .obBdate(LocalDateTime.now())
            .obGenre("genre")
            .obComp("comp")
            .build());

        orderinfoRepository.saveAndFlush(Orderinfo.builder()
                .orderBookno(1)
            .orderBooktype("2")
            .build());

        testEntityManager.clear();

        final List<Orderinfo> orderInfoList = orderinfoRepository.selectOldBookOrderAll();
        assertThat(orderInfoList.get(0).getOrderBookno()).isEqualTo(1);
        assertThat(orderInfoList.get(0).getOrderBooktype()).isEqualTo("2");
    }

    @Test
    void selectNoBankAll() {
        orderinfoRepository.findAllByOrderStateGroupByOrderListNo();
    }

    @Test
    void selectOrderAll() {
        orderinfoRepository.notEqualOrderStateGroupByOrderListNo();
    }

    @Test
    void selectDelayDeposit() {
        List<Orderinfo> resultList = orderinfoRepository.findAll();
        resultList.stream()
            .filter(this::isOrderStateZero)
            .filter(this::isBookTypeEquals1)
            .filter(this::isDifferenceGreaterThen2Days)
            .collect(Collectors.toList());
    }

    private boolean isBookTypeEquals1(Orderinfo orderinfo) {
        return orderinfo.getOrderBooktype().equals("1");
    }

    private boolean isOrderStateZero(Orderinfo orderinfo) {
        return orderinfo.getOrderState().equals("0");
    }

    private boolean isDifferenceGreaterThen2Days(Orderinfo orderinfo) {
        return ChronoUnit.DAYS.between(orderinfo.getOrderDate(), LocalDateTime.now()) > 2;
    }

    @Test
    void orderDateDesc3List() {
        orderinfoRepository.findTop3ByOrderIdOrderByOrderDateDesc("9");
    }

    @Test
    void findOrderListAll() {
        String orderId = "1";
        orderinfoRepository.findOldBookOrderListAll(orderId);
    }

    @Test
    void 새책_주문정보_조회() {
        String orderId = "2";
        orderinfoRepository.findNewBookOrderListAll(orderId);
    }

    @Test
    void 접수_화면() {
        String orderPerson = "test";
        orderinfoRepository.findByOrderPerson(orderPerson, Sort.by(Sort.Direction.DESC, "orderNo"));
    }

}