package pack.orderinfo.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositorySupportImpl implements OrderRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;


}
