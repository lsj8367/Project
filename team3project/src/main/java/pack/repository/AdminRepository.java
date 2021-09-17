package pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pack.domain.entity.Admin;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByAdminId(String adminId);
    List<Admin> findAllByAdminAcc(int adminAcc); //0 고정
    void deleteAdminByAdminNo(Long adminNo);
    void deleteAdminByAdminId(String adminId);
}
