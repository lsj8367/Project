package pack.rentinfo.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import pack.config.QuerydslConfig;

@DataJpaTest
@Import(QuerydslConfig.class)
class RentinfoRepositoryTest {

    @Autowired
    private RentinfoRepository rentinfoRepository;

    @Test
    void 렌트한_달_가져오기() {
        rentinfoRepository.rentMonth();
    }
}