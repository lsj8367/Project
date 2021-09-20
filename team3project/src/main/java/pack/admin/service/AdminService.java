package pack.admin.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.admin.model.AdminUpdateDto;
import pack.domain.entity.Admin;
import pack.domain.entity.NewBook;
import pack.model.NewBookDto;
import pack.repository.AdminRepository;
import pack.repository.NewBookRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final AdminRepository adminRepository;
    private final NewBookRepository newBookRepository;

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

    public void deleteAdminInfo(String adminId) throws Exception {
        adminRepository.deleteAdminByAdminId(adminId);
    }

    public NewBook getMostSellBook() {
        return newBookRepository.selectBestSeller();
    }

    public boolean insertBookData(NewBookDto newBookDto) {
        try {
            newBookRepository.save(NewBook.toEntity(newBookDto));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public void upNbStock(int nbNo, int[] rank, int[] no) {
        Optional<NewBook> optionalNewBook = newBookRepository.findById((long) nbNo);

        optionalNewBook.ifPresent(newBook -> {
            for (int i = 0; i < no.length; i++) {
                if (rank[i] == 1) {
                    newBook.setNbStock(newBook.getNbStock() + 200);
                } else if (rank[i] == 2) {
                    newBook.setNbStock(newBook.getNbStock() + 100);
                } else if (rank[i] == 3) {
                    newBook.setNbStock(newBook.getNbStock() + 50);
                } else {
                    newBook.setNbStock(newBook.getNbStock());
                }
            }
        });
    }

    public void rollBackStock(int nbNo) {
        Optional<NewBook> optionalNewBook = newBookRepository.findById((long) nbNo);

        optionalNewBook.ifPresent(newBook -> {
        });
    }

}
