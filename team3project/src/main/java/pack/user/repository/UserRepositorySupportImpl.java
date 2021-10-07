package pack.user.repository;

import static pack.common.enums.Grade.FIFTH_GRADE;
import static pack.common.enums.Grade.FORTH_GRADE;
import static pack.oldbook.domain.QOldBook.oldBook;
import static pack.user.domain.QUser.user;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import pack.user.domain.User;
import pack.user.model.UserDto;

@RequiredArgsConstructor
public class UserRepositorySupportImpl implements UserRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<UserDto> selectRefuseCount() {
        final List<Tuple> resultList = jpaQueryFactory.select(user, oldBook.count().as("rfcount"))
            .from(oldBook)
            .innerJoin(user).on(user.userId.eq(oldBook.obUserid))
            .where(oldBook.obState.eq(FORTH_GRADE.getGrade())
                .or(oldBook.obState.eq(FIFTH_GRADE.getGrade()))
                .and(DateExpression.currentDate().year().eq(oldBook.obDdate.substring(0, 3).castToNum(Integer.class))))
            .groupBy(oldBook.obDonor)
            .having(oldBook.count().gt(10))
            .fetch();

        List<UserDto> list = new ArrayList<>();

        for (Tuple tuple : resultList) {
            UserDto dto = UserDto.builder()
                .user_id(tuple.get(user.userId))
                .user_name(tuple.get(user.userName))
                .user_tel(tuple.get(user.userTel))
                .user_mail(tuple.get(user.userMail))
                .build();
            dto.setUser_penalty(String.valueOf(tuple.get(user.userPenalty)));

            dto.setRfcount(tuple.get(oldBook.count().intValue()));
            list.add(dto);
        }

        return list;
    }

    @Override
    public User selectBestRead() {
        return jpaQueryFactory.selectFrom(user)
            .orderBy(user.userRentcnt.desc())
            .limit(1)
            .fetchOne();
    }

}
