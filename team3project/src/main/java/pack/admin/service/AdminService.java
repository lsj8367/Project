package pack.admin.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.admin.model.AdminUpdateDto;
import pack.domain.entity.Admin;
import pack.repository.AdminRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;

    private static RuntimeException notFoundAdminId() {
        return new RuntimeException("찾는 관리자 계정이 없습니다.");
    }

    public Admin selectAdminData(String adminId) {
        Optional<Admin> optionalAdmin = adminRepository.findByAdminId(adminId);
        return optionalAdmin.orElseThrow(AdminService::notFoundAdminId);
    }

    public List<Admin> adminYetAll() {
        return adminRepository.findAllByAdminAcc(0);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public boolean updateAdmin(AdminUpdateDto adminUpdateDto) {
        Admin admin = selectAdminData(adminUpdateDto.getAdminId());
        admin.setAdminId(adminUpdateDto.getAdminId());
        admin.setAdminAcc(adminUpdateDto.getAdminAcc());

        return true;
    }

    public boolean updateAdminInfo(String adminId, String adminPassword, String adminName) {
        try {
            Admin admin = selectAdminData(adminId);
            admin.setAdminPassword(adminPassword);
            admin.setAdminName(adminName);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    public void deleteAdminInfo(String adminId) throws Exception{
        adminRepository.deleteAdminByAdminId(adminId);
    }
}
