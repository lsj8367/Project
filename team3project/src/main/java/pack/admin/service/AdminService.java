package pack.admin.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pack.admin.domain.Admin;
import pack.admin.model.AdminUpdateDto;
import pack.admin.repository.AdminRepository;
import pack.common.enums.Grade;
import pack.newbook.domain.NewBook;
import pack.newbook.model.NewBookDto;
import pack.newbook.repository.NewBookRepository;
import pack.newbook.utils.AnotherStock;
import pack.newbook.utils.FirstStock;
import pack.newbook.utils.SecondStock;
import pack.newbook.utils.Stock;
import pack.newbook.utils.StockState;
import pack.newbook.utils.ThirdStock;
import pack.oldbook.domain.OldBook;
import pack.oldbook.repository.OldBookRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;
    private final NewBookRepository newBookRepository;
    private final OldBookRepository oldBookRepository;
    private final StockState stockState = StockState.of(Arrays.asList(new FirstStock(), new SecondStock(),
        new ThirdStock(), new AnotherStock()));

    public Admin selectAdminData(String adminId) {
        return adminRepository.findByAdminId(adminId)
            .orElseThrow(() -> new RuntimeException("찾는 관리자 계정이 없습니다."));
    }

    public List<Admin> adminYetAll() {
        return adminRepository.findAllByAdminAcc(0);
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @Transactional
    public boolean updateAdmin(AdminUpdateDto adminUpdateDto) {
        Admin admin = selectAdminData(adminUpdateDto.getAdminId());
        admin.setAdminId(adminUpdateDto.getAdminId());
        admin.setAdminAcc(adminUpdateDto.getAdminAcc());

        return true;
    }

    @Transactional
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

    @Transactional
    public void deleteAdminInfo(String adminId) {
        adminRepository.deleteAdminByAdminId(adminId);
    }

    public NewBook getMostSellBook() {
        return newBookRepository.selectBestSeller();
    }

    @Transactional
    public void insertBookData(NewBookDto newBookDto) {
        newBookRepository.save(NewBook.toEntity(newBookDto));
    }

    public void upNbStock(int nbNo, int[] rank, int[] no) {
        Optional<NewBook> optionalNewBook = newBookRepository.findById((long) nbNo);
        optionalNewBook.ifPresent(newBook -> {
            for (int i = 0; i < no.length; i++) {
                if (rank[i] > 3) {
                    rank[i] = -1;
                }
                Stock stock = stockState.getStockState(rank[i]);
                newBook.setNbStock(
                    stock.getNewBookStock(newBook.getNbStock())
                );
            }
        });
    }

    public boolean rollBackStock(int nbNo, int nbScount) {
        try {
            newBookRepository.rollbackStock(nbScount, (long) nbNo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<NewBook> selectBookDataAll() {
        return newBookRepository.findAll();
    }

    public List<OldBook> selectSellObAll() {
        return oldBookRepository.findByObState(Grade.FIRST_GRADE.getGrade());
    }

    public List<OldBook> selectStandByAll() {
        return oldBookRepository.findByObState(Grade.READY.getGrade());
    }

    public List<OldBook> selectRentBookAll() {
        return oldBookRepository.findByObStateOrObState(
            Grade.SECOND_GRADE.getGrade(), Grade.THIRD_GRADE.getGrade());
    }

    public List<OldBook> selectReuseAll() {
        return oldBookRepository.findByObStateOrObState(
            Grade.FORTH_GRADE.getGrade(), Grade.FIFTH_GRADE.getGrade());
    }

    public List<OldBook> getMostRentBook() {
        return oldBookRepository.getMostRentBook();
    }

    public void upObState(int obNo, String obState) {
        final Optional<OldBook> optionalOldBook = oldBookRepository.findById((long) obNo);
        optionalOldBook.ifPresent(oldBook -> {
            oldBook.setObState(obState);
        });
    }

    public void removeOb(int obNo) {
        final Optional<OldBook> optionalOldBook = oldBookRepository.findById((long) obNo);
        optionalOldBook.ifPresent(oldBookRepository::delete);
    }

}
