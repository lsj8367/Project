package pack.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import pack.domain.entity.Admin;

@Repository
public class AdminRepositorySupport extends QuerydslRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public AdminRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Admin.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }
}
