package pack.user.model;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import pack.model.OldBookDto;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(OldBookDao.class)
class OldBookDaoTest {
    @Autowired
    private OldBookDao oldBookDao;

    @Test
    void bookInfo() {
        OldBookDto oldBookDto = oldBookDao.bookInfo("3");
        System.out.println(oldBookDto.toString());
        assertThat(oldBookDto.getOb_name()).isEqualTo("진화심리학");
    }

    @Test
    void rentalInfo() {
        OldBookDto oldBookDto = oldBookDao.rentalInfo("9");
        System.out.println(oldBookDto.toString());
    }



}