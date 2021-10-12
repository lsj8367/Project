package pack.orderinfo.repository;

import static pack.newbook.domain.QNewBook.newBook;
import static pack.oldbook.domain.QOldBook.oldBook;
import static pack.orderinfo.domain.QOrderinfo.orderinfo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pack.orderinfo.domain.Orderinfo;

@RequiredArgsConstructor
public class OrderRepositorySupportImpl implements OrderRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public List<Orderinfo> selectNewBookOrderAll() {
        return jpaQueryFactory.selectFrom(orderinfo)
            .innerJoin(newBook).on(orderinfo.orderBookno.eq(newBook.id.intValue()))
            .where(orderinfo.orderBooktype.eq("1"))
            .fetch();
    }

    @Override
    public List<Orderinfo> selectOldBookOrderAll() {
        return jpaQueryFactory.selectFrom(orderinfo)
            .innerJoin(oldBook).on(orderinfo.orderBookno.eq(oldBook.obNo.intValue()))
            .where(orderinfo.orderBooktype.eq("2"))
            .fetch();
    }

    @Override
    public List<Orderinfo> findAllByOrderStateGroupByOrderListNo() {
        return jpaQueryFactory.selectFrom(orderinfo)
            .where(orderinfo.orderState.eq("0"))
            .groupBy(orderinfo.orderlistNo)
            .fetch();
    }

    @Override
    public List<Orderinfo> notEqualOrderStateGroupByOrderListNo() {
        return jpaQueryFactory.selectFrom(orderinfo)
            .where(orderinfo.orderState.ne("0"))
            .groupBy(orderinfo.orderlistNo)
            .fetch();
    }

}
