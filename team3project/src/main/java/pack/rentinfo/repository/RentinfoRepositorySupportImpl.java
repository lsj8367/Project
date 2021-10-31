package pack.rentinfo.repository;

import static pack.oldbook.domain.QOldBook.oldBook;
import static pack.rentinfo.domain.QRentinfo.rentinfo;
import static pack.user.domain.QUser.user;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pack.rentinfo.domain.Rentinfo;

@RequiredArgsConstructor
public class RentinfoRepositorySupportImpl implements RentinfoRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Rentinfo> findAllByRentBook() {
        jpaQueryFactory.selectFrom(rentinfo)
            .innerJoin(oldBook).on(rentinfo.rentNo.eq(oldBook.obNo))
            .innerJoin(user).on(rentinfo.rentId.eq(user.userId))
            .fetch();
        return null;
    }

    @Override
    public List<Rentinfo> rentMonth() {
        return jpaQueryFactory.selectFrom(rentinfo)
            .groupBy(rentinfo.rentSdate).fetch();
    }

}
