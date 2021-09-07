package pack.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CardInfoRepositorySupportImpl implements CardInfoRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;
}
