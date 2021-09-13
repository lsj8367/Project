package pack.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import pack.config.QuerydslConfig;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(QuerydslConfig.class)
class NewBookRepositoryTest {
    @Autowired
    NewBookRepository newBookRepository;

    @Test
    void selectBestSeller() {
        newBookRepository.selectBestSeller();
    }
}