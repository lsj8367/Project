package pack.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import pack.config.QuerydslConfig;
import pack.domain.entity.Admin;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@Import(QuerydslConfig.class)
class AdminRepositoryTest {
    @Autowired
    AdminRepository adminRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    EntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager = testEntityManager.getEntityManager();
    }

    //마이바티스 id이름이랑 다 똑같은 테스트를 만들것이다.

    @Test
    @DisplayName("selectAdminData, adminIdCheck도 같이 해도 될듯")
    void selectAdminData() {
        Optional<Admin> admin = adminRepository.findByAdminId("asd");
        assertThat(admin.get().getAdminId()).isEqualTo("asd");
        assertThat(admin.get().getAdminName()).isEqualTo("방예림");
        assertThat(admin.get().getAdminJik()).isEqualTo("이사");
    }

    @Test
    void getAdminAll() {
        adminRepository.findAll();
    }

    @Test
    void adminYetAll() {
        List<Admin> list = adminRepository.findAllByAdminAcc(0);
        assertThat(list.size()).isEqualTo(3);
    }

    @Test
    @Transactional
    void upadmin() {
        Optional<Admin> optAdmin = adminRepository.findByAdminId("asd");
        Admin admin = optAdmin.get();
        admin.setAdminAcc(0);
        adminRepository.save(admin);
        assertThat(admin.getAdminAcc()).isEqualTo(0);
    }

    @Test
    @Transactional
    void upadminJik() {
        Optional<Admin> optAdmin = adminRepository.findByAdminId("asd");
        Admin admin = optAdmin.get();
        admin.setAdminJik("대리");
        adminRepository.saveAndFlush(admin);
        assertThat(admin.getAdminJik()).isEqualTo("대리");
    }

    @Test
    @Transactional
    void updateAdminInfo() {
        Optional<Admin> optAdmin = adminRepository.findByAdminId("asd");
        Admin admin = optAdmin.get();
        admin.setAdminPassword("456");
        adminRepository.saveAndFlush(admin);
        assertThat(admin.getAdminPassword()).isEqualTo("456");
    }

    @Test
    @Transactional
    void deladmin() {
        adminRepository.deleteById(1L);
        adminRepository.flush();

        Optional<Admin> optAdmin = adminRepository.findById(1L);
        assertThat(optAdmin.isPresent()).isFalse();
    }

}